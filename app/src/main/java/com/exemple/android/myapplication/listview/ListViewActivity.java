package com.exemple.android.myapplication.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toolbar;

import com.exemple.android.myapplication.PhotoActivity;
import com.exemple.android.myapplication.R;
import com.exemple.android.myapplication.contacts.AllContacts;
import com.exemple.android.myapplication.contacts.NewContactUsingActivity;
import com.exemple.android.myapplication.realm.ListItem;
import com.exemple.android.myapplication.recycler.OnItemClickListener;
import com.exemple.android.myapplication.retrofit.RetrofitActivity;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class ListViewActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private CustomListViewAdapter adapter;
    RealmResults<ListItem> results;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                return true;
            case R.id.action:
                startActivity(new Intent(this, ListViewActivity.class));
                return true;
            case R.id.foto:
                startActivity(new Intent(this, PhotoActivity.class));
                return true;
            case R.id.contacts:
                startActivity(new Intent(this, AllContacts.class));
                return true;
            case R.id.newcontact:
                startActivity(new Intent(this, NewContactUsingActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_view);
        Realm realm = Realm.getDefaultInstance();
        results = realm.where(ListItem.class).findAll();

        ListView listView = (ListView) findViewById(R.id.list_activity_view);

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
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<ListItem> newList = new ArrayList<>();
        for (ListItem listItem : results) {
            String name = listItem.getName().toLowerCase();
            if (name.contains(newText))
                newList.add(listItem);
        }
        adapter.setFilter(newList);
        return true;
    }

}