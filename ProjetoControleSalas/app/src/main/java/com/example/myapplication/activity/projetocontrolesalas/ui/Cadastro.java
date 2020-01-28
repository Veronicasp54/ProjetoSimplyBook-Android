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
import android.widget.Toast;

import com.example.myapplication.activity.projetocontrolesalas.R;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
        buttonCadastrar = findViewById(R.id.btnCadastro);
        imageButtonBack = findViewById(R.id.imageButtonBack);

        cadastrar();

        voltarTela();


    }


    private void cadastrar() {
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startClass(Login.class);
            }
        });

    }

    private void voltarTela() {
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

    public boolean verificarDados() {
        boolean chave = true;

        if (editTextEmail.getText().toString().trim().isEmpty() || !editTextEmail.getText().toString().trim().contains("@")
                || !editTextEmail.getText().toString().trim().contains(".")) {
            editTextEmail.setError("Insira um e-mail válido!");
            chave = false;
        }

        if (editTextEmail.getText().toString().trim().length() < 8) {
            editTextEmail.setError("A senha deve ter no mínimo 8 caracteres!");
            chave = false;
        }

        return chave;
    }

    public String criptografarSenha(String senha) {
        String criptografada = null;
        try {
            byte[] bytesSenha = senha.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] criptografado = md.digest(bytesSenha);

            criptografada = new String(criptografado, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            mostrarMensagem("Codificaçõ não suportada!");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            mostrarMensagem("Erro!");
        }

        return criptografada;
    }

    public void mostrarMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    public void getEmpresa(){

    }

}
