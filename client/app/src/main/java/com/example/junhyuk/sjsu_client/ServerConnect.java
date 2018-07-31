package com.example.junhyuk.sjsu_client;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ServerConnect extends AsyncTask<String, Integer, String>{
    @Override
    protected String doInBackground(String[] urls) {
        String url = urls[0];
        String result = null;
        String inputLine;

        try {

            URL myUrl = new URL(url);

            HttpURLConnection conn = (HttpURLConnection)myUrl.openConnection();

            conn.setRequestMethod("GET");
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.connect();

            InputStreamReader isr = new InputStreamReader(conn.getInputStream());

            BufferedReader reader = new BufferedReader(isr);
            StringBuilder stringBuilder = new StringBuilder();

            while((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            reader.close();
            isr.close();
            result = stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
    }
}
