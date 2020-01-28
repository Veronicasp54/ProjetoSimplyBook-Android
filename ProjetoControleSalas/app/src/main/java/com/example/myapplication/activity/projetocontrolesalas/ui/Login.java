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
import com.example.myapplication.activity.projetocontrolesalas.services.Verificador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

                try {
                    String authReturn = new Verificador().execute(emailStr, senhaStr).get();



                    //Toast.makeText(getApplicationContext(),authReturn,Toast.LENGTH_LONG).show();

                    if (authReturn.equalsIgnoreCase("Login efetuado com sucesso!")) {
                        Toast.makeText(getApplicationContext(), "Login realizado com sucesso", Toast.LENGTH_LONG).show();
                        startClass(MainActivity.class);

                    } else {
                        Toast.makeText(getApplicationContext(), "Login inválido!", Toast.LENGTH_LONG).show();


                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), " inválido", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });
    }

    public static String autenticarUsuario(String email, String password) throws Exception {

        String urlWS = "http://172.30.248.56:8080/ReservaDeSala/rest/usuario/login/";

        String authorizationHeader = "secret";

        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlWS);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("authorization", authorizationHeader);
            conn.setRequestProperty("email", email);
            conn.setRequestProperty("password", password);

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            System.out.println(result.toString());

            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "";
    }

    private void loginInvalido() {

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
}



