package org.example;

import java.io.IOException;
import java.net.InetAddress;

public class Main {
    public static int puerto = 7777;

    public static void main(String[] args) {
        try {
            Thread servidor = new Thread(new Servidor(puerto), "[ServidorTCP] ");
            servidor.start();

            Thread.sleep(1000);

            Thread cliente = new Thread(new Cliente(InetAddress.getByName("127.0.0.1"), puerto), "[ClienteTCP] ");
            cliente.start();

            servidor.join();
            cliente.join();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}