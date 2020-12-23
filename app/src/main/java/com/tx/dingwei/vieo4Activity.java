package com.tx.dingwei;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class vieo4Activity extends AppCompatActivity implements View.OnClickListener {


    private SurfaceView surfaceView;
    private ProgressBar progressBar;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer vieoPlayer;
    private SeekBar seekBar;
    private ImageView start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieo4);
        surfaceView = findViewById(R.id.surfaceView);
        progressBar = findViewById(R.id.progressBar);
        seekBar = findViewById(R.id.seekBar);
        start =findViewById(R.id.start);
        start.setOnClickListener(this);

        vieoPlayer = new MediaPlayer();
        String uri = "android.resource://" + getPackageName() + "/raw/" + R.raw.xue;

        try {
            vieoPlayer.setDataSource(this, Uri.parse(uri));

            surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(new MyCallBack());
            vieoPlayer.prepare();
            vieoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.INVISIBLE);

                    vieoPlayer.setLooping(true);

                    seekBar.setMax(vieoPlayer.getDuration());
                    new myThread().start();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                vieoPlayer.seekTo(seekBar.getProgress());
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case  R.id.start:
                if (!vieoPlayer.isPlaying()) {
                    vieoPlayer.start();
                    start.setImageResource(R.drawable.bofang);
                }else{
                    vieoPlayer.pause();
                    start.setImageResource(R.drawable.zanting);

                }
                break;
                default:
                    break;
        }
    }

    private class MyCallBack implements SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            vieoPlayer.setDisplay(holder);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }


    class myThread extends Thread {
        @Override
        public void run() {

            while (seekBar.getProgress() <= seekBar.getMax()) {

                seekBar.setProgress(vieoPlayer.getCurrentPosition());
            }
        }
    }

}