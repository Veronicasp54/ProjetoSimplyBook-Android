package com.example.myapplication.activity.projetocontrolesalas.model;

import com.example.myapplication.activity.projetocontrolesalas.empresa.Cep;

import java.util.Date;

public class Empresa {

    private int id;
    private String nomeEmpresa;
    private int idOrganizacaoPai;
    private String tipoEmpresa;
    private String emailEmpresa;
    private String dominio;
    private Date dataCriacao;
    private Date dataAlteraca;
    private Cep cep;


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

    public int getIdOrganizacaoPai() {
        return idOrganizacaoPai;
    }

    public void setIdOrganizacaoPai(int idOrganizacaoPai) {
        this.idOrganizacaoPai = idOrganizacaoPai;
    }


    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAlteraca() {
        return dataAlteraca;
    }

    public void setDataAlteraca(Date dataAlteraca) {
        this.dataAlteraca = dataAlteraca;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }
}
