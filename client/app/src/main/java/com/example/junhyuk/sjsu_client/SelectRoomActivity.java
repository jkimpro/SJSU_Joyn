package com.example.junhyuk.sjsu_client;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class SelectRoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.select_room8, options);
        ImageView imageView = (ImageView)findViewById(R.id.back);
        imageView.setImageBitmap(bitmapImage);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectroom);

    }
}
