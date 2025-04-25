package com.example.demo.models;

import com.example.demo.config.RmiClienteVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.example.demo.entity.Voto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Oyente {

    private static final int PUERTO = 12345;

    @PostConstruct
    public void iniciarOyente() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
                System.out.println("🔊 Oyente activo en el puerto: " + PUERTO);

                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println("📡 Conexión recibida desde: " + socket.getInetAddress());

                    // Leer JSON como texto
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String json = reader.readLine(); // asumimos una línea por mensaje

                    ObjectMapper objectMapper = new ObjectMapper();
                    // Convertimos el JSON a un objeto Voto
                    Voto votonuevo = objectMapper.readValue(json, Voto.class);

                    if (votonuevo != null) {
                        System.out.println("✅ Voto recibido: " + votonuevo);

                        RmiClienteVo handler = new RmiClienteVo(); // cliente RMI
                        handler.guardarVoto(votonuevo);

                    }

                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start(); // 🔁 Corre en un hilo separado
    }
}

