package com.example.demo.service;

import com.example.demo.entity.Voto;
import com.example.demo.model.TotalVotos;
import com.example.demo.repository.IVotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

@Service
public class VotoService extends UnicastRemoteObject implements IVotoService{
        IVotoRepository votoRepository;

    @Autowired
    public VotoService(IVotoRepository votoRepository) throws RemoteException {
        super();
        this.votoRepository = votoRepository;
    }

    @Override
    public Boolean guardarVoto(Voto voto) throws RemoteException {
        if(rectificarExistencia(voto)){
            return false;
        }
        votoRepository.save(voto);
        return true;

    }

    @Override
    public Boolean rectificarExistencia(Voto voto) throws RemoteException {
        Boolean respuesta = votoRepository.existsByNombreVotante(voto.getNombreVotante());
        return respuesta;
    }


    @Override
    public TotalVotos mandarTotalVotos() throws RemoteException{
        Integer a = votoRepository.contarPorCandidato("Chespirito del Ocho");
        Integer b = votoRepository.contarPorCandidato("Chefso Villa");
        Integer c = votoRepository.contarPorCandidato("Lolita la del Barrio");
        Integer nulo = votoRepository.contarPorCandidato("Nulo");

        return new TotalVotos(a, b, c, nulo);
    }

    @Override
    public List<Voto> obtenerTodosVotosDatos() throws RemoteException{
        System.out.println(votoRepository.findAll());
        return votoRepository.findAll();
    }
}
