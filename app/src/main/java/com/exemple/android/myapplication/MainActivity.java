package com.exemple.android.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.exemple.android.myapplication.contacts.AllContacts;
import com.exemple.android.myapplication.contacts.NewContactUsingActivity;
import com.exemple.android.myapplication.listview.ListViewActivity;
import com.exemple.android.myapplication.realm.ListItem;
import com.exemple.android.myapplication.receivers.BluetoothReceiver;
import com.exemple.android.myapplication.receivers.HeadSetReceiver;
import com.exemple.android.myapplication.recycler.MyAdapter;
import com.exemple.android.myapplication.recycler.OnItemClickListener;
import com.exemple.android.myapplication.retrofit.RetrofitActivity;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Realm realm;
    private HeadSetReceiver myReceiver;
    private BluetoothReceiver bluetoothReceiver;
    RealmResults<ListItem> results;
    MyAdapter adapter;


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
        setContentView(R.layout.activity_main);
        myReceiver = new HeadSetReceiver();
        bluetoothReceiver = new BluetoothReceiver();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        realm = Realm.getDefaultInstance();
        results = realm.where(ListItem.class).findAll();

        adapter = new MyAdapter(results);
        mRecyclerView.setAdapter(adapter);

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
    }


    public void onResume() {
        super.onResume();
        registerReceiver(bluetoothReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
        registerReceiver(myReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
        unregisterReceiver(bluetoothReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void addStudent() {
        realm.beginTransaction();

        for (int i = 0; i < getResources().getStringArray(R.array.name).length; i++) {
            ListItem listItem = realm.createObject(ListItem.class);
            listItem.setName(getResources().getStringArray(R.array.name)[i]);
            listItem.setGit("git");
            listItem.setGooglePlusUrl(getResources().getStringArray(R.array.googlePlusUrl)[i]);
            listItem.setGitUrl(getResources().getStringArray(R.array.gitUrl)[i]);
        }
        realm.commitTransaction();
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