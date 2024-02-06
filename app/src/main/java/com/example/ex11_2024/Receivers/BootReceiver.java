package com.example.ex11_2024.Receivers;

import static android.content.Context.MODE_PRIVATE;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BootReceive","start");
        SharedPreferences bootcounter = context.getSharedPreferences("bootcount", MODE_PRIVATE);
        SharedPreferences.Editor edit=bootcounter.edit();
        int temp  = bootcounter.getInt("boot", -1);
        Log.i("test", String.valueOf(temp));
        if(temp!=-1) {
            edit.putInt("boot", temp + 1);
        }
        else{
            edit.putInt("boot",0);
        }
        edit.commit();
        Log.i("BootReceive","end");
    }
}