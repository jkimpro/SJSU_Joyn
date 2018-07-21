package com.example.junhyuk.sjsu_client;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends MainActivity {

    EditText username, password;
    Button btn;

    boolean isSignIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username = (EditText) findViewById(R.id.emailInput);
        password = (EditText) findViewById(R.id.pwInput);
        btn = (Button) findViewById(R.id.loginBtn);
        btn.setOnClickListener(new Btn_Click(this));

    }

    class Btn_Click implements View.OnClickListener
    { Context ctx;
        Btn_Click(Context ctx)
        { this.ctx=ctx;}

        @Override

        public void onClick(View view) {
            if(view==btn) {
                //String qs = "?username=" + username.getText() + "&password=" + password.getText();

                String id = ""+username.getText();
                String pw = ""+password.getText();
                ConnectServer loginServer = new ConnectServer();
                loginServer.setId(id);
                loginServer.setPassword(pw);

                isSignIn = signIn();

                // 시작 예정
                /*
                CallHttpRequest loginCall = new CallHttpRequest(ctx, "Login");
                // Change this url
                String url[] = {"http://" + ctx.getString(R.string.ip) + ":88/something/somefile" + qs};
                loginCall.execute(url);
                */
            }

        }
    }

}
