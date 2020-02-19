package com.example.myapplication.activity.projetocontrolesalas.menu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.model.Sala;
import com.example.myapplication.activity.projetocontrolesalas.services.RequestExibirReservas;
import com.example.myapplication.activity.projetocontrolesalas.services.RequestSalas;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DayFragment extends Fragment {

    private View view;
    private TextView dataAtual, quantReunioes;

    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_day, container, false);

        iniciaComponentes();


        return view;
    }

    private void iniciaComponentes() {

        dataAtual = view.findViewById(R.id.dataAtual);
        dataAtual.setText(getData());

        quantReunioes = view.findViewById(R.id.textQuantReunioes);

    }

    private String getData() {
        Date data = new Date();
        Locale local = new Locale("pt", "BR");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd',' EEEE", local);
        return dateFormat.format(data);

    }

    private void exibirReservas() {

        String requestReservas = null;
        try {
            requestReservas = new RequestExibirReservas().execute(preferences.getString("id_usuario", null)).get();

            System.out.println(requestReservas);

            JSONArray reservasJson = new JSONArray(requestReservas);

            if (reservasJson.length() > 0) {

                for (int i = 0; i < reservasJson.length(); i++) {
                    JSONObject reservaJson = reservasJson.getJSONObject(i);



                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}