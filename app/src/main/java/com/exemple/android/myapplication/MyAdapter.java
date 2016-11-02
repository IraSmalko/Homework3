package com.exemple.android.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {

    private List<ListItem> items;
    private OnItemClickListener onItemClickListener;
    private OnItemClickListener onButtonClickListener;

    public MyAdapter(List<ListItem> items) {
        this.items = items;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
    public OnItemClickListener getOnButtonClickListener() {return onButtonClickListener;}

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public void setOnButtonClickListener (OnItemClickListener onButtonClickListener){
        this.onButtonClickListener = onButtonClickListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView nameTextView;
        protected Button gitButton;

        public CustomViewHolder(View view) {
            super(view);
            this.nameTextView = (TextView) view.findViewById(R.id.name);
            this.gitButton = (Button) view.findViewById(R.id.git);
        }
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final ListItem item = items.get(i);

        customViewHolder.nameTextView.setText(item.getName());
        customViewHolder.gitButton.setText(item.getGit());

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
        customViewHolder.nameTextView.setOnClickListener(listener);
        customViewHolder.gitButton.setOnClickListener(buttonListener);
    }

    public int getItemCount() {
        return (null != items ? items.size() : 0);
    }
}

