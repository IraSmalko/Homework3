package com.exemple.android.myapplication.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.exemple.android.myapplication.recycler.MainActivity;
import com.exemple.android.myapplication.recycler.OnItemClickListener;
import com.exemple.android.myapplication.R;
import com.exemple.android.myapplication.retrofit.RetrofitActivity;

import java.util.ArrayList;


public class ListViewActivity extends Activity {
    private static ArrayList<ListItem> items = new ArrayList<>();
    private ListView mListView;
    private CustomListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_view);

        String[] array1 = getResources().getStringArray(R.array.name);
        String[] array2 = getResources().getStringArray(R.array.googlePlusUrl);
        String[] array3 = getResources().getStringArray(R.array.gitUrl);

        for (int i = 0; i < array1.length; i++) {
            items.add(new ListItem(getResources().getStringArray(R.array.name)[i], array2[i], "git", array3[i]));
        }

        mListView = (ListView) findViewById(R.id.list_activity_view);

        adapter = new CustomListViewAdapter(getApplicationContext(), items);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ListItem items) {
                Intent intent = new Intent(getApplicationContext(), RetrofitActivity.class);
                intent.putExtra("urlToPassGoogle", items.getGooglePlusUrl());
                startActivity(intent);
            }
        });
        adapter.setOnButtonClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ListItem items) {
                Intent intent = new Intent(getApplicationContext(), RetrofitActivity.class);
                intent.putExtra("urlToPassGit", items.getGitUrl());
                startActivity(intent);
            }
        });
        mListView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        items.clear();
    }

}