package com.exemple.android.myapplication.recycler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.exemple.android.myapplication.PhotoActivity;
import com.exemple.android.myapplication.R;
import com.exemple.android.myapplication.contacts.AllContacts;
import com.exemple.android.myapplication.contacts.ContactVO;
import com.exemple.android.myapplication.contacts.NewContactUsing;
import com.exemple.android.myapplication.retrofit.RetrofitActivity;
import com.exemple.android.myapplication.listview.ListItem;
import com.exemple.android.myapplication.listview.ListViewActivity;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter adapter;
    private static ArrayList<ListItem> items = new ArrayList<>();
    private HeadSetReceiver myReceiver;

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

    public static ArrayList<ListItem> getItems() {
        return items;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myReceiver = new HeadSetReceiver();

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

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(items);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ListItem items) {
                Intent intent = new Intent(getApplicationContext(), RetrofitActivity.class);
                intent.putExtra("urlToPassGoogle", items.getGooglePlusUrl());//urlToPassGoogle = items.getGooglePlusUrl();
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
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(myReceiver, filter);
        super.onResume();
    }

    private class HeadSetReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                if (state == 1) {
                    {
                        Toast.makeText(context, "Headset plugged", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}