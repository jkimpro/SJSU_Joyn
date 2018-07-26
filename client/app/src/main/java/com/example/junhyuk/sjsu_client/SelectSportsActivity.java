package com.example.junhyuk.sjsu_client;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
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
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_sportschioce);
    }
}
