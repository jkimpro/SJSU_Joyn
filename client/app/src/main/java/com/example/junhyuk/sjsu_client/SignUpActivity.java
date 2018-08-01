package com.example.junhyuk.sjsu_client;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SignUpActivity extends AppCompatActivity {

    private String url = "http://seslab.sejong.ac.kr:7777/signUp.php";
    private EditText fname, lname, email, pw, pwChk, date, year;
    private Spinner monthSpinner;
    private boolean flag;
    private int currentApiVersion;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup);

        /* Sign Up Status */
        flag = false;

        /* Set Focus Order */
        findViewById(R.id.fnameInput).setNextFocusDownId(R.id.lnameInput);
        findViewById(R.id.dateInput).setNextFocusDownId(R.id.yearInput);

        hideBar();
        getToolbar();

        monthSpinner = findViewById(R.id.monthSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.month, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        findViewById(R.id.applyBtn).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /* Get Data from input */
                        fname = findViewById(R.id.fnameInput);
                        lname = findViewById(R.id.lnameInput);

                        email = findViewById(R.id.aplyEmailInput);
                        pw = findViewById(R.id.aplyPwInput);
                        pwChk = findViewById(R.id.pwChkInput);
                        year = findViewById(R.id.yearInput);
                        date = findViewById(R.id.dateInput);

                        /* Check for input data */
                        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                            Toast.makeText(getApplicationContext(), "Email Invalid", Toast.LENGTH_SHORT).show();
                        } else if (!pw.getText().toString().equals(pwChk.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Password Miss Match", Toast.LENGTH_SHORT).show();
                        } else if (Integer.parseInt(year.getText().toString()) < 1900) {
                            Toast.makeText(getApplicationContext(), "Year must over 1900", Toast.LENGTH_SHORT).show();
                        } else {

                            /* Send Data to Server */
                            String reqUrl = url + "?flag=local";
                            reqUrl += "&fname=" + fname.getText();
                            reqUrl += "&lname=" + lname.getText();
                            reqUrl += "&email=" + email.getText();
                            reqUrl += "&pw=" + pw.getText();
                            reqUrl += "&birth=" + year.getText();
                            reqUrl += String.format("-%02d", monthSpinner.getSelectedItemPosition() + 1);
                            reqUrl += String.format("-%02d", Integer.parseInt(date.getText().toString()));

                            String resp = null;
                            JSONObject json = null;
                            try {
                                resp = new ServerConnect().execute(reqUrl).get();
                                json = new JSONObject(resp);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            /* Check Server Response */
//                            Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
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
                                    case 3: // User Already Exists
                                        Toast.makeText(SignUpActivity.this, "User Already Exists.", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            } else {
                                /* Join Success */
                                Toast.makeText(SignUpActivity.this, "Sign Up Succeed. Please Login.", Toast.LENGTH_SHORT).show();
                                flag = true;
                                finish();
                            }
                        }
                    }
                }
        );
    }

    public void hideBar() {
        //상하단바 없애기
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        currentApiVersion = android.os.Build.VERSION.SDK_INT;
        // This work only for android 4.4+
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hideㅁ
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

    public void getToolbar(){
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        actionBar = getSupportActionBar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp); //뒤로가기 버튼을 본인이 만든 아이콘으로 하기 위해 필요
        actionBar.setTitle(null);
    }
    @Override
    public void finish() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();

        if (email == null) {
            email = findViewById(R.id.aplyEmailInput);
        }

        bundle.putString("email", email.getText().toString());
        bundle.putBoolean("flag", flag);
        intent.putExtras(bundle);
        setResult(AppCompatActivity.RESULT_OK, intent);
        super.finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
