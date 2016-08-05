package com.abo.news.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abo.news.R;

/**
 * Created by abo on 16/8/4.
 */
public class NewsListFragment extends Fragment{
    private int mType = NewsFragment.NEWS_TYPE_TOP;
    
    public static  NewsListFragment newInstance(int type){

        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("type",type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news_list,null);
        return v;
    }
}
