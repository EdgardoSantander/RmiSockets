package com.example.demo.model;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


@Component
public class Hablador {

    @PostConstruct
    public void iniciarHablador() {
        new Thread(() -> {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(5001);
                System.out.println("Esperando conexiones en el puerto 5001...");

                while (true) {  // Bucle para aceptar conexiones
                    try (Socket clientSocket = serverSocket.accept()) {
                        // Preparar los streams para recibir y enviar datos
                        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                        // Aca de castea el objeto que trae desde el socket de la ui para comparar y verificar si se regresan los datos de la tabla
                        String input = (String) in.readObject();
                        if ("1".equals(input)) {
                            //aqui se crea el manejador que trae los datos del rmi por medio del metodo obtenerResultados
                            EnvioVotos votos = new EnvioVotos();
                            TotalVotos nuevosvotos = votos.obtenerResultados();
                            out.writeObject(nuevosvotos);  // Enviamos los resultados al cliente
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error al iniciar el servidor de sockets", e);
            } finally {
                try {
                    if (serverSocket != null && !serverSocket.isClosed()) {
                        serverSocket.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
