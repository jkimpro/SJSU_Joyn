package com.example.junhyuk.sjsu_client;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    // LoginActivity login;
    EditText email, pw;
    String url = "http://192.168.137.1:7777/signIn.php?flag=local";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // login = new LoginActivity();

        email = findViewById(R.id.emailInput);
        pw = findViewById(R.id.pwInput);

        findViewById(R.id.loginBtn).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /* Send Data to Server */
                        url += "&email=" + email.getText() + "&pw=" + pw.getText();
                        String resp = null;
                        try {
                            resp = new ServerConnect().execute(url).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        /* Check Server Response */
                        Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();

                        /* Login Success */

                        /* Login Failed */
                    }
                }
        );

        /* Sign Up Button Click Event : Page Redirect*/
        findViewById(R.id.joinBtn).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent_act = new Intent(getApplicationContext(), SignUpActivity.class);
                        startActivity(intent_act);
                    }
                }
        );
    }
}
