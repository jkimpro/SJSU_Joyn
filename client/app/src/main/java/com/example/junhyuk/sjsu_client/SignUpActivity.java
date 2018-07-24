package com.example.junhyuk.sjsu_client;

import android.graphics.Color;
import android.media.MediaCodec;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class SignUpActivity extends AppCompatActivity{

    String url = "http://seslab.sejong.ac.kr:7777/signUp.php";
    EditText fname, lname, email, pw, pwChk, date, year;
    Spinner monthSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup);

        monthSpinner = findViewById(R.id.monthSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.month, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
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
                        monthSpinner = findViewById(R.id.monthSpinner);

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
                                UserProfileVO user = new UserProfileVO();
                                try {
                                    user.setUserID(json.getInt("user"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                /** TODO: change page to set favorite sport
                                 *        set user as a parameter for favorite sport setting page
                                 */
                            }
                        }
                    }
                }
        );
    }
}
