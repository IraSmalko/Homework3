package com.exemple.android.myapplication.json;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.exemple.android.myapplication.R;
import com.exemple.android.myapplication.listview.ListViewActivity;
import com.exemple.android.myapplication.recycler.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class JSONActivity extends AppCompatActivity {

    ImageView mImageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_activity);
        boolean flag = false;

        if (getIntent() != null) {
            Uri data = getIntent().getData();
            if (data != null) {
                String url = data.toString();
                if (url.contains("github.com")) {
                    url = url.replace("github.com", "API.github.com/users");
                    MainActivity.setUrlToPass(url);
                }else if (url.contains("plus.google.com/1")) {
                    url = url.replace("plus.google.com", "www.googleapis.com/plus/v1/people");
                    url = url + "?key=AIzaSyC7xbtBoEVQUwWWcDjENu0Z11mSyTeaijE";
                    MainActivity.setUrlToPass(url);
                }else {
                    flag = true;
                    Toast.makeText(this, "некоректне посилання", Toast.LENGTH_SHORT).show();
                }
            }
        }

        mImageView = (ImageView) findViewById(R.id.Json_imageView);
        textView = (TextView) findViewById(R.id.Json);
        if(!flag){
        JSONmaker jsoNmaker = new JSONmaker();
        jsoNmaker.execute();
        }
    }

    public Bitmap getBitmapFromUrl(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    class JSONmaker extends AsyncTask<Void, Void, Card> {

        @Override
        protected Card doInBackground(Void... params) {
            String passedUrl = MainActivity.getUrlToPass();
            String urlToJSON = (passedUrl != null) ? passedUrl : ListViewActivity.getUrlToPass();
            return parseJSON(urlToJSON);
        }

        public String loadJSON(String urlToJSON) {
            String resultJson = new String();
            try {
                URL url = new URL(urlToJSON);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

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

        public Card parseJSON(String urlToJSON) {
            String login = new String();
            String avatar = new String();
            if (urlToJSON.contains("https://API.github.com")) {
                try {
                    JSONObject dataJsonObj = new JSONObject(loadJSON(urlToJSON));
                    login = dataJsonObj.getString("login");
                    avatar = dataJsonObj.getString("avatar_url");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (urlToJSON.contains("https://www.googleapis.com/plus/v1/people")) {
                try {
                    JSONObject dataJsonObj = new JSONObject(loadJSON(urlToJSON));
                    JSONObject name = dataJsonObj.getJSONObject("name");
                    JSONObject imageData = dataJsonObj.getJSONObject("image");
                    String familyName = name.getString("familyName");
                    String givenName = name.getString("givenName");
                    avatar = imageData.getString("url");
                    login = familyName + " " + givenName;
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