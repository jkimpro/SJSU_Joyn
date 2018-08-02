package com.example.junhyuk.sjsu_client;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class JudgePageActivity extends AppCompatActivity {

    private int currentApiVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        //바꿔야됨.
        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.judge, options);
        ImageView imageView = (ImageView)findViewById(R.id.back);
        imageView.setImageBitmap(bitmapImage);

        /*
        findViewById(R.id.nextBt).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(Jud.this, FitnessPageActivity.class);
                startActivity(intent);
            }
        });
        */

    }
}
