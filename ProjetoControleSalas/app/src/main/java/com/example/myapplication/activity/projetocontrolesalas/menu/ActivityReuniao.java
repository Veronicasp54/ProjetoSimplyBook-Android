package com.example.myapplication.activity.projetocontrolesalas.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.model.Empresa;
import com.example.myapplication.activity.projetocontrolesalas.services.RequestSalas;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityReuniao extends AppCompatActivity {

    private Spinner spinnerSalas;
    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";
    private List<String> listaNomesSalas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box_reservar);

        getSupportActionBar().setTitle("Marcar Reunião");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        iniciarComponentes();

    }

    private void iniciarComponentes() {

        spinnerSalas = findViewById(R.id.spinnerSalas);

        buscarListSalas();
        createSpinner();

    }


    private void buscarListSalas() {
        preferences = getSharedPreferences(userPreferences, Context.MODE_PRIVATE);

        String requestSalas = null;

        try {
            requestSalas = new RequestSalas().execute(preferences.getString("userIdEmpresa", null)).get();
            System.out.println("salas em string: " + requestSalas);


            if (requestSalas.length() > 0) {

                JSONArray jsonArray = new JSONArray(requestSalas);

                if (jsonArray.length() > 0) {

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        if (jsonObject.has("nome")) {

                            String nome = jsonObject.getString("nome");

                            Empresa newEmpresa = new Empresa();

                            newEmpresa.setNomeEmpresa(nome);

                            listaNomesSalas.add(newEmpresa.getNomeEmpresa());

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaNomesSalas);

                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            spinnerSalas.setAdapter(adapter);
                            spinnerSalas.setVisibility(View.VISIBLE);

                        }


                    }


                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void createSpinner() {
        spinnerSalas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //idSalaSelecionada = listaSalas.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }
}
