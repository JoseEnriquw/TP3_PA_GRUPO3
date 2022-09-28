package com.example.tp3_pa_grupo3;

import java.util.List;

public class EParqueos {
    Integer id;
    String matricula;
    String tiempo;
    Usuarios usuario;

    public EParqueos(String matricula, String tiempo) {
        this.matricula = matricula;
        this.tiempo = tiempo;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public EParqueos() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int getIds(){return matricula.hashCode();}



}

