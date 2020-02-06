package com.example.myapplication.activity.projetocontrolesalas.menu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.model.Sala;
import com.example.myapplication.activity.projetocontrolesalas.services.RequestSalas;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

        inserirEmpresa();
        inserirSalas();
        clickList();

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

                    salas.add(newSala);
                    nomeSalas.add(newSala.getNomeSala());


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

    private void clickList(){
        listSalas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {

                showDialogDetalhes(pos);
            }
        });
    }

    private void showDialogDetalhes(int pos) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View dialogLayout = inflater.inflate(R.layout.dialog_box_sala, null);

        builder.setView(dialogLayout);

        TextView nomeSala = (TextView) dialogLayout.findViewById(R.id.textNomeSala);

        TextView textTamanhoSala = (TextView) dialogLayout.findViewById(R.id.textTamanho);
        TextView textCapacidade = (TextView) dialogLayout.findViewById(R.id.textCapacidade);
        TextView booleanArcond = (TextView) dialogLayout.findViewById(R.id.textArCondicionado);
        TextView booleanMultimidia = (TextView) dialogLayout.findViewById(R.id.textMultimidia);

        nomeSala.setText(salas.get(pos).getNomeSala());
        textTamanhoSala.setText(salas.get(pos).getDimensaoSala());
        textCapacidade.setText(salas.get(pos).getCapacidade());
        booleanArcond.setText(salas.get(pos).getArCondicionado());
        booleanMultimidia.setText(salas.get(pos).getArCondicionado());

        AlertDialog dialog = builder.create();
    }


}
