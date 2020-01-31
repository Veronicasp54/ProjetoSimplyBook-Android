package com.example.myapplication.activity.projetocontrolesalas.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.model.Empresa;
import com.example.myapplication.activity.projetocontrolesalas.services.VerificadorCadastro;
import com.example.myapplication.activity.projetocontrolesalas.services.VerificadorEmpresa;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Cadastro extends AppCompatActivity {

    private EditText editTextEmail, editTextNome, editTextSenha, editTextSenhaConfirmar;
    private Button buttonCadastrar;
    private ImageButton imageButtonBack;
    private Spinner spinnerEmpresa;

    List<Empresa> listaEmpresas = new ArrayList();
    List<String> listaNomesEmpresas = new ArrayList<>();
    int idEmpresaSelecionada;

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
        editTextSenhaConfirmar = findViewById(R.id.senha_confirmar);
        buttonCadastrar = findViewById(R.id.btnCadastro);
        imageButtonBack = findViewById(R.id.imageButtonBack);
        spinnerEmpresa = findViewById(R.id.spinnerEmpresa);

        cadastrar();
        inicializaEmailFocusListener();
        voltarTela();
        createSpinner();

    }


    private void cadastrar() {

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = editTextNome.getText().toString();
                String email = editTextEmail.getText().toString();
                String senha = editTextSenha.getText().toString();
                //int id_organizacao = getIdOrganizacao();


                if (verificarDados() == true) {
                    createJson(email, nome, senha, idEmpresaSelecionada);

                    // startClass(Login.class);


                } else {

                }

            }
        });


    }

    private void createJson(String email, String nome, String senha, int idOrganizacao) {

        JSONObject usuarioJson = new JSONObject();


        try {
            usuarioJson.put("email", email);
            usuarioJson.put("senha", senha);
            usuarioJson.put("nome", nome);
            usuarioJson.put("idOrganizacao", idOrganizacao);


            System.out.println(usuarioJson.toString());

            String userEncoded = Base64.encodeToString(usuarioJson.toString().getBytes("UTF-8"), Base64.NO_WRAP);
            System.out.println(userEncoded);

            String respostaMetodo = new VerificadorCadastro().execute(userEncoded).get();

            if (respostaMetodo.equals("Usuário criado com sucesso")) {

                startClass(Login.class);
            } else {
                Toast.makeText(Cadastro.this, "Campos inválidos!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(
                    Cadastro.this,
                    "Erro ao efetuar cadastro!",
                    Toast.LENGTH_SHORT).show();
        }

    }


    private void inicializaEmailFocusListener() {

        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String emailAfterTextChanged = editTextEmail.getText().toString();

                    if (emailAfterTextChanged.contains("@")) {
                        String[] emailCompleto = emailAfterTextChanged.split("@");
                        if (emailCompleto.length > 1) {
                            String dominio = emailCompleto[1];
                            if (dominio.contains(".")) {
                                System.out.println("dominio: " + dominio);
                                try {
                                    String organizacoesStringFromServer = new VerificadorEmpresa().execute(dominio).get();
                                    System.out.println("Organizações em string: " + organizacoesStringFromServer);

                                    // 1 - verifica se a string nao eh vazia -> exibe erro dizendo q n existe organizacao com o dominio informado


                                    if (organizacoesStringFromServer.length() > 0) {

                                        // 2 - inicializar um jsonarray a partir da string recebida
                                        JSONArray jsonArray = new JSONArray(organizacoesStringFromServer);


                                        // 3 - verifica o length do array > 0
                                        // 4 - se tem coisa no array, pega a posicao do array e inicializa um jsonobject
                                        // 5 - verifica se o jsonObject possui os campos id, nome e tipoOrganizacao
                                        // 6 - se ele possuir esses 3 atributos, entao voce passa pra variaveis
                                        // 7 - criar em tempo de execuao um spinner com os as organizacoes

                                        if (jsonArray.length() > 0) {

                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                                if (jsonObject.has("id") && jsonObject.has("nome") && jsonObject.has("tipoOrganizacao")) {

                                                    int id = jsonObject.getInt("id");
                                                    String nome = jsonObject.getString("nome");
                                                    String tipoEmpresa = jsonObject.getString("tipoOrganizacao");

                                                    Empresa newEmpresa = new Empresa();

                                                    newEmpresa.setId(id);
                                                    newEmpresa.setNomeEmpresa(nome);
                                                    newEmpresa.setTipoEmpresa(tipoEmpresa);

                                                    listaEmpresas.add(newEmpresa);

                                                    listaNomesEmpresas.add(newEmpresa.getNomeEmpresa());

                                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Cadastro.this, android.R.layout.simple_spinner_item, listaNomesEmpresas);

                                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                    spinnerEmpresa.setAdapter(adapter);
                                                    spinnerEmpresa.setVisibility(View.VISIBLE);
                                                    //  createSpinner(listaEmpresas);


                                                }


                                            }


                                        } else {
                                            mostrarMensagem("Erro");

                                        }


                                    } else {
                                        Toast.makeText(getApplicationContext(), "Não pertence a nenhuma organização", Toast.LENGTH_LONG);

                                    }


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }


                        }
                    }
                }

            }
        });


    }

    private void createSpinner() {
        spinnerEmpresa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idEmpresaSelecionada = listaEmpresas.get(position).getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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

        if (editTextSenha.getText().toString().trim().length() < 8) {
            editTextSenha.setError("A senha deve ter no mínimo 8 caracteres!");
            chave = false;
        }


        if (editTextNome.getText().toString().trim().length() <= 0) {
            editTextNome.setError("Campo obrigatório");
            chave = false;

        }

        if (editTextSenhaConfirmar.getText().toString().trim().length() <= 0
                && editTextSenhaConfirmar.getText().toString() != editTextSenha.getText().toString()) {
            chave = false;
        }

        return chave;
    }


    public void mostrarMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
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


}
