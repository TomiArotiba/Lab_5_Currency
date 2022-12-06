package com.example.lab_5_app;

import android.os.AsyncTask;
import android.app.ProgressDialog;
import android.content.Context;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;


public class DataLoader extends AsyncTask<String, Void, ArrayList<String>> {

    protected ArrayList<String> doInBackground(String... params){
        ArrayList<String> rates = new ArrayList<>();
        try {
            InputStream stream = downloadUrl(Constants.url);
            rates = Parser.getRates(stream);
            stream.close();
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        return rates;
    }

    private static InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
}
