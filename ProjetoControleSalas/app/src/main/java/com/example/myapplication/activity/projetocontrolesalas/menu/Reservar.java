package com.example.myapplication.activity.projetocontrolesalas.menu;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.model.Sala;

import java.util.ArrayList;
import java.util.List;

class Reservar extends AppCompatActivity {

    private TextView nomeSala, textTamanhoSala, textCapacidade;
    private ImageView iconCheckArCondicionado, iconCheckMultimidia;
    private List<Sala> salas = new ArrayList<>();
    private ImageButton buttonBack;
    private Button buttonReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_box_sala);

        iniciaComponentes();

    }

    private void iniciaComponentes() {

        nomeSala = (TextView) findViewById(R.id.textNomeSala);
        textTamanhoSala = (TextView) findViewById(R.id.textTamanho);
        textCapacidade = (TextView) findViewById(R.id.textCapacidade);

        iconCheckArCondicionado = findViewById(R.id.iconCheckArcond);
        iconCheckMultimidia = findViewById(R.id.iconCheckMultimidia);

        //getDetalhesSala(pos);
        clickVoltar();
        clickReservar();


    }

    private void clickVoltar() {

        buttonBack = findViewById(R.id.imageButtonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*/sair/*/
            }
        });

    }

    private void clickReservar() {

        buttonReserva = findViewById(R.id.buttonReserva);

        buttonReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    private void getDetalhesSala(int pos) {


        nomeSala.setText(salas.get(pos).getNomeSala());
        textTamanhoSala.setText("Dimensão: " + salas.get(pos).getDimensaoSala());
        textCapacidade.setText("Capacidade: " + salas.get(pos).getCapacidade());

        /*/check/*/
        String arcondiconadoCondicao = salas.get(pos).getArCondicionado();
        String multimidia = salas.get(pos).getMultimidia();

        System.out.println(arcondiconadoCondicao);
        System.out.println(multimidia);

        check(arcondiconadoCondicao, iconCheckArCondicionado);
        check(multimidia, iconCheckMultimidia);

    }

    private void check(String checkVerifica, ImageView iconCheck) {

        if (checkVerifica.equals("true")) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_check_true);
            iconCheck.setImageDrawable(drawable);
            iconCheck.setVisibility(View.VISIBLE);

        } else if (checkVerifica.equals("false")) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_check_false);
            iconCheck.setImageDrawable(drawable);
            iconCheck.setVisibility(View.VISIBLE);

        }

    }


}
