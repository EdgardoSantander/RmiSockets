package com.example.demo.iuserdesk.model;

import java.io.Serializable;

public class TotalVotos implements Serializable {
    public Integer votoCandidato1; //ocho
    public Integer votoCandidato2; //pancho
    public Integer votoCandidato3; // lolita
    public Integer votoCandidatoNulo; // nulo

    public TotalVotos(){

    }
    public TotalVotos(Integer a, Integer b, Integer c, Integer nulo){
        this.votoCandidato1 = a;
        this.votoCandidato2 = b;
        this.votoCandidato3 = c;
        this.votoCandidatoNulo = nulo;
    }

    public Integer getVotoCandidato1() {
        return votoCandidato1;
    }

    public void setVotoCandidato1(Integer votoCandidato1) {
        this.votoCandidato1 = votoCandidato1;
    }

    public Integer getVotoCandidato2() {
        return votoCandidato2;
    }

    public void setVotoCandidato2(Integer votoCandidato2) {
        this.votoCandidato2 = votoCandidato2;
    }

    public Integer getVotoCandidato3() {
        return votoCandidato3;
    }

    public void setVotoCandidato3(Integer votoCandidato3) {
        this.votoCandidato3 = votoCandidato3;
    }

    public Integer getVotoCandidatoNulo() {
        return votoCandidatoNulo;
    }

    public void setVotoCandidatoNulo(Integer votoCandidatoNulo) {
        this.votoCandidatoNulo = votoCandidatoNulo;
    }
}
