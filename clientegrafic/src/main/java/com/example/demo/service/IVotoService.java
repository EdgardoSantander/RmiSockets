package com.example.demo.service;

import com.example.demo.entity.Voto;
import com.example.demo.model.TotalVotos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVotoService extends Remote {
    public Boolean guardarVoto(Voto voto) throws RemoteException;
    public Boolean rectificarExistencia(Voto voto) throws RemoteException;
    public TotalVotos mandarTotalVotos() throws RemoteException;
    public List<Voto> obtenerTodosVotosDatos() throws RemoteException;
}
