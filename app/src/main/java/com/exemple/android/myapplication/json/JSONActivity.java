package com.exemple.android.myapplication.json;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.exemple.android.myapplication.listview.ListViewActivity;
import com.exemple.android.myapplication.recycler.MainActivity;
import com.exemple.android.myapplication.R;

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

        private String login;
        private String avatar;
        private String urlToPassGit = MainActivity.getUrlToPassGit();
        private String urlToPassGoogle = MainActivity.getUrlToPassGoogle();
        private String urlToPass = MainActivity.getUrlToPass();
        private String urlToPassList = ListViewActivity.getUrlToPassList();
        private String urlToPassGitList = ListViewActivity.getUrlToPassGitList();
        private String urlToPassGoogleList = ListViewActivity.getUrlToPassGoogleList();
        private String urL = (urlToPass != null) ? urlToPass:urlToPassList;
        private String urlGit = (urlToPassGit != null) ? urlToPassGit:urlToPassGitList;


        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected Card doInBackground(Void... params) {
            try {
                URL url = new URL(urL);

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
            if (urlToPass.equals(urlGit)) {

                try {
                    JSONObject dataJsonObj = new JSONObject(resultJson);

                    login = dataJsonObj.getString("login");
                    avatar = dataJsonObj.getString("avatar_url");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    JSONObject dataJsonObj = new JSONObject(resultJson);
                    JSONObject  name = dataJsonObj.getJSONObject("name");
                    JSONObject  imageData = dataJsonObj.getJSONObject("image");
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