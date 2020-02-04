package com.example.myapplication.activity.projetocontrolesalas.model;

import com.example.myapplication.activity.projetocontrolesalas.model.Empresa;

public class Usuario {

    private String nomeUser;
    private String emailUser;
    private int id;
    private int idEmpresa;

    public Usuario (){

    }

    public Usuario(String nomeUser, String emailUser, int id, int idEmpresa) {
        this.nomeUser = nomeUser;
        this.emailUser = emailUser;
        this.id = id;
        this.idEmpresa = idEmpresa;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
