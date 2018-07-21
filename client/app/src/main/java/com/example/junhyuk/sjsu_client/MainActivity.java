package com.example.junhyuk.sjsu_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

    public class MainActivity extends AppCompatActivity {

        LoginActivity login;

        @Override
        protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.loginActivity);
            login = new LoginActivity();



            //setContentView(R.layout.activity_main);
        }

    }
