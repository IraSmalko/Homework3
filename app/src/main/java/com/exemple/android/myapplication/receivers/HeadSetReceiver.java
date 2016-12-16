package com.exemple.android.myapplication.receivers;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class HeadSetReceiver extends BroadcastReceiver {

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

