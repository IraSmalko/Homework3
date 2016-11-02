package com.exemple.android.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class JSONActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_activity);

        Google google = new Google();
        google.execute();
        google.getLogin();
        google.getAvatar();

        TextView textView = (TextView) findViewById(R.id.Json);
        textView.setTextSize(40);
        textView.setText(google.getLogin());

        // Устанавливаем текстовое поле в системе компоновки activity




    }
}