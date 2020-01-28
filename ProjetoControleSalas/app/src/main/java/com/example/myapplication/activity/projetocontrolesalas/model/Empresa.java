package com.example.myapplication.activity.projetocontrolesalas.model;

import com.example.myapplication.activity.projetocontrolesalas.empresa.Cep;

public class Empresa {

    private int id;
    private String nomeEmpresa;
    private String emailEmpresa;
    private Cep cep;

    public Empresa(int id, String nomeEmpresa, String emailEmpresa) {
        this.id = id;
        this.nomeEmpresa = nomeEmpresa;
        this.emailEmpresa = emailEmpresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }
}
