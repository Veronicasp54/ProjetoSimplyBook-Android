package com.example.myapplication.activity.projetocontrolesalas.sala;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.model.Sala;

import java.util.ArrayList;
import java.util.List;

public class SalaDetalhes extends AppCompatActivity {

    private TextView nomeSala;
    private TextView textTamanhoSala;
    private TextView textCapacidade;
    private TextView booleanArcond ;
    private TextView booleanMultimidia;
    private List<Sala> salas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_box_sala);


        iniciarComponentes();

    }

    private void iniciarComponentes() {

        findViewById(R.id.textNomeSala);
        findViewById(R.id.textTamanho);
        findViewById(R.id.textCapacidade);
        findViewById(R.id.textArCondicionado);
        findViewById(R.id.textMultimidia);

        atualizarCampos();

    }

    private void atualizarCampos() {
   ///    nomeSala.setText(salas.get(pos).getNomeSala());
       /// textTamanhoSala.setText(salas.get(pos).getDimensaoSala());
       // textCapacidade.setText(salas.get(pos).getCapacidade());
      //  booleanArcond.setText(salas.get(pos).getArCondicionado());
      //  booleanMultimidia.setText(salas.get(pos).getArCondicionado());
    }
}