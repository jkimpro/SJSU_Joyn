package com.example.junhyuk.sjsu_client;

import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button btnClosePopup;
    private Button btnLogin;
    private PopupWindow pwindo;
    private int mWidthPixels, mHeightPixels;

    // LoginActivity login;
    private EditText email, pw;
    private String url = "http://seslab.sejong.ac.kr:7777/signIn.php?flag=local";
    private JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.emailInput);
        pw = findViewById(R.id.pwInput);

        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        // since SDK_INT = 1;
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;
/* SDK 17 사용으로 필요없는 항목
        // 상태바와 메뉴바의 크기를 포함해서 재계산
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try {
                mWidthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                mHeightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
*/
        // 상태바와 메뉴바의 크기를 포함
        if (Build.VERSION.SDK_INT >= 17)
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                mWidthPixels = realSize.x;
                mHeightPixels = realSize.y;
            } catch (Exception ignored) {
            }

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
                } else {
                    /* login Success */
                    UserProfileVO user = new UserProfileVO();
                    try {
                        user.setUserID(json.getInt("user"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // Display Pop Window
                    initiatePopupWindow();
                }

            }
        });

        /* Sign Up Button Click Event : Page Redirect*/
        findViewById(R.id.joinBtn).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent_act = new Intent(getApplicationContext(), SignUpActivity.class);
                        startActivityForResult(intent_act, RESULT_FIRST_USER);
                    }
                }
        );

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

    //첫번째 팝업 이벤트===================================================================================================
    private void initiatePopupWindow() {
        try {
            //  LayoutInflater 객체와 시킴
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup_get_started, (ViewGroup) findViewById(R.id.popup_element));

            pwindo = new PopupWindow(layout, mWidthPixels - 320, mHeightPixels - 1000, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

            btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);
            btnClosePopup.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Button.OnClickListener cancel_button_click_listener =
            new Button.OnClickListener() {
                public void onClick(View v) {

                    Intent intent_act = new Intent(getApplicationContext(), SelectSportsActivity.class);
                    startActivity(intent_act);
                    /**
                     * TODO: Page direction to select favorite sports
                     */
                    // Select Sports Activity 런칭 ============================================================================================


                }
            };

}