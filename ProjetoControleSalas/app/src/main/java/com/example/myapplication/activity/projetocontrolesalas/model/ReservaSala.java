package com.example.myapplication.activity.projetocontrolesalas.model;

import java.sql.Time;
import java.util.Date;

public class ReservaSala {

    private Sala sala;
    private Date dataReserva;
    private Time horarioUsoInicio;
    private Time horarioUsoFinal;
    private int quantidadePessosas;

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Time getHorarioUsoInicio() {
        return horarioUsoInicio;
    }

    public void setHorarioUsoInicio(Time horarioUsoInicio) {
        this.horarioUsoInicio = horarioUsoInicio;
    }

    public Time getHorarioUsoFinal() {
        return horarioUsoFinal;
    }

    public void setHorarioUsoFinal(Time horarioUsoFinal) {
        this.horarioUsoFinal = horarioUsoFinal;
    }

    public int getQuantidadePessosas() {
        return quantidadePessosas;
    }

    public void setQuantidadePessosas(int quantidadePessosas) {
        this.quantidadePessosas = quantidadePessosas;
    }
}