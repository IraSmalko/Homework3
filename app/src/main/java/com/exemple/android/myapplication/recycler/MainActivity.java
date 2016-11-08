package com.exemple.android.myapplication.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.exemple.android.myapplication.FotoActivity;
import com.exemple.android.myapplication.R;
import com.exemple.android.myapplication.json.JSONActivity;
import com.exemple.android.myapplication.listview.ListItem;
import com.exemple.android.myapplication.listview.ListViewActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter adapter;
    private static ArrayList<ListItem> items = new ArrayList<>();
    private OnMenuItemClickListener onMenuItemClickListener;
    private static String urlToPass;

    public static String getUrlToPass() {
        return urlToPass;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action:
                startActivity(new Intent(this, ListViewActivity.class));
                return true;
            case R.id.foto:
                startActivity(new Intent(this, FotoActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
        }

        public static ArrayList<ListItem> getItems () {
            return items;
        }
        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            items.add(new ListItem(getResources().getString(R.string.name1),
                    getResources().getString(R.string.gplus1), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl1)));
            items.add(new ListItem(getResources().getString(R.string.name2),
                    getResources().getString(R.string.gplus2), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl2)));
            items.add(new ListItem(getResources().getString(R.string.name3),
                    getResources().getString(R.string.gplus3), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl3)));
            items.add(new ListItem(getResources().getString(R.string.name4),
                    getResources().getString(R.string.gplus4), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl4)));
            items.add(new ListItem(getResources().getString(R.string.name5),
                    getResources().getString(R.string.gplus5), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl5)));
            items.add(new ListItem(getResources().getString(R.string.name6),
                    getResources().getString(R.string.gplus6), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl6)));
            items.add(new ListItem(getResources().getString(R.string.name7),
                    getResources().getString(R.string.gplus7), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl7)));
            items.add(new ListItem(getResources().getString(R.string.name8),
                    getResources().getString(R.string.gplus8), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl8)));
            items.add(new ListItem(getResources().getString(R.string.name9),
                    getResources().getString(R.string.gplus9), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl9)));
            items.add(new ListItem(getResources().getString(R.string.name10),
                    getResources().getString(R.string.gplus10), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl10)));
            items.add(new ListItem(getResources().getString(R.string.name11),
                    getResources().getString(R.string.gplus11), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl11)));
            items.add(new ListItem(getResources().getString(R.string.name12),
                    getResources().getString(R.string.gplus12), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl12)));
            items.add(new ListItem(getResources().getString(R.string.name13),
                    getResources().getString(R.string.gplus13), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl13)));
            items.add(new ListItem(getResources().getString(R.string.name14),
                    getResources().getString(R.string.gplus14), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl14)));
            items.add(new ListItem(getResources().getString(R.string.name15),
                    getResources().getString(R.string.gplus15), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl15)));
            items.add(new ListItem(getResources().getString(R.string.name16),
                    getResources().getString(R.string.gplus16), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl16)));
            items.add(new ListItem(getResources().getString(R.string.name17),
                    getResources().getString(R.string.gplus17), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl17)));
            items.add(new ListItem(getResources().getString(R.string.name18),
                    getResources().getString(R.string.gplus18), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl18)));
            items.add(new ListItem(getResources().getString(R.string.name19),
                    getResources().getString(R.string.gplus19), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl19)));
            items.add(new ListItem(getResources().getString(R.string.name20),
                    getResources().getString(R.string.gplus20), getResources().getString(R.string.git), getResources().getString(R.string.gitUrl20)));

            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter = new MyAdapter(items);
            mRecyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(ListItem items) {
                    urlToPass = items.getGooglePlusUrl();
                    startActivity(new Intent(getApplicationContext(), JSONActivity.class));
                }
            });
            adapter.setOnButtonClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(ListItem items) {
                    urlToPass = items.getGitUrl();
                    startActivity(new Intent(getApplicationContext(), JSONActivity.class));
                }
            });

            setOnMenuItemClickListener(new OnMenuItemClickListener() {
                @Override
                public void onMenuItemClick() {
                }
            });
        }
    }