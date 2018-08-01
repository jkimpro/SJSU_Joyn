package com.example.junhyuk.sjsu_client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PopUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        findViewById(R.id.btn_close_popup).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent_act = new Intent(PopUpActivity.this, SelectSportsActivity.class);
                        startActivityForResult(intent_act, RESULT_FIRST_USER);
                    }
                }
        );
    }

}