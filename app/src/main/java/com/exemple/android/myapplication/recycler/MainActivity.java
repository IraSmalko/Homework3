package com.exemple.android.myapplication.recycler;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.exemple.android.myapplication.receivers.BluetoothReceiver;
import com.exemple.android.myapplication.receivers.HeadSetReceiver;
import com.exemple.android.myapplication.PhotoActivity;
import com.exemple.android.myapplication.R;
import com.exemple.android.myapplication.contacts.AllContacts;
import com.exemple.android.myapplication.contacts.NewContactUsing;
import com.exemple.android.myapplication.listview.ListItem;
import com.exemple.android.myapplication.listview.ListViewActivity;
import com.exemple.android.myapplication.retrofit.RetrofitActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<ListItem> items = new ArrayList<>();
    private HeadSetReceiver myReceiver;
    private BluetoothReceiver bluetoothReceiver;

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
                startActivity(new Intent(this, PhotoActivity.class));
                return true;
            case R.id.contacts:
                startActivity(new Intent(this, AllContacts.class));
                return true;
            case R.id.newcontact:
                startActivity(new Intent(this, NewContactUsing.class));
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
        String[] array1 = getResources().getStringArray(R.array.name);
        String[] array2 = getResources().getStringArray(R.array.googlePlusUrl);
        String[] array3 = getResources().getStringArray(R.array.gitUrl);

        for (int i = 0; i < array1.length; i++) {
            items.add(new ListItem(getResources().getStringArray(R.array.name)[i], array2[i], "git", array3[i]));
        }

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(items);
        mRecyclerView.setAdapter(adapter);

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
        items.clear();
    }

}