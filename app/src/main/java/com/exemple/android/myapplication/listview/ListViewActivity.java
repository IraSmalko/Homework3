package com.exemple.android.myapplication.listview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.exemple.android.myapplication.recycler.MainActivity;
import com.exemple.android.myapplication.recycler.OnItemClickListener;
import com.exemple.android.myapplication.R;
import com.exemple.android.myapplication.json.JSONActivity;
import com.exemple.android.myapplication.retrofit.GitApiInterface;
import com.exemple.android.myapplication.retrofit.GooglePlusApiInterface;
import com.exemple.android.myapplication.retrofit.data.GithubUser;
import com.exemple.android.myapplication.retrofit.data.GooglePlusUser;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.Response;


public class ListViewActivity extends Activity {
    private static ArrayList<ListItem> items = new ArrayList<>();
    private ListView mListView;
    private CustomListViewAdapter adapter;
    private static String urlToPass;
    private static String urlToPassGit;
    private static String urlToPassGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_view);

        items = MainActivity.getItems();
        mListView = (ListView) findViewById(R.id.list_activity_view);

        adapter = new CustomListViewAdapter(getApplicationContext(), items);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ListItem items) {
                urlToPass = urlToPassGoogle = items.getGooglePlusUrl();
                startActivity(new Intent(getApplicationContext(), JSONActivity.class));
            }
        });
        adapter.setOnButtonClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ListItem items) {
                urlToPass = urlToPassGit = items.getGitUrl();
                startActivity(new Intent(getApplicationContext(), JSONActivity.class));
            }
        });
        mListView.setAdapter(adapter);
    }
    public static String getUrlToPass() { return urlToPass;  }

    public static String getUrlToPassGit() {
        return urlToPassGit;
    }
    public static String getUrlToPassGoogle() {
        return urlToPassGoogle;
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}