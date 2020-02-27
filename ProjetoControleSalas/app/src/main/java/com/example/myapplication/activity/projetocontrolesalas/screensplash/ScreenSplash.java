package com.example.myapplication.activity.projetocontrolesalas.screensplash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.myapplication.activity.projetocontrolesalas.R;
import com.example.myapplication.activity.projetocontrolesalas.ui.Login;

public class ScreenSplash extends Activity {

    //Define tempo que a tela vai exibir. (tempo em milisegundos)
    private static int SPLASH_TIME_OUT = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screensplash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //Método que será executado uma vez.. Na abertura do app.
                Intent i = new Intent(ScreenSplash.this, Login.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}