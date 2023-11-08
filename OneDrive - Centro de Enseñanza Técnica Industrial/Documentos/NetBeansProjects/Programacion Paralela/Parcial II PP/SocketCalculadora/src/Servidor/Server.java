package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PUERTO = 8082;

    public static void main(String[] args) {
        try {
            // Crear una instancia de ServerState
            ServerState serverState = new ServerState();

            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor en espera de conexiones...");

            while (true) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("Cliente conectado desde " + socketCliente.getInetAddress());

                // Pasar la instancia de ServerState al constructor de ClientHandler
                ClientHandler cliente = new ClientHandler(socketCliente, serverState);
                cliente.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
