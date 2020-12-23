package com.tx.dingwei;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class alarmActivity extends AppCompatActivity {


    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = new MediaPlayer();
        AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.gangqing);
        try {
            mp.setDataSource(file.getFileDescriptor(), file.getStartOffset(),
                    file.getLength());
            mp.prepare();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setVolume(0.5f, 0.5f);
        mp.setLooping(true);
        mp.start();
        alarmOialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp.release();
        }
    }

    public void alarmOialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("您的浏览已经达到约定时间,请注意休息!");
        builder.setPositiveButton("稍后提醒",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alarm();
                        finish();
                    }
                });

        builder.setNegativeButton("停止", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cancleAlarm();
                finish();// 关闭窗口
            }
        });
        builder.show().setCanceledOnTouchOutside(false);
        ;

    }

    /**
     * 取消闹钟
     */
    private void cancleAlarm() {
        // Create the same intent, and thus a matching IntentSender, for
        // the one that was scheduled.
        Intent intent = new Intent(alarmActivity.this, RepeatingAlarm.class);
        intent.setAction("com.gcc.alarm");
        PendingIntent sender = PendingIntent.getBroadcast(alarmActivity.this,
                0, intent, 0);

        // And cancel the alarm.
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.cancel(sender);
    }

    private void alarm() {

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // 触发闹钟的时间（毫秒）
        long triggerTime = System.currentTimeMillis() + 20000;
        Intent intent = new Intent(this, RepeatingAlarm.class);
        intent.setAction("com.gcc.alarm");
        PendingIntent op = PendingIntent.getBroadcast(this, 0, intent, 0);
        // 启动一次只会执行一次的闹钟
        am.set(AlarmManager.RTC, triggerTime, op);

    }

}