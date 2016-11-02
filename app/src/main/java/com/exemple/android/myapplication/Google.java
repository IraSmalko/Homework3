package com.exemple.android.myapplication;



import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Google extends AsyncTask<Void, Void, String>{

    public static String LOG_TAG = "my_log";
    private String login;
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public String getLogin() {
        return login;

    }

    public void setMessage(String message) {
        this.message = message;
    }
    HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";
        String message;



        @Override
        protected String doInBackground(Void... params) {

            try {
                URL url = new URL( message);

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
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);

            Log.d(LOG_TAG, strJson);

            JSONObject dataJsonObj = null;

            try {
                dataJsonObj = new JSONObject(strJson);


                JSONObject secondPerson = dataJsonObj.getJSONObject(strJson);
                login = secondPerson.getString("login");
                avatar = secondPerson.getString("avatar_url");
                Log.d(LOG_TAG, "Имя: " + login);
                Log.d(LOG_TAG, "avatar: " + avatar);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    public class JSON extends AppCompatActivity {

        Intent intent = getIntent();
        private String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        public String getMessage() {
            return message;
        }
    }
}