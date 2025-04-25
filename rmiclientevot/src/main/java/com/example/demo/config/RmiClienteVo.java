package com.example.demo.config;

import com.example.demo.entity.Voto;
import com.example.demo.service.IVotoService;
import org.springframework.stereotype.Component;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


@Component
public class RmiClienteVo {

    public void guardarVoto(Voto voto){
        try{

            IVotoService service = (IVotoService) Naming.lookup("//localhost:1099/VotoService");

            boolean resultado = service.guardarVoto(voto);
            System.out.println(resultado?"Voto guardado exitosamente":"voto no guardado con etsitos");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}