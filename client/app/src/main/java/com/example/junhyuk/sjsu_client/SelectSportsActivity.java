package com.example.junhyuk.sjsu_client;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class SelectSportsActivity extends AppCompatActivity {

    private Button tennis, basketBall, soccer;
    private Button workOut, badminton, jogging;
    private Button yoga, cycling, baseBall;
    private Button bowling, billards, surfing;

    private Button ok, cancel;
    private Button skip;

    private Button upScroll;
    private Button downScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup);

    }

}
