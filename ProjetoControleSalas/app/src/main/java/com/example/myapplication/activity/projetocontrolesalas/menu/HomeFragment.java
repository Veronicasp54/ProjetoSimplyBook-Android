package com.example.myapplication.activity.projetocontrolesalas.menu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.model.Sala;
import com.example.myapplication.activity.projetocontrolesalas.model.Usuario;
import com.example.myapplication.activity.projetocontrolesalas.services.RequestSalas;
import com.example.myapplication.activity.projetocontrolesalas.services.RequestVerificadorLogin;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeFragment extends Fragment {

    private View view;
    private TextView textNomeEmpresa;
    private ListView listSalas;
    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";
    List<Sala> salas = new ArrayList<>();
    List<String> nomeSalas = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private Sala sala;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        iniciaCampos();

        return view;

    }

    public void iniciaCampos() {
        textNomeEmpresa = view.findViewById(R.id.textViewNomeEmpresa);


        listDetails();
        inserirEmpresa();
        inserirSalas();

    }

    private void inserirSalas() {

        String requestSalas = null;
        try {
            requestSalas = new RequestSalas().execute(preferences.getString("userIdEmpresa", null)).get();

            System.out.println(requestSalas);
            //JSONObject usuarioJSON = new JSONObject(requestSalas);
            JSONArray salasJson = new JSONArray(requestSalas);

//            List<Sala> salas = new ArrayList<>();

            if (salasJson.length() > 0) {

                for (int i = 0; i < salasJson.length(); i++) {
                    JSONObject salaJSon = salasJson.getJSONObject(i);
                    String nome = salaJSon.getString("nome");

                    Sala sala = new Sala();
                    sala.setNomeSala(nome);

                    salas.add(sala);
                    nomeSalas.add(sala.getNomeSala());


                }
                listSalas = view.findViewById(R.id.lista_salas_listview);
                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, nomeSalas);
                listSalas.setAdapter(adapter);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void inserirEmpresa() {

        preferences = getActivity().getSharedPreferences(userPreferences, Context.MODE_PRIVATE);

        if (preferences.contains("userEmail") && preferences.contains("userIdOrganizacao")) {

            String nomeEmpresa = preferences.getString("userNomeEmpresa", null);
            String tipoEmpresa = preferences.getString("userTipoEmpresa", null);

            System.out.println(tipoEmpresa);

            if (tipoEmpresa.equals("M")) {
                tipoEmpresa = "Matriz";

            } else if (tipoEmpresa.equals("F")) {
                tipoEmpresa = "Filial";
            }

            textNomeEmpresa.setText(nomeEmpresa.concat(" " + tipoEmpresa).toUpperCase());

        } else {

            textNomeEmpresa.setText("Empresa associada");

        }

    }


    private void listDetails() {


    }

    private void showDialogDetalhes() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.box_reservar, null));

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        AlertDialog dialog = builder.create();
    }


}
