package com.example.myapplication.activity.projetocontrolesalas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.activity.projetocontrolesalas.R;

public class Login extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonLogin;
    private TextView textViewCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarComponentes();
    }

    private void iniciarComponentes() {

        editTextEmail = findViewById(R.id.email_entrar);
        editTextSenha = findViewById(R.id.senha_entrar);
        buttonLogin = findViewById(R.id.btnLogin);
        textViewCadastro = findViewById(R.id.realizar_cadastro);

        textViewCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClass(Cadastro.class);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClass(MainActivity.class);
            }
        });


    }

    private void startClass(Class classe) {
        Intent intent = new Intent(this, classe);
        startActivity(intent);
        this.finish();

    }


}
