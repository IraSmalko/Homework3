package com.exemple.android.myapplication;


import android.os.AsyncTask;
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

    HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";


        @Override
        protected String doInBackground(Void... params) {

            try {
                URL url = new URL("https://API.github.com/users/IraSmalko");

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
}