package com.example.myapplication.activity.projetocontrolesalas.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.model.ReservaSala;

import java.util.List;

public class AdapterReservasAll extends BaseAdapter {

    private final List<ReservaSala> reservas;
    private final Activity act;

    public AdapterReservasAll(List<ReservaSala> reservas, Activity act) {
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.list_item_reserva_all, parent, false);

        ReservaSala reserva = reservas.get(position);

        // TextView nomeSala = (TextView)
        //         view.findViewById(R.id.item_nome_sala);
        TextView nomeOrganizador = (TextView)
                view.findViewById(R.id.textViewNome);
        TextView descricao = (TextView)
                view.findViewById(R.id.textDescricao);
        TextView dataReuniao = (TextView)
                view.findViewById(R.id.textDataReserva);
        TextView textViewHourInicio = view.findViewById(R.id.textHourReuniao);

        //  nomeSala.setText(reserva.getIdSala());
        // nomeSala.setText(reserva.getNomeSala());
        nomeOrganizador.setText(reserva.getNomeOrganizador());
        descricao.setText(reserva.getDescricaoReserva());
        dataReuniao.setText(reserva.getDataReserva());
        String horarioConcatenado = reserva.getHorarioInicio().concat(" - " + reserva.getHorarioFinal());
        textViewHourInicio.setText(horarioConcatenado);


        return view;
    }
}