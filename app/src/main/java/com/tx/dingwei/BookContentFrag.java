package com.tx.dingwei;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BookContentFrag extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.books_content_frag,container,false);
        return view;
    }

    public void refresh(String newsTitle,String newsContent){
        View visibilityLayout =view.findViewById(R.id.book_visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView newsTitleText=(TextView) view.findViewById(R.id.book_title);
        TextView newsContentText=(TextView) view.findViewById(R.id.book_content);
        newsTitleText.setText(newsTitle);
        newsContentText.setText(newsContent);

    }


}
