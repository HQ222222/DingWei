package com.tx.dingwei;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RepeatingAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction()!=null&&intent.getAction().equals("com.gcc.alarm")) {
            intent = new Intent(context,alarmActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
