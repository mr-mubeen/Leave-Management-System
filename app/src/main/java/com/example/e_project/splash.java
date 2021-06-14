package com.example.e_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.logging.Handler;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(){
            @Override
            public void run() {

                try {
                    sleep(3000);
                }
                catch (Exception e) {
                    e.printStackTrace();

                }
                finally {
                    Intent intent = new Intent(splash.this , MainActivity.class);
                    startActivity(intent);
                }

            }
        };
        thread.start();


    }
}