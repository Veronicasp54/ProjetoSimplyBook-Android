package com.example.myapplication.activity.projetocontrolesalas.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication.activity.projetocontrolesalas.R;

public class Cadastro extends AppCompatActivity {

    private EditText editTextEmail, editTextNome, editTextSenha, editTextSenhaConfirmar;
    private Button buttonCadastrar;
    private ImageButton imageButtonBack;

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
        buttonCadastrar =  findViewById(R.id.btnCadastro);
        buttonCadastrar =  findViewById(R.id.btnCadastro);
        imageButtonBack = findViewById(R.id.imageButtonBack);

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClass(Login.class);
            }
        });

        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClass(Login.class);
            }
        });

    }

    private void startClass(Class classe) {
        Intent intent = new Intent(this, classe);
        startActivity(intent);
        this.finish();

    }

}
