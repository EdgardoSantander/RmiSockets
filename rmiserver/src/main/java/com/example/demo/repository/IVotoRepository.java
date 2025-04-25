package com.example.demo.repository;

import com.example.demo.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IVotoRepository extends JpaRepository<Voto,Integer> {
    @Query("SELECT COUNT(v) FROM Voto v WHERE v.candidato = :nombre")
    int contarPorCandidato(@Param("nombre") String nombre);
    boolean existsByNombreVotante(String nombreVotante);

}
