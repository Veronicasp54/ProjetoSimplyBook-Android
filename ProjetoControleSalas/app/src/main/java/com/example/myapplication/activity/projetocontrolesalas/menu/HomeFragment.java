package com.example.myapplication.activity.projetocontrolesalas.menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.activity.projetocontrolesalas.R;

public class HomeFragment extends Fragment {

    private View view;
    private TextView textNomeEmpresa;
    private ListView listSalas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);


        return view;

    }

    public void iniciaCampos() {
        textNomeEmpresa = view.findViewById(R.id.textViewNomeEmpresa);
        listSalas = view.findViewById(R.id.lista_salas_listview);

        listDetails();

    }

    private void listDetails() {


    }

    private void showDialogDetalhes() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.box_reservar_sala, null));

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
