package com.exemple.android.myapplication;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;


public class JSONActivity extends AppCompatActivity {

    ImageView mImageView;
    TextView textView;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_activity);

        Google google = new Google();
        google.execute();

        mImageView = (ImageView) findViewById(R.id.Json_imageView);
        textView = (TextView) findViewById(R.id.Json);
    }

    public Bitmap getBitmapFromUrl(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection conection = (HttpURLConnection) url.openConnection();
            conection.setDoInput(true);
            conection.connect();
            InputStream input = conection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    class Google extends AsyncTask<Void, Void, Card> {

        public String LOG_TAG = "my_log";
        private String login;
        private String avatar;
        public String urlToPassGit = MainActivity.getUrlToPassGit();
        public String urlToPassGoogle = MainActivity.getUrlToPassGoogle();
        public String urlToPass = MainActivity.getUrlToPass();

        public String getAvatar() {
            return avatar;
        }

        public String getLogin() {
            return login;

        }

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected Card doInBackground(Void... params) {
            try {
                URL url = new URL(MainActivity.getUrlToPass());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();


            } catch (Exception e) {
                e.printStackTrace();
            }
            if (urlToPass.equals(urlToPassGit)) {

                try {
                    JSONObject dataJsonObj = new JSONObject(resultJson);

                    login = dataJsonObj.getString("login");
                    avatar = dataJsonObj.getString("avatar_url");
                    Log.d(LOG_TAG, "Имя: " + login);
                    Log.d(LOG_TAG, "avatar: " + avatar);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    JSONObject dataJsonObj = new JSONObject(resultJson);
                    JSONArray name = dataJsonObj.getJSONArray("name");

                    login = name.getString(1);
                    avatar = name.getString(2);
                    Log.d(LOG_TAG, "Имя: " + login);
                    Log.d(LOG_TAG, "avatar: " + avatar);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return new Card(login, getBitmapFromUrl(avatar));
        }

        @Override
        protected void onPostExecute(Card card) {
            super.onPostExecute(card);
            textView.setText(card.getLogin());
            mImageView.setImageBitmap(card.getAvatar());
        }

    }
}