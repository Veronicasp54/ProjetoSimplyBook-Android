package com.example.myapplication.activity.projetocontrolesalas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.activity.projetocontrolesalas.R;

public class Cadastro extends AppCompatActivity {

    private EditText editTextEmail, editTextNome, editTextSenha, editTextSenhaConfirmar;
    private Button buttonCadastrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        iniciarComponentes();
    }

    private void iniciarComponentes() {

        editTextEmail = findViewById(R.id.email_cadastrar);
        editTextNome = findViewById(R.id.nome_cadastrar);
        editTextSenha = findViewById(R.id.senha_cadastrar);


    }

}
