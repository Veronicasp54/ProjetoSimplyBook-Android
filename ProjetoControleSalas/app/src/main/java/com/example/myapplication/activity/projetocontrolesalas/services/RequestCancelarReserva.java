package com.example.myapplication.activity.projetocontrolesalas.services;

import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

public class RequestCancelarReserva extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {

        String urlWS = "http:/172.30.248.134:8080/ReservaDeSala/rest/reserva/cancelar/";

        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlWS);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("authorization", "secret");
            conn.setRequestProperty("id_reserva", strings[0]);
            conn.setConnectTimeout(6000);

            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
