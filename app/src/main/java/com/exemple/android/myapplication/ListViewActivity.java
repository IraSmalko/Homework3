package com.exemple.android.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;


public class ListViewActivity extends Activity {
    private static ArrayList<ListItem> items = new ArrayList<>();
    private ListView mListView;
    private CustomListViewAdapter adapter;

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
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getGooglePlusUrl())));
            }
        });
        adapter.setOnButtonClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ListItem item) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getGitUrl())));
            }
        });
        mListView
                .setAdapter(adapter);
    }
}