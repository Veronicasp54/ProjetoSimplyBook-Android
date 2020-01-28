package com.example.myapplication.activity.projetocontrolesalas.model;

import com.example.myapplication.activity.projetocontrolesalas.model.Empresa;

public class Usuario {

    private String nomeUser;
    private String emailUser;
    private int id;
    private Empresa empresa;

    public Usuario(String nomeUser, String emailUser, int id, Empresa empresa) {
        this.nomeUser = nomeUser;
        this.emailUser = emailUser;
        this.id = id;
        this.empresa = empresa;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
