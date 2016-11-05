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

import java.util.ArrayList;


public class ListViewActivity extends Activity {
    private static ArrayList<ListItem> items = new ArrayList<>();
    private ListView mListView;
    private CustomListViewAdapter adapter;
    private static String urlToPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_view);

        items = MainActivity.getItems();
        mListView = (ListView) findViewById(R.id.list_activity_view);

        adapter = new CustomListViewAdapter(MainActivity.getContext(), items);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ListItem item) {
                urlToPass = item.getGooglePlusUrl();
                startActivity(new Intent(MainActivity.getContext(), JSONActivity.class));
            }
        });
        adapter.setOnButtonClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ListItem item) {
                urlToPass = item.getGitUrl();
                startActivity(new Intent(MainActivity.getContext(), JSONActivity.class));
            }
        });
        mListView.setAdapter(adapter);
    }
    public static String getUrlToPass() {
        return urlToPass;
    }
}