package com.exemple.android.myapplication.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.exemple.android.myapplication.realm.ListItem;
import com.exemple.android.myapplication.recycler.OnItemClickListener;
import com.exemple.android.myapplication.R;

import java.util.ArrayList;

import io.realm.RealmResults;

public class CustomListViewAdapter extends BaseAdapter {
    private static RealmResults<ListItem> listItems;

    private LayoutInflater mInflater;
    private OnItemClickListener onItemClickListener;
    private OnItemClickListener onButtonClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public OnItemClickListener getOnButtonClickListener() {
        return onButtonClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnButtonClickListener(OnItemClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public CustomListViewAdapter(Context context, RealmResults<ListItem> results) {
        listItems = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return listItems.size();
    }

    public Object getItem(int position) {
        return listItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ListItem item = listItems.get(position);

        CustomViewHolder customViewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_view, null);
            customViewHolder = new CustomViewHolder();
            customViewHolder.name = (TextView) convertView.findViewById(R.id.name);
            customViewHolder.gitButton = (Button) convertView.findViewById(R.id.git);

            convertView.setTag(customViewHolder);
        } else {
            customViewHolder = (CustomViewHolder) convertView.getTag();
        }

        customViewHolder.name.setText(listItems.get(position).getName());
        customViewHolder.gitButton.setText(listItems.get(position).getGit());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(item);
            }
        };
        View.OnClickListener buttonListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onButtonClickListener.onItemClick(item);
            }
        };
        customViewHolder.name.setOnClickListener(listener);
        customViewHolder.gitButton.setOnClickListener(buttonListener);

        return convertView;
    }

    static class CustomViewHolder {
        TextView name;
        Button gitButton;
    }
}
