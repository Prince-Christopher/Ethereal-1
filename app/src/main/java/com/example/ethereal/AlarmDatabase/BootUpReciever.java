package com.example.ethereal.AlarmDatabase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import Fragments.NotificationsFragment;

public class BootUpReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, NotificationsFragment.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
