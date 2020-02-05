package com.example.myapplication.activity.projetocontrolesalas.model;

public class Sala {

    private int id;
    private String nomeSala;
    private String descricaoSala;
    private double dimensaoSala;

    public Sala() {
    }

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

    public double getDimensaoSala() {
        return dimensaoSala;
    }

    public void setDimensaoSala(double dimensaoSala) {
        this.dimensaoSala = dimensaoSala;
    }
}
