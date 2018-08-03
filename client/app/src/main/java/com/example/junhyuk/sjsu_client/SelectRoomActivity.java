package com.example.junhyuk.sjsu_client;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class SelectRoomActivity extends AppCompatActivity {


    private int currentApiVersion;
    private boolean isJoined;
    private ImageButton imageButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        isJoined = false;
        imageButton = findViewById(R.id.joinBt);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectroom);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.select_room8, options);

        ImageView imageView = (ImageView)findViewById(R.id.back);
        imageView.setImageBitmap(bitmapImage);


        //join -> joined , joined -> join 바뀌는것.
        findViewById(R.id.joinBt).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(SelectRoomActivity.this, FriendListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //화면전환
        findViewById(R.id.joinCloseBt).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SelectRoomActivity.this, HostGameInitActivity.class);
                startActivity(intent);
                finish();
            }
        });


        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT)
        {
            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                    {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility)
                        {
                            if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                            {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    //친구창 열리는 Listener
    Button.OnClickListener friendButtonListener =
            new Button.OnClickListener(){
                public void onClick(View v) {
                    Intent intent = new Intent(SelectRoomActivity.this, FriendViewActivity.class);
                    startActivity(intent);
                }
            };
}
