package com.example.myapplication.activity.projetocontrolesalas.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.model.ReservaSala;
import com.example.myapplication.activity.projetocontrolesalas.model.Sala;
import com.example.myapplication.activity.projetocontrolesalas.utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

public class AdapterReservasUser extends BaseAdapter {

    private final List<ReservaSala> reservas;
    private final Activity act;
    private TinyDB tinydb;

    public AdapterReservasUser(List<ReservaSala> reservas, Activity act) {
        this.reservas = reservas;
        this.act = act;
    }

    @Override
    public int getCount() {
        return reservas.size();
    }

    @Override
    public Object getItem(int position) {
        return reservas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return reservas.get(position).getIdReserva();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.list_item_reserva_unique, parent, false);

        ReservaSala reserva = reservas.get(position);

        TextView nomeSala = (TextView)
                view.findViewById(R.id.textViewNomeSala);
        TextView descricao = (TextView)
                view.findViewById(R.id.textDescSala);
        TextView dataReuniao = (TextView)
                view.findViewById(R.id.textDataReserva);
        TextView textViewHourReuniao =
                view.findViewById(R.id.textHoraReuniao);

        tinydb = new TinyDB(parent.getContext());
        ArrayList<Sala> salas = tinydb.getListSalaObject("salas");

        for (int i = 0; i < salas.size(); i++) {

            if(salas.get(i).getId() == reserva.getIdSala()){
                nomeSala.setText(salas.get(i).getNomeSala());

            }
        }

        //   nomeSala.setText(reserva.getIdSala());
        // nomeSala.setText(reserva.getNomeSala());
        descricao.setText(reserva.getDescricaoReserva());
        dataReuniao.setText(reserva.getDataReserva());
        String horarioConcatenado = reserva.getHorarioInicio().concat(" - " + reserva.getHorarioFinal());
        textViewHourReuniao.setText(horarioConcatenado);

        return view;
    }
}