package com.exemple.android.myapplication.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.exemple.android.myapplication.R;
import com.exemple.android.myapplication.realm.ListItem;
import com.exemple.android.myapplication.recycler.OnItemClickListener;
import com.exemple.android.myapplication.retrofit.RetrofitActivity;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class ListViewActivity extends Activity {
   // private static ArrayList<ListItem> items = new ArrayList<>();
    private ListView mListView;
    private CustomListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_view);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ListItem> results = realm.where(ListItem.class).findAll();

        mListView = (ListView) findViewById(R.id.list_activity_view);

        adapter = new CustomListViewAdapter(getApplicationContext(), results);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ListItem results) {
                Intent intent = new Intent(getApplicationContext(), RetrofitActivity.class);
                intent.putExtra("urlToPassGoogle", results.getGooglePlusUrl());
                startActivity(intent);
            }
        });
        adapter.setOnButtonClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ListItem results) {
                Intent intent = new Intent(getApplicationContext(), RetrofitActivity.class);
                intent.putExtra("urlToPassGit", results.getGitUrl());
                startActivity(intent);
            }
        });
        mListView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        items.clear();
    }

}