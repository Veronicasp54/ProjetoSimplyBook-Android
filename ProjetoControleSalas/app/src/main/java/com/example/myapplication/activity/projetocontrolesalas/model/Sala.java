package com.example.myapplication.activity.projetocontrolesalas.model;

public class Sala {

    private int id;
    private String nomeSala;
    private String descricaoSala;
    private String dimensaoSala;
    private String localizacao;
    private boolean arCondicionado;
    private boolean multimidia;
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

    public void setDimensaoSala(String dimensaoSala) {
        this.dimensaoSala = dimensaoSala;
    }


    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public void setMultimidia(boolean multimidia) {
        this.multimidia = multimidia;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }


}
