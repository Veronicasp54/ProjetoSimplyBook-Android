package com.example.myapplication.activity.projetocontrolesalas.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.activity.projetocontrolesalas.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DayFragment extends Fragment {

    private View view;
    private TextView dataAtual, quantReunioes;

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
}