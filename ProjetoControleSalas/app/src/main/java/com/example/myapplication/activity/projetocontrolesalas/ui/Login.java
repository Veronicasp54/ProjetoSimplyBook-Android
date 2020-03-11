package com.example.myapplication.activity.projetocontrolesalas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.menu.MainActivity;
import com.example.myapplication.activity.projetocontrolesalas.model.Empresa;
import com.example.myapplication.activity.projetocontrolesalas.model.Usuario;
import com.example.myapplication.activity.projetocontrolesalas.services.RequestVerificadorLogin;

import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonLogin;
    private TextView textViewCadastro;
    private ImageButton btnModoConvidado;

    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences(userPreferences, Context.MODE_PRIVATE);

        if (preferences.contains("userEmail")) {
            startClass(MainActivity.class);
        }

        setContentView(R.layout.activity_login);

        iniciarComponentes();
    }

    private void iniciarComponentes() {

        editTextEmail = findViewById(R.id.email_entrar);
        editTextSenha = findViewById(R.id.senha_entrar);

        buttonLogin = findViewById(R.id.btnLogin);
        textViewCadastro = findViewById(R.id.realizar_cadastro);

        btnModoConvidado = findViewById(R.id.modoConvidado);

        startCadastro();
        logar();
        modoConvidado();


    }

    private void logar() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String emailStr = editTextEmail.getText().toString().trim();
                String senhaStr = editTextSenha.getText().toString().trim();

                if (verificarDados() == true) {

                    try {
                        String authReturn = new RequestVerificadorLogin().execute(emailStr, senhaStr).get();

                        //Toast.makeText(getApplicationContext(),authReturn,Toast.LENGTH_LONG).show();

                        if (authReturn.length() > 0) {
                            Toast.makeText(getApplicationContext(), "Login realizado com sucesso", Toast.LENGTH_LONG).show();

                            JSONObject usuarioJSON = new JSONObject(authReturn);

                            if (usuarioJSON.has("email") && usuarioJSON.has("id") && usuarioJSON.has("nome") && usuarioJSON.has("idOrganizacao")) {
                                int id = usuarioJSON.getInt("id");
                                String nome = usuarioJSON.getString("nome");
                                String email = usuarioJSON.getString("email");

                                JSONObject organizacao = usuarioJSON.getJSONObject("idOrganizacao");
                                String nomeOrganizacao = organizacao.getString("nome");
                                String tipoOrganizacao = organizacao.getString("tipoOrganizacao");
                                int idOrganizacao = organizacao.getInt("id");


                                Usuario usuarioAuth = new Usuario();
                                usuarioAuth.setId(id);
                                usuarioAuth.setNomeUser(nome);
                                usuarioAuth.setEmailUser(email);
                                usuarioAuth.setIdEmpresa(idOrganizacao);

                                Empresa empresa = new Empresa();
                                empresa.setId(idOrganizacao);
                                empresa.setNomeEmpresa(nomeOrganizacao);
                                empresa.setTipoEmpresa(tipoOrganizacao);

                                salvarCredenciais(usuarioAuth, empresa);
                            }
                            System.out.println(preferences.getString("userEmail", null));
                            System.out.println(preferences.getString("userName", null));
                            System.out.println(preferences.getString("userId", null));
                            System.out.println(preferences.getString("userIdOrganizacao", null));
                            System.out.println(preferences.getString("userNomeEmpresa", null));
                            System.out.println(preferences.getString("userTipoEmpresa", null));
                            System.out.println(preferences.getString("userIdEmpresa", null));

                            startClass(MainActivity.class);
                        } else {
                            Toast.makeText(getApplicationContext(), "Login inválido!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), " inválido", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                } else if (senhaStr.equals(null)) {
                    Toast.makeText(getApplicationContext(), "Senha incorreta", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Campos inválidos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void salvarCredenciais(Usuario u, Empresa e) {

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("userEmail", u.getEmailUser());
        editor.putString("userName", u.getNomeUser());
        editor.putString("userId", Integer.toString(u.getId()));
        editor.putString("userIdOrganizacao", Integer.toString(u.getIdEmpresa()));
        editor.putString("userNomeEmpresa", e.getNomeEmpresa());
        editor.putString("userTipoEmpresa", e.getTipoEmpresa());
        editor.putString("userIdEmpresa", Integer.toString(e.getId()));

        editor.commit();

    }


    private void modoConvidado() {

        btnModoConvidado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
        alertDialog.setTitle(R.string.modo_convidado);

        alertDialog.setMessage("Você está acessando o modo de convidado!" +
                " Neste modo você apenas terá a vizualização da aplicação. " +
                "Ou seja, não será disponibilizada sua usabilidade.");

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Confirmar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startClass(MainActivity.class);
                    }
                });

        alertDialog.show();

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
            editTextSenha.setError("Insira uma senha válida");
            chave = false;
        }

        return chave;
    }


}



