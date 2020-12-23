package com.tx.dingwei;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tx.dingwei.R;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class NewsTestActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView counter;
    private int count = 30;
    private boolean isEnd = false;

    private TextView musicTitle;
    private TextView videos;
    private TextView read;
    private TextView  weizhi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicTitle =findViewById(R.id.musicTitle);
        musicTitle.setOnClickListener(this);

        videos=findViewById(R.id.videos);
        videos.setOnClickListener(this);


        read= findViewById(R.id.read);
        read.setOnClickListener(this);

        weizhi=findViewById(R.id.weizhi);
        weizhi.setOnClickListener(this);



        List images = new ArrayList();
        images.add(R.drawable.tt1);
        images.add(R.drawable.tt2);
        images.add(R.drawable.tt3);

        counter = (TextView)findViewById(R.id.counter);
        new Thread(new CounterThread()).start();


        Banner banner = findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(NewsTestActivity.this, "与阿拉伯国家走得太近，普京公开敲打卡德罗夫：不要干预外交政策", Toast.LENGTH_SHORT).show();
            }
        });


}






    Handler handler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case 0:
                    if (count == 0){
                        Intent intent6 =new Intent(NewsTestActivity.this,   alarmActivity.class);
                        startActivity(intent6);
                        count = 30;

                    }
                    counter.setText(String.valueOf(count)+"s");

                    break;
                default:
                    break;
            }
        }
    };





    public class CounterThread implements Runnable{
        @Override
        public void run(){
            while (!isEnd){
                try {
                    Thread.sleep(1000);
                    count--;
                    if (count < 1){
                        isEnd = true;
                    }
                    Message msg = new Message();
                    msg.what = 0;
                    handler.sendMessage(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.musicTitle:
                Intent intent1 =new Intent(NewsTestActivity.this,   media4Activity.class);
                startActivity(intent1);


                break;
            case R.id.videos:
                Intent intent2 =new Intent(NewsTestActivity.this,   vieo4Activity.class);
                startActivity(intent2);


                break;

            case R.id.read:
                Intent intent3 =new Intent(NewsTestActivity.this,   ReadBookActivity.class);
                startActivity(intent3);


                break;

            case R.id.weizhi:
                Intent intent4 =new Intent(NewsTestActivity.this,   MainActivity.class);
                startActivity(intent4);


                break;



            default:
                    break;
        }

    }
}
