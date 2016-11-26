package com.exemple.android.myapplication.retrofit;

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
import com.exemple.android.myapplication.retrofit.GetBitmap;
import com.exemple.android.myapplication.retrofit.GitApiInterface;
import com.exemple.android.myapplication.retrofit.GooglePlusApiInterface;
import com.exemple.android.myapplication.retrofit.data.GithubUser;
import com.exemple.android.myapplication.retrofit.data.GooglePlusUser;

import retrofit.Callback;
import retrofit.Response;


public class RetrofitActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_activity);

        if (getIntent() != null) {
            Uri data = getIntent().getData();
            if (data != null) {
                String url = data.toString();
                if (url.contains("github.com")) {
                    url = url.replace("http://github.com/", "");
                    MainActivity.setUrlToPassGit(url);
                } else if (url.contains("plus.google.com/1")) {
                    url = url.replace("https://plus.google.com/", "");
                    MainActivity.setUrlToPassGoogle(url);
                } else {
                    Toast.makeText(this, "некоректне посилання", Toast.LENGTH_SHORT).show();
                }
            }
        }

        imageView = (ImageView) findViewById(R.id.Json_imageView);
        textView = (TextView) findViewById(R.id.Json);
        String passedUrl = MainActivity.getUrlToPassGoogle();
        String urlToCall = (passedUrl != null) ? passedUrl : ListViewActivity.getUrlToPassGoogle();
        String whereUrl = (MainActivity.getUrlToPassGit() != null) ? MainActivity.getUrlToPassGit() : ListViewActivity.getUrlToPassGit();

        if (urlToCall != null) {
            GooglePlusApiInterface.Factory.getService().getUser(urlToCall).enqueue(new Callback<GooglePlusUser>() {
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
        } else if (whereUrl != null) {
            GitApiInterface.Factory.getService().getUser(whereUrl).enqueue(new Callback<GithubUser>() {
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

