package com.example.junhyuk.sjsu_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // LoginActivity login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // login = new LoginActivity();

        /* Sign Up Button Click Event : Page Redirect*/
        findViewById(R.id.joinBtn).setOnClickListener(
            new Button.OnClickListener() {
                public void onClick(View v) {
                    Intent intent_act = new Intent(getApplicationContext(), SignUpActivity.class);
                    startActivity(intent_act);
                }
            }
        );
    }
}
