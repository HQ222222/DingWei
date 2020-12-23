package com.tx.dingwei;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class media4Activity extends AppCompatActivity implements View.OnClickListener {

    public static final int UPDATE_TEXT=1;
    private Handler handler =new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    countDownTimer.start();
                    mediaPlayer.start();
                    pause_btn.setImageResource(R.drawable.zanting);
                    default:break;
            }
        }
    };
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private ImageView pause_btn;
    private SeekBar seekbar;
    private Timer timer;
    private TimerTask timerTask;
    private boolean iffirst = false;
    private boolean isChanging=false;
    private TextView mTvValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media4);


        mTvValue=findViewById(R.id.update22);
        ImageView play_btn = findViewById(R.id.playing_pre);
        play_btn.setOnClickListener(this);
         pause_btn = findViewById(R.id.playing_play);
        pause_btn.setOnClickListener(this);
        ImageView stop_btn = findViewById(R.id.playing_next);
        stop_btn.setOnClickListener(this);
        seekbar=findViewById(R.id.music_seek_bar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isChanging=true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                isChanging=false;
            }
        });

        seekbar.setMax(mediaPlayer.getDuration());
        timer=new Timer();
        timerTask  =new TimerTask() {
            @Override
            public void run() {
                seekbar.setProgress(mediaPlayer.getCurrentPosition());


            }
        };
        timer.schedule(timerTask,0,10);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            mediaPlayer.stop();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.playing_play:
                try {
                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer = MediaPlayer.create(this, R.raw.gangqing);
                            seekbar.setMax(mediaPlayer.getDuration());
                            timer=new Timer();
                            timerTask  =new TimerTask() {
                                @Override
                                public void run() {
                                    seekbar.setProgress(mediaPlayer.getCurrentPosition());
                                }
                            };
                            timer.schedule(timerTask,0,10);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Message message =new Message();
                                    message.what=UPDATE_TEXT;
                                    handler.sendMessage(message);
                                }
                            }).start();
                    }else{
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                        pause_btn.setImageResource(R.drawable.bofang);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.playing_pre:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.playing_next:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }




    /**
     * CountDownTimer 实现倒计时
     */
    private CountDownTimer countDownTimer = new CountDownTimer((long) (262 * 1000), 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            String value = String.valueOf((int) (millisUntilFinished / 1000));
            mTvValue.setText(value+"秒");
        }

        @Override
        public void onFinish() {
            mTvValue.setText("0:00");
        }
    };

}
