package com.abo.news.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abo.news.Http.Url;
import com.abo.news.R;
import com.abo.news.beans.NewsBean;
import com.abo.news.news.presenter.NewsPresenter;
import com.abo.news.news.presenter.NewsPresenterImpl;
import com.abo.news.news.view.NewsView;

import java.util.ArrayList;
import java.util.List;





/**
 * Created by abo on 16/8/4.
 */
public class NewsListFragment extends Fragment implements NewsView, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private NewsAdapter mAdapter;
    private List<NewsBean> mData;
    private NewsPresenter mNewsPresenter;

    private int mType = NewsFragment.NEWS_TYPE_TOP;
    private int pageIndex = 0;


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
        mNewsPresenter = new NewsPresenterImpl(this);
        mType = getArguments().getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news_list,null);
        mSwipeRefreshWidget = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshWidget.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.news_recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new NewsAdapter(getActivity().getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
        onRefresh();
        return v;
    }
    //实现NewsView中的四个方法
    public void showProgress() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    public void addNews(List<NewsBean> newsList) {
        mAdapter.isShowFooter(true);
        if (mData == null){
            mData = new ArrayList<NewsBean>();
        }
        mData.addAll(newsList);
        if (pageIndex == 0){
            mAdapter.setmData(mData);
        }else{
            if (newsList == null ||newsList.size()==0){
                mAdapter.isShowFooter(false);
            }
            mAdapter.notifyDataSetChanged();
        }
        pageIndex += Url.PAZE_SIZE;

    }

    public void hideProgress() {
        mSwipeRefreshWidget.setRefreshing(false);
    }
//  notifyDataSetChanged()什么作用
    public void showLoadFail() {
        if(pageIndex == 0){
            mAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? mRecyclerView.getRootView() :
                getActivity().findViewById(R.id.drawer_layout);
        Snackbar.make(view,"数据加载失败",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        Log.d("aaaa","aaaa");
        pageIndex = 0;
        if(mData != null){
            mData.clear();
        }
        mNewsPresenter.loadNews(mType,pageIndex);
    }
}
