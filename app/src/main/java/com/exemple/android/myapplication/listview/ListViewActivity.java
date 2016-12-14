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

        items.add(new ListItem(getResources().getString(R.string.name1), getResources().getString(R.string.gplus1), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl1)));
        items.add(new ListItem(getResources().getString(R.string.name2), getResources().getString(R.string.gplus2), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl2)));
        items.add(new ListItem(getResources().getString(R.string.name3), getResources().getString(R.string.gplus3), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl3)));
        items.add(new ListItem(getResources().getString(R.string.name4), getResources().getString(R.string.gplus4), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl4)));
        items.add(new ListItem(getResources().getString(R.string.name5), getResources().getString(R.string.gplus5), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl5)));
        items.add(new ListItem(getResources().getString(R.string.name6), getResources().getString(R.string.gplus6), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl6)));
        items.add(new ListItem(getResources().getString(R.string.name7), getResources().getString(R.string.gplus7), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl7)));
        items.add(new ListItem(getResources().getString(R.string.name8), getResources().getString(R.string.gplus8), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl8)));
        items.add(new ListItem(getResources().getString(R.string.name9), getResources().getString(R.string.gplus9), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl9)));
        items.add(new ListItem(getResources().getString(R.string.name10), getResources().getString(R.string.gplus10), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl10)));
        items.add(new ListItem(getResources().getString(R.string.name11), getResources().getString(R.string.gplus11), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl11)));
        items.add(new ListItem(getResources().getString(R.string.name12), getResources().getString(R.string.gplus12), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl12)));
        items.add(new ListItem(getResources().getString(R.string.name13), getResources().getString(R.string.gplus13), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl13)));
        items.add(new ListItem(getResources().getString(R.string.name14), getResources().getString(R.string.gplus14), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl14)));
        items.add(new ListItem(getResources().getString(R.string.name15), getResources().getString(R.string.gplus15), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl15)));
        items.add(new ListItem(getResources().getString(R.string.name16), getResources().getString(R.string.gplus16), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl16)));
        items.add(new ListItem(getResources().getString(R.string.name17), getResources().getString(R.string.gplus17), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl17)));
        items.add(new ListItem(getResources().getString(R.string.name18), getResources().getString(R.string.gplus18), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl18)));
        items.add(new ListItem(getResources().getString(R.string.name19), getResources().getString(R.string.gplus19), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl19)));
        items.add(new ListItem(getResources().getString(R.string.name20), getResources().getString(R.string.gplus20), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl20)));

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