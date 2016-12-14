package com.exemple.android.myapplication;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.exemple.android.myapplication.listview.ListItem;

import java.util.ArrayList;

public class BluetoothReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int state;
        BluetoothDevice bluetoothDevice;

        switch(action)
        {
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
                if (state == BluetoothAdapter.STATE_OFF)
                {
                    Toast.makeText(context, "Bluetooth is off", Toast.LENGTH_SHORT).show();
                }
                else if (state == BluetoothAdapter.STATE_TURNING_OFF)
                {
                    Toast.makeText(context, "Bluetooth is turning off", Toast.LENGTH_SHORT).show();
                }
                break;

            case BluetoothDevice.ACTION_ACL_CONNECTED:
                bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(context, "Connected to "+bluetoothDevice.getName(),
                        Toast.LENGTH_SHORT).show();
                break;

            case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(context, "Disconnected from "+bluetoothDevice.getName(),
                        Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
