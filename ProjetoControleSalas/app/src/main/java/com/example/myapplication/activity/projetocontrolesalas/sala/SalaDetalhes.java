package com.example.myapplication.activity.projetocontrolesalas.sala;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.model.Sala;
import com.example.myapplication.activity.projetocontrolesalas.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class SalaDetalhes extends AppCompatActivity {

    private TextView textNomeSala;
    private TextView textTamanhoSala;
    private TextView textCapacidade;
    private TextView textArCondicionado;
    private TextView textMultimidia;
    private List<Sala> salas = new ArrayList<>();
    private ImageButton buttonBack;
    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_box_sala);


        iniciarComponentes();

    }

    private void iniciarComponentes() {

        textNomeSala = findViewById(R.id.textReuniao);
        textTamanhoSala = findViewById(R.id.dataSelecionada);
        textCapacidade = findViewById(R.id.textCapacidade);
        textArCondicionado = findViewById(R.id.textAdmReuniao);
        textMultimidia = findViewById(R.id.textMultimidia);
        buttonBack = findViewById(R.id.imageButtonBack);

        buttonBack();
        inserirDados();

    }

    private void buttonBack() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClass(MainActivity.class);

            }
        });

    }

    private void inserirDados() {

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


            textNomeSala.setText(nomeEmpresa.concat(" "+ tipoEmpresa));
            textTamanhoSala.setText("");
            textArCondicionado.setText("");
            textCapacidade.setText("");
            textMultimidia.setText("");


        }
    }

    private void startClass(Class classe) {
        Intent intent = new Intent(this, classe);
        startActivity(intent);
        this.finish();

    }

}