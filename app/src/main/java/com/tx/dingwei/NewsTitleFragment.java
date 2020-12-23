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

public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.new_title_frag,container,false);
        RecyclerView newsTitleRecyclerView=(RecyclerView) view.findViewById(R.id.news_title_recycler_view);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter=new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);



        return view;
    }

    public  List<News> getNews(){
        List <News> newsList=new ArrayList<>();
        for(int i=1;i<=20;i++){
            News news=new News();
            news.setTitle("纳卡地区再次爆发冲突 俄维和部队将直接参战？");
            news.setContent(getRandomLengthContent("环球网军事报道,俄罗斯卫星通讯社12月17日报道，亚美尼亚国防部12月16日发布消息称，俄罗斯维和人员帮助亚美尼亚军人从在纳卡哈德鲁特区的包围圈撤出。"));
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
        if(getActivity().findViewById(R.id.news_content)!=null){
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
                newsTitleText=(TextView) view.findViewById(R.id.news_title);
            }
        }


        /*NewsAdapter构造方法，调用时需传入数据列表*/
        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view) {
            };
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane) {
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        NewContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());   /*指定参数传递的启动Activity方式*/
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