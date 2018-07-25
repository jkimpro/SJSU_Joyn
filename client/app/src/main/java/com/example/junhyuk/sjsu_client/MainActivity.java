package com.example.junhyuk.sjsu_client;

<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
=======
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
>>>>>>> master
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    private Button btnClosePopup;
    private Button btnLogin;
    private PopupWindow pwindo;
    private int mWidthPixels, mHeightPixels;
=======
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    // LoginActivity login;
    EditText email, pw;
    String url = "http://192.168.137.1:7777/signIn.php?flag=local";
>>>>>>> master

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.emailInput);
        pw = findViewById(R.id.pwInput);

        findViewById(R.id.loginBtn).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /* Send Data to Server */
                        url += "&email=" + email.getText() + "&pw=" + pw.getText();
                        String resp = null;
                        try {
                            resp = new ServerConnect().execute(url).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        /* Check Server Response */
                        Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();

                        /* Login Success */

                        /* Login Failed */
                    }
                }
        );

        /* Sign Up Button Click Event : Page Redirect*/
        findViewById(R.id.joinBtn).setOnClickListener(
                new Button.OnClickListener() {
<<<<<<< HEAD
=======
                    @Override
>>>>>>> master
                    public void onClick(View v) {
                        Intent intent_act = new Intent(getApplicationContext(), SignUpActivity.class);
                        startActivity(intent_act);
                    }
                }
        );

        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        // since SDK_INT = 1;
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;

        // 상태바와 메뉴바의 크기를 포함해서 재계산
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try {
                mWidthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                mHeightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
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
            public void onClick(View v) {
                initiatePopupWindow();
            }
        });
    }

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
                    pwindo.dismiss();
                }
            };
}