package com.example.myapplication.activity.projetocontrolesalas.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.ui.Login;


public class PerfilFragment extends Fragment {

    private Button buttonAtualizar, buttonSair;
    private ImageView imageViewEscuro, imageViewClaro;
    private TextView textViewNomeEmpresa, textViewNomeUser, textViewEmailUser;
    private View view;
    private SharedPreferences preferences;
    public static final String userPreferences = "userPreferences";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_user, container, false);

        iniciaComponentes();

        return view;
    }

    public void iniciaComponentes() {

        imageViewEscuro = (ImageView) view.findViewById(R.id.icon_theme_escuro);
        imageViewClaro = (ImageView) view.findViewById(R.id.icon_theme_claro);
        buttonAtualizar = (Button) view.findViewById(R.id.buttonAtualizar);
        buttonSair = (Button) view.findViewById(R.id.buttonSair);
        textViewNomeEmpresa = (TextView) view.findViewById(R.id.textSala);
        textViewNomeUser = (TextView) view.findViewById(R.id.textViewNome);
        textViewEmailUser = (TextView) view.findViewById(R.id.textViewEmail);


        changeTheme();
        atualizarConta();
        logout();
        inserirDados();

    }

    private void inserirDados() {

        preferences = getActivity().getSharedPreferences(userPreferences, Context.MODE_PRIVATE);

        if (preferences.contains("userEmail") && preferences.contains("userIdOrganizacao")) {

            String nomeUser = preferences.getString("userName", null);
            String emailUser = preferences.getString("userEmail", null);
            String nomeEmpresa = preferences.getString("userNomeEmpresa", null);
            String tipoEmpresa = preferences.getString("userTipoEmpresa", null);

            System.out.println(tipoEmpresa);

            if (tipoEmpresa.equals("M")) {
                tipoEmpresa = "Matriz";

            } else if (tipoEmpresa.equals("F")) {
                tipoEmpresa = "Filial";

            }


            textViewNomeUser.setText("Nome: " + nomeUser);
            textViewEmailUser.setText("Email: " + emailUser);
            textViewNomeEmpresa.setText(nomeEmpresa.concat(" "+ tipoEmpresa));

        } else {
            textViewNomeUser.setText("Convidado");
            textViewEmailUser.setText("Email");
            textViewNomeEmpresa.setText("Empresa associada");
        }
    }

    private void atualizarConta() {
        buttonAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Click buttonSalvar", Toast.LENGTH_LONG).show();


            }
        });
    }

    private void logout() {

        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerCredenciais();
                startClass(Login.class);
            }
        });

    }

    private void removerCredenciais() {
        SharedPreferences preferences = getActivity().getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("userEmail");
        editor.commit();

    }

    private void changeTheme() {

        imageViewEscuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Click modo Noite", Toast.LENGTH_LONG).show();

                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }

            }
        });

        imageViewClaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.getContext().setTheme(AppCompatDelegate.MODE_NIGHT_NO);
                Toast.makeText(getContext(), "Click modo Dia", Toast.LENGTH_LONG).show();

                if (AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }

            }
        });
    }

    private void startClass(Class classe) {
        Intent intent = new Intent(getContext(), classe);
        startActivity(intent);
        getActivity().finish();
    }


}