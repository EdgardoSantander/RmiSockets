package com.example.demo.config;

import com.example.demo.repository.IVotoRepository;
import com.example.demo.service.VotoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Component
public class RmiServer {

    @Autowired
    private IVotoRepository votoRepository;

    @PostConstruct
    public void iniciarRMI() {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            VotoService remoteService = new VotoService(votoRepository);
            registry.rebind("VotoService", remoteService);
            System.out.println("✅ RMI Server registrado con éxito");
        } catch (Exception e) {
            System.err.println("❌ Error al iniciar RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

