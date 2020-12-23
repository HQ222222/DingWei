package com.tx.dingwei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BookContentActivity extends AppCompatActivity {


    public static  void actionStart(Context context, String newsTitle, String newsContent){
        Intent intent=new Intent(context,BookContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_content);
        String newsTitle=getIntent().getStringExtra("news_title");
        String newsContent =getIntent().getStringExtra("news_content");
        BookContentFrag booksContentFragment =(BookContentFrag)
                getSupportFragmentManager().findFragmentById(R.id.books_content_fragment);
        booksContentFragment.refresh(newsTitle,newsContent);
    }
}
