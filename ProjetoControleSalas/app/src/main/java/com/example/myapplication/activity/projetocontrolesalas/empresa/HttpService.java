package com.example.myapplication.activity.projetocontrolesalas.empresa;

import android.os.AsyncTask;

import com.example.myapplication.activity.projetocontrolesalas.model.Empresa;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, Empresa> {

    private final String empresa;

    public HttpService(String empresa) {
        this.empresa = empresa;
    }

    @Override
    protected Empresa doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

        if (this.empresa != null && this.empresa.length() == 8) {
            try {
                URL url = new URL("http://ws.matheuscastiglioni.com.br/ws/cep/find/" + this.empresa + "/json/");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Gson().fromJson(resposta.toString(), Empresa.class);
    }
}