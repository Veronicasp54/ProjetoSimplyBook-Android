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

import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonLogin;
    private TextView textViewCadastro;
    private String senhaStr, emailStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarComponentes();
    }

    private void iniciarComponentes() {

        editTextEmail = findViewById(R.id.email_entrar);
        editTextSenha = findViewById(R.id.senha_entrar);

         emailStr = editTextEmail.getText().toString();
         senhaStr = editTextSenha.getText().toString();

        buttonLogin = findViewById(R.id.btnLogin);
        textViewCadastro = findViewById(R.id.realizar_cadastro);

        startCadastro();
        logar();


    }

    private void logar() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    autenticarUsuario(emailStr, senhaStr);
                    Toast.makeText(getApplicationContext(),"Login realizado com sucesso", Toast.LENGTH_LONG).show();
                    startClass(MainActivity.class);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Login inválido", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
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

    public static int autenticarUsuario(String email, String password) throws Exception {
        try {
            String wsURL = "http://localhost:8080/ReservaDeSala/rest/usuario/login";
            URL obj = new URL(wsURL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("authorization", "secret");
            con.setConnectTimeout(2000);
            con.setRequestProperty("email", email);
            con.setRequestProperty("password", password);

            int responseCode = con.getResponseCode();
            return responseCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
        }
    }

}



