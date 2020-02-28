package com.example.myapplication.activity.projetocontrolesalas.menu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.adapter.AdapterListSalas;
import com.example.myapplication.activity.projetocontrolesalas.model.Sala;
import com.example.myapplication.activity.projetocontrolesalas.services.RequestSalas;
import com.example.myapplication.activity.projetocontrolesalas.utils.TinyDB;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SalasFragment extends Fragment {

    private View view;
    private TextView textNomeEmpresa;
    private ListView listSalas;

    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";
    public static final String salaPreferences = "salaPreferences";


    private ArrayList<Sala> salas = new ArrayList<Sala>();
    private List<String> nomeSalas = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private TinyDB tinydb;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_salas, container, false);

        iniciaCampos();


        return view;

    }

    public void iniciaCampos() {
        textNomeEmpresa = view.findViewById(R.id.textViewNomeEmpresa);
        listSalas = view.findViewById(R.id.lista_salas_listview);

         tinydb = new TinyDB(getContext());

        inserirEmpresa();
        inserirSalas();
        clickList();

    }

    private void inserirSalas() {

        String requestSalas = null;
        try {
            requestSalas = new RequestSalas().execute(preferences.getString("userIdEmpresa", null)).get();

            System.out.println(requestSalas);

            JSONArray salasJson = new JSONArray(requestSalas);

            if (salasJson.length() > 0) {

                for (int i = 0; i < salasJson.length(); i++) {
                    JSONObject salaJSon = salasJson.getJSONObject(i);

                    int idSala = salaJSon.getInt("id");
                    String nome = salaJSon.getString("nome");
                    String dimensao = salaJSon.getString("areaDaSala");
                    String capacidade = salaJSon.getString("quantidadePessoasSentadas");
                    String multimidia = salaJSon.getString("possuiMultimidia");
                    String arcondicionado = salaJSon.getString("possuiMultimidia");
                    String localizacao = salaJSon.getString("localizacao");

                    Sala newSala = new Sala();

                    newSala.setNomeSala(nome);

                    newSala.setDimensaoSala(dimensao);
                    newSala.setCapacidade(capacidade);
                    newSala.setLocalizacao(localizacao);
                    newSala.setArCondicionado(arcondicionado);
                    newSala.setMultimidia(multimidia);
                    newSala.setId(idSala);

                    salas.add(newSala);
                    nomeSalas.add(newSala.getNomeSala());

                }

                tinydb.putListSalaObject("salas",salas);

                listSalas = view.findViewById(R.id.lista_salas_listview);
                AdapterListSalas adapter = new AdapterListSalas(salas, getActivity(), getContext());
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

    private void clickList() {
        listSalas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {

                showDialogDetalhesSala(pos);

            }
        });
    }


    private void showDialogDetalhesSala(int pos) {

        preferences = getActivity().getSharedPreferences(salaPreferences, Context.MODE_PRIVATE);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        final View dialogLayout = inflater.inflate(R.layout.dialog_box_sala, null);
        builder.setView(dialogLayout);

        TextView nomeSala = (TextView) dialogLayout.findViewById(R.id.textReuniao);

        TextView textTamanhoSala = (TextView) dialogLayout.findViewById(R.id.dataSelecionada);
        TextView textCapacidade = (TextView) dialogLayout.findViewById(R.id.textCapacidade);

        ImageView iconCheckArCondicionado = dialogLayout.findViewById(R.id.iconCheckArcond);
        ImageView iconCheckMultimidia = dialogLayout.findViewById(R.id.iconCheckMultimidia);

        Sala salaSave = new Sala();
        salaSave.setDimensaoSala(salas.get(pos).getDimensaoSala());
        salaSave.setCapacidade(salas.get(pos).getCapacidade());

        salvarCredenciais(salaSave);


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

        /*/buttonBack/*/
        ImageButton buttonBack = dialogLayout.findViewById(R.id.imageButtonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*/buttonBack/*/
        Button buttonReserva = dialogLayout.findViewById(R.id.buttonSave);

        buttonReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        final AlertDialog dialog = builder.create();

        dialog.show();
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
