package Servidor;

import Calculadora.Implementaciones;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    private ServerState serverState;

    public ClientHandler(Socket socket, ServerState serverState) {
        this.socket = socket;
        this.serverState = serverState;
    }

    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            String entradaCliente;

            while ((entradaCliente = entrada.readLine()) != null) {
                // Dividir la entrada en número y operador (ejemplo: "5 +")
                String[] partes = entradaCliente.split(" ");
                if (partes.length == 2) {
                    try {
                        double numero = Double.parseDouble(partes[0]);
                        String operador = partes[1];

                        // Agregar el número y operador al estado del servidor
                        serverState.agregarNumeroOperador(numero, operador);
                        salida.println("Número y operador agregados al registro del servidor.");
                    } catch (NumberFormatException e) {
                        salida.println("Entrada inválida.");
                    }
                } else if (entradaCliente.equals("calcular")) {
                    // Realizar el cálculo usando el estado del servidor
                    double resultado = serverState.getAcumulado();
                    salida.println("Resultado: " + resultado);
                } else {
                    salida.println("Entrada inválida.");
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
