package com.exemple.android.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class JSONActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_activity);
        Google google = new Google();
        google.execute();
        google.getLogin();
        google.getAvatar();


    }
}