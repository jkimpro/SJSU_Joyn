package com.example.junhyuk.sjsu_client;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ConnectServer {
    private String id;
    private String password;

    public ConnectServer(){
    }

    public void signOut(){
    }

    public void connectFacebook() {         //페이스북 연동 부분.
    }

    public boolean signIn() {
        return
    }


    public void setId(String id)
    {
        this.id = id;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
}
