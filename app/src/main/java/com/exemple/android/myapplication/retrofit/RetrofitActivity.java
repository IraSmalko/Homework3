package com.exemple.android.myapplication.retrofit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.exemple.android.myapplication.R;
import com.exemple.android.myapplication.listview.ListViewActivity;
import com.exemple.android.myapplication.recycler.MainActivity;
import com.exemple.android.myapplication.retrofit.data.GithubUser;
import com.exemple.android.myapplication.retrofit.data.GooglePlusUser;

import retrofit.Callback;
import retrofit.Response;


public class RetrofitActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    String urlToPassGoogle;
    String urlToPassGit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_activity);
        imageView = (ImageView) findViewById(R.id.Json_imageView);
        textView = (TextView) findViewById(R.id.Json);
        Intent rintent = getIntent();
        urlToPassGoogle = rintent.getStringExtra("urlToPassGoogle");
        urlToPassGit = rintent.getStringExtra("urlToPassGit");

        if (getIntent() != null) {
            Uri data = getIntent().getData();
            if (data != null) {
                String url = data.toString();
                if (url.contains("github.com")) {
                    urlToPassGit = url.replace("https://github.com/", "");
                } else if (url.contains("plus.google.com/1")) {
                    urlToPassGoogle = url.replace("https://plus.google.com/", "");
                } else {
                    Toast.makeText(this, "некоректне посилання", Toast.LENGTH_SHORT).show();
                }
            }
        }

        if (urlToPassGoogle != null) {
            GooglePlusApiInterface.Factory.getService().getUser(urlToPassGoogle).enqueue(new Callback<GooglePlusUser>() {
                @Override
                public void onResponse(Response<GooglePlusUser> response) {
                    textView.setText(response.body().getDisplayName());
                    GetBitmap getBitmap = new GetBitmap(getApplicationContext(), listener);
                    getBitmap.execute(response.body().getImage().getUrl());
                }

                @Override
                public void onFailure(Throwable t) {
                }
            });
        } else if (urlToPassGit != null) {
            GitApiInterface.Factory.getService().getUser(urlToPassGit).enqueue(new Callback<GithubUser>() {
                @Override
                public void onResponse(Response<GithubUser> response) {
                    textView.setText(response.body().getLogin());
                    GetBitmap getBitmap = new GetBitmap(getApplicationContext(), listener);
                    getBitmap.execute(response.body().getAvatarUrl());
                }

                @Override
                public void onFailure(Throwable t) {
                }
            });
        }
    }

    private final GetBitmap.AvatarProcessed listener = new GetBitmap.AvatarProcessed() {
        @Override
        public void onBitmapReady(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    };
}

