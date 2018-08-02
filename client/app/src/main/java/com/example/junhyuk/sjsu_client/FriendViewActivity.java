package com.example.junhyuk.sjsu_client;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class FriendViewActivity extends AppCompatActivity {

    private ImageView imageview = null;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_friend_view);

        imageview = (ImageView)findViewById(R.id.friendImage);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.joined_room1, options);
        ImageView imageView = (ImageView)findViewById(R.id.back);
        imageView.setImageBitmap(bitmapImage);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String testValue = bundle.getString("TEST_KEY");

        if(testValue.equals("1"))
        {
            imageview.setImageResource(R.drawable.kihong);
        }
        else if(testValue.equals("2"))
        {
            imageview.setImageResource(R.drawable.kyung);
        }
        else if(testValue.equals("3"))
        {
            imageview.setImageResource(R.drawable.junhyuk);
        }
    }
}
