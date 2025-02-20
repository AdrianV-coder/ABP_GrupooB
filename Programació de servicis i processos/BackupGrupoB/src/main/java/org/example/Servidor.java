package org.example;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor implements Runnable {
    public ServerSocket serverSocket;
    public Socket socketToCliente;
    public int puerto;

    public DataInputStream fluxIn;

    public Servidor(int p) throws IOException {
        puerto = p;
        serverSocket = new ServerSocket(p);
    }

    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();

        try {
            socketToCliente = serverSocket.accept();
            fluxIn = new DataInputStream(socketToCliente.getInputStream());

            String mensajeRecibido = fluxIn.readUTF();

            if (mensajeRecibido.equals("backup")) {
                hacerBackup();
            } else {
                System.out.println(nombre + "Comando no reconocido.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hacerBackup() {
        String nombre = Thread.currentThread().getName();

        String ssh = "ssh administrador@4.211.191.132 \"mysqldump -u root --password=1234 --databases abp_grupob > /home/administrador/backup.sql\"";

        if (comando(ssh)) {
            System.out.println(nombre + "Ssh realizado con éxito.");
        } else {
            System.err.println(nombre + "Error realizando el ssh.");
            return;
        }

        String backupAzure = "ssh administrador@4.211.191.132 \"test -f /home/administrador/backup.sql\"";
        if (comando(backupAzure)) {
            System.out.println(nombre + "Archivo backup encontrado en Azure.");
            String rutaLocal = "src/main/resources/backup.sql";
            String copiaBackup = "scp administrador@4.211.191.132:/home/administrador/backup.sql " + rutaLocal;

            if (comando(copiaBackup)) {
                System.out.println(nombre + "Archivo de backup copiado en: " + rutaLocal);
            } else {
                System.err.println(nombre + "Error copiando el backup.");
            }

            enviarCorreo();
        } else {
            System.err.println(nombre + "Archivo backup no encontrado.");
        }
    }

    public void enviarCorreo() {
        String nombre = Thread.currentThread().getName();
        System.out.println(nombre + "Quieres enviarle un correo a alguien? Escribe su correo ('no' para no enviar correo):");
        Scanner sc = new Scanner(System.in);
        String correoEscrito = sc.nextLine();
        String correo = "ssh -T administrador@4.211.191.132 \"echo 'Se ha realizado el backup sin fallos.' | mail -s 'Backup Realizado con Éxito' " + correoEscrito + "\"";

        if (!correoEscrito.equals("no")) {
            if (comando(correo)) {
                System.out.println(nombre + "Correo enviado con éxito.");
            } else {
                System.err.println(nombre + "Error enviando el correo.");
            }
        }
    }

    public boolean comando(String comando) {
        try {
            ProcessBuilder pb;

            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                pb = new ProcessBuilder("cmd", "/c", comando);
            } else {
                pb = new ProcessBuilder("/bin/bash", "-c", comando);
            }

            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            return process.waitFor() == 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}