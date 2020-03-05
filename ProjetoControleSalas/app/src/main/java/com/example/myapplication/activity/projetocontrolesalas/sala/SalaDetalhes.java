package com.example.myapplication.activity.projetocontrolesalas.sala;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.menu.ActivityReserva;
import com.example.myapplication.activity.projetocontrolesalas.model.Sala;
import com.example.myapplication.activity.projetocontrolesalas.utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

public class SalaDetalhes extends AppCompatActivity {

    private TextView textNomeSala, textTamanhoSala, textCapacidade;
    private List<Sala> salas = new ArrayList<>();
    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";
    private ImageView iconCheckArCondicionado, iconCheckMultimidia;
    private Button btnReservar;

    private TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_box_sala);

        getSupportActionBar().setTitle("Detalhes da Sala");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        iniciarComponentes();

    }

    private void iniciarComponentes() {

        textNomeSala = findViewById(R.id.textReuniao);
        textTamanhoSala = findViewById(R.id.dataSelecionada);
        textCapacidade = findViewById(R.id.textCapacidade);
        iconCheckArCondicionado = findViewById(R.id.iconCheckArcond);
        iconCheckMultimidia = findViewById(R.id.iconCheckMultimidia);
        btnReservar = findViewById(R.id.buttonSave);

        tinyDB = new TinyDB(getApplicationContext());
        salas = tinyDB.getListSalaObject("salas");

        inserirDados();
        realizarReserva();
    }

    private void realizarReserva() {
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityReserva.class);

                String nomeSalaParam = textNomeSala.toString();
                int pos = getIntent().getIntExtra("positionSala", 0);

                intent.putExtra("nomeSala", nomeSalaParam);
                intent.putExtra("idSala", pos + 1);

                startActivity(intent);
            }
        });
    }


    private void inserirDados() {

        int pos = getIntent().getIntExtra("positionSala", 0);

        preferences = getSharedPreferences(userPreferences, Context.MODE_PRIVATE);

        if (preferences.contains("userEmail") && preferences.contains("userIdOrganizacao")) {

            String nomeEmpresa = preferences.getString("userNomeEmpresa", null);
            String tipoEmpresa = preferences.getString("userTipoEmpresa", null);

            System.out.println(tipoEmpresa);

            if (tipoEmpresa.equals("M")) {
                tipoEmpresa = "Matriz";

            } else if (tipoEmpresa.equals("F")) {
                tipoEmpresa = "Filial";

            }


            textNomeSala.setText(nomeEmpresa.concat(" " + tipoEmpresa));
            textTamanhoSala.setText("");
            textCapacidade.setText("");


            Sala salaSave = new Sala();

            try {
                salaSave.setDimensaoSala(salas.get(pos).getDimensaoSala());
                salaSave.setCapacidade(salas.get(pos).getCapacidade());
                salvarCredenciais(salaSave);

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
            }


            textNomeSala.setText(salas.get(pos).getNomeSala());
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
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;

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

    private void salvarCredenciais(Sala sala) {

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("salaDimensao", sala.getDimensaoSala());
        editor.putString("salaCapacidade", sala.getCapacidade());

        editor.commit();

    }
}