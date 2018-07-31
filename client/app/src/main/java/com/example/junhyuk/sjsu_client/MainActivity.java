package com.example.junhyuk.sjsu_client;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;

    private int currentApiVersion;

    private EditText email, pw;
    private String url = "http://seslab.sejong.ac.kr:7777/signIn.php?flag=local";
    private JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.emailInput);
        pw = findViewById(R.id.pwInput);

        hideBar();
        putBackground();

        btnLogin = (Button) findViewById(R.id.loginBtn);
        btnLogin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Send Data to Server */
                url += "&email=" + email.getText() + "&pw=" + pw.getText();
                String resp = null;
                try {
                    resp = new ServerConnect().execute(url).get();
                    json = new JSONObject(resp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                /* Check Server Response */
                int status = 1;
                try {
                    status = json.getInt("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (status > 0) {
                    /* Error status code */
                    switch (status) {
                        case 1: // DB Connection fail
                            Toast.makeText(getApplicationContext(), "Server Connection failed. Try Again", Toast.LENGTH_SHORT).show();
                            break;
                        case 4: // User Already Exists
                            Toast.makeText(MainActivity.this, "Login Failed.", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                else {
                    /* login Success */
                    UserProfileVO user = new UserProfileVO();
                    try {
                        user.setUserID(json.getInt("user"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // Display Pop Window
                    Intent intent = new Intent(MainActivity.this, PopUpActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        /* Sign Up Button Click Event : Page Redirect*/
        findViewById(R.id.joinBtn).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent_act = new Intent(MainActivity.this, SignUpActivity.class);
                        startActivityForResult(intent_act, RESULT_FIRST_USER);
                    }
                }
        );
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data.hasExtra("email")) {
                if (data.getBooleanExtra("flag", false)) {
                    email.setText(data.getStringExtra("email").toString());
                    findViewById(R.id.pwInput).setFocusableInTouchMode(true);
                    findViewById(R.id.pwInput).requestFocus();
                }
            }
        }
    }

    public void hideBar() {
        //상하단바 없애기
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        currentApiVersion = android.os.Build.VERSION.SDK_INT;
        // This work only for android 4.4+
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
    }

    public void putBackground(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.backgroundimage2, options);
        ImageView imageView = (ImageView) findViewById(R.id.back);
        imageView.setImageBitmap(bitmapImage);
    }

}
