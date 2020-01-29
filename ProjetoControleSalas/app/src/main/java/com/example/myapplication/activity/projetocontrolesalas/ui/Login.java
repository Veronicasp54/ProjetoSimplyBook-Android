package com.example.myapplication.activity.projetocontrolesalas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.services.VerificadorLogin;

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

        startCadastro();
        logar();


    }

    private void logar() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String emailStr = editTextEmail.getText().toString().trim();
                String senhaStr = editTextSenha.getText().toString().trim();


                if (verificarDados() == true) {

                    try {
                        String authReturn = new VerificadorLogin().execute(emailStr, senhaStr).get();


                        //Toast.makeText(getApplicationContext(),authReturn,Toast.LENGTH_LONG).show();

                        if (authReturn.equalsIgnoreCase("Login efetuado com sucesso!")) {

                            Toast.makeText(getApplicationContext(), "Login realizado com sucesso", Toast.LENGTH_LONG).show();
                            startClass(MainActivity.class);

                        } else {
                            Toast.makeText(getApplicationContext(), "Login inválido!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), " inválido", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else {

                }

            }
        });
    }



    private void startCadastro() {
        textViewCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClass(Cadastro.class);
            }
        });

    }


    private void startClass(Class classe) {
        Intent intent = new Intent(this, classe);
        startActivity(intent);
        this.finish();

    }

    public boolean verificarDados() {
        boolean chave = true;

        if (editTextEmail.getText().toString().trim().isEmpty() || !editTextEmail.getText().toString().trim().contains("@")
                || !editTextEmail.getText().toString().trim().contains(".")) {
            editTextEmail.setError("Insira um e-mail válido!");
            chave = false;
        }

        if (editTextSenha.getText().toString().trim().length() < 8) {
            editTextSenha.setError("Insira uma senha");
            chave = false;
        }

        return chave;
    }

}



