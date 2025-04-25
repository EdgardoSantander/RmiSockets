package com.example.demo.model;


import com.example.demo.service.IVotoService;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@Component
public class EnvioVotos {
    public TotalVotos obtenerResultados() throws MalformedURLException, NotBoundException, RemoteException {
        // Aqui se inicia la conexion con el rmi por medio de la interface que se creo
        IVotoService service = (IVotoService) Naming.lookup("//localhost:1099/VotoService");
        TotalVotos votos = service.mandarTotalVotos();
        //Mensaje creado solo para rectificar que se estan recibiendo los datos de manera exitosa
        System.out.println("Chespirito: "+votos.getVotoCandidato1()+" Chefso: "+ votos.getVotoCandidato2() +" Lolita: "+ votos.getVotoCandidato3() +" Nulo: "+ votos.getVotoCandidatoNulo());
        //Aqui regresamos el objeto con los datos
        return  votos;
    }
}
