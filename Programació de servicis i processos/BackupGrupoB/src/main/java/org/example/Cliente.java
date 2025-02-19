package org.example;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente implements Runnable {
    public InetAddress inetAddress;
    public Socket socketToServidor;
    public int puerto;

    public DataOutputStream fluxOut;

    public Cliente(InetAddress ia, int p) throws IOException {
        inetAddress = ia;
        puerto = p;
        socketToServidor = new Socket(ia, p);
    }

    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();

        try {
            fluxOut = new DataOutputStream(socketToServidor.getOutputStream());
            System.out.print(nombre + "Que quieres hacer? (prueba 'backup'): ");
            Scanner sc = new Scanner(System.in);
            String mensajeEnviado = sc.nextLine();
            fluxOut.writeUTF(mensajeEnviado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}