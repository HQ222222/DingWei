package com.tx.dingwei;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookTitleFrag extends Fragment {
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.book_title_frag,container,false);
        RecyclerView newsTitleRecyclerView=(RecyclerView) view.findViewById(R.id.book_title_recycler_view);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        BookTitleFrag.NewsAdapter adapter=new BookTitleFrag.NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);



        return view;
    }

    public List<News> getNews(){
        List <News> newsList=new ArrayList<>();
        for(int i=1;i<=20;i++){
            News news=new News();
            news.setTitle("四大名著");
            news.setContent(getRandomLengthContent("中国古典长篇小说四大名著，简称四大名著，是指《水浒传》《三国演义》《西游记》《红楼梦》（按照成书先后顺序）这四部巨著。\n" +
                    "四大古典名著是中国文学史中的经典作品，是世界宝贵的文化遗产 。此四部巨著在中国文学史上的地位是难分高低的，都有着极高的文学水平和艺术成就，细致的刻画和所蕴含的深刻思想都为历代读者所称道，其中的故事、场景、人物已经深深地影响了中国人的思想观念、价值取向。可谓中国文学史上的四座伟大丰碑。"));
            newsList.add(news);

        }
        return newsList;
    }
    private  String getRandomLengthContent(String content){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(content);
        }
        return  builder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.book_content)!=null){
            isTwoPane=true;
        }else{
            isTwoPane=false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        private List<News> mNewsList;

        class ViewHolder extends  RecyclerView.ViewHolder{
            TextView newsTitleText;
            public ViewHolder(View view){
                super(view);
                newsTitleText=(TextView) view.findViewById(R.id.book_title);
            }
        }


        /*NewsAdapter构造方法，调用时需传入数据列表*/
        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
            final BookTitleFrag.NewsAdapter.ViewHolder holder = new BookTitleFrag.NewsAdapter.ViewHolder(view) {
            };
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane) {
                        BookContentFrag newsContentFragment = (BookContentFrag) getFragmentManager().findFragmentById(R.id.books_content_fragment);
                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        BookContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());   /*指定参数传递的启动Activity方式*/
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news =mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }


        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }
}