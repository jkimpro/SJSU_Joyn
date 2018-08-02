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

public class RealMainActivity extends AppCompatActivity{

    private static int fStatus;

    private int currentApiVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        fStatus = 0;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.main_page, options);
        ImageView imageView = (ImageView)findViewById(R.id.back);
        imageView.setImageBitmap(bitmapImage);

        findViewById(R.id.joinOpenBt).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(RealMainActivity.this, SelectRoomActivity.class);
                startActivity(intent);
                finish();

            }
        });

        findViewById(R.id.friendImage1).setOnClickListener(friendButtonListener);
        findViewById(R.id.friendImage2).setOnClickListener(friendButtonListener);
        findViewById(R.id.friendImage3).setOnClickListener(friendButtonListener);
        findViewById(R.id.friendImageElse1).setOnClickListener(friendButtonListener);
        findViewById(R.id.friendImageElse2).setOnClickListener(friendButtonListener);

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

    Button.OnClickListener friendButtonListener =
            new Button.OnClickListener(){
                public void onClick(View v) {

                    switch(v.getId())
                    {
                        case R.id.friendImage1: {

                            Intent intent = new Intent(RealMainActivity.this, FriendViewActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("TEST_KEY", "1");
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
                        }
                        case R.id.friendImage2: {

                            Intent intent = new Intent(RealMainActivity.this, FriendViewActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("TEST_KEY", "2");
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
                        }
                        case R.id.friendImage3: {

                            Intent intent = new Intent(RealMainActivity.this, FriendViewActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("TEST_KEY", "3");
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
                        }
                        default:
                        {

                            Intent intent = new Intent(RealMainActivity.this, FriendViewActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("TEST_KEY", "0");
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
                        }
                    }
                }
            };

}
