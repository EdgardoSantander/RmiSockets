package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serializable;
//Creacion de Entidad para manejar los votos
//Se utiliza lombok para automatizar algunas cosas
// al igual que jakarta que viene siendo como jpa para manejo de datos
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Voto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVoto;
    private String nombreVotante;
    private String candidato;
}
