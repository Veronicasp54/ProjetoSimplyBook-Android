package com.example.myapplication.activity.projetocontrolesalas.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.model.Sala;

import java.util.List;

public class AdapterListSalas extends BaseAdapter {

    private final List<Sala> salas;
    private final Activity act;
    private SharedPreferences preferences;
    public static final String salaPreferences = "salaPreferences";
    private Context context;


    public AdapterListSalas(List<Sala> salas, Activity act, Context context) {
        this.salas = salas;
        this.act = act;
        this.context = context;
    }

    @Override
    public int getCount() {
        return salas.size();
    }

    @Override
    public Object getItem(int position) {
        return salas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.list_item_salas, parent, false);

        Sala sala = salas.get(position);

        //references = context.getSharedPreferences(salaPreferences, Context.MODE_PRIVATE);

       // String dimesnsaoSala = preferences.getString("salaDimensao", null);
        // //String capacidadeSala = preferences.getString("salaCapacidade", null);

       // System.out.println("dimen"+dimesnsaoSala);


        TextView nomeSala = (TextView)
                view.findViewById(R.id.textViewNomeSala);
       /*/ TextView dimensaoSala = (TextView)
                view.findViewById(R.id.textViewDesc);
        TextView capacidade = (TextView)
                view.findViewById(R.id.capacidade);/*/

        nomeSala.setText(sala.getNomeSala());
//        dimensaoSala.setText(sala.getDimensaoSala());
//        capacidade.setText(sala.getCapacidade());

        return view;
    }


}