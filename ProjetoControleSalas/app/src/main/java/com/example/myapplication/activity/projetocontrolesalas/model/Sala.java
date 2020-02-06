package com.example.myapplication.activity.projetocontrolesalas.model;

public class Sala {

    private int id;
    private String nomeSala;
    private String descricaoSala;
    private String dimensaoSala;
    private String localizacao;
    private String arCondicionado;
    private String multimidia;
    private String capacidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeSala() {
        return nomeSala;
    }

    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
    }

    public String getDescricaoSala() {
        return descricaoSala;
    }

    public void setDescricaoSala(String descricaoSala) {
        this.descricaoSala = descricaoSala;
    }

    public String getDimensaoSala() {
        return dimensaoSala;
    }

    public void setDimensaoSala(String dimensaoSala) {
        this.dimensaoSala = dimensaoSala;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(String arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public String getMultimidia() {
        return multimidia;
    }

    public void setMultimidia(String multimidia) {
        this.multimidia = multimidia;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }
}
