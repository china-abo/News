package com.abo.news.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abo.news.R;
import com.abo.news.beans.ImageBean;
import com.abo.news.image.persenter.imagePersenter;
import com.abo.news.image.persenter.imagePersenterImpl;
import com.abo.news.image.view.imageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abo on 16/8/17.
 */
public class imageFragment extends Fragment implements imageView, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLinearLayoutManager;
    private List<ImageBean> mData;
    private imagePersenter mImagePersenter;
    private imageAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImagePersenter = new imagePersenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image,null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_image);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
//      mAdapter初始化
        mAdapter = new imageAdapter(getActivity().getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.addOnScrollListener(mOnScrollListener);
        onRefresh();
        return view;
    }

//    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener(){
//
//        private int lastVisibleItem;
//
//        @Override
//        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//            super.onScrolled(recyclerView, dx, dy);
//            lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
//        }
//
//        @Override
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
//            if(newState == RecyclerView.SCROLL_STATE_IDLE
//                    && lastVisibleItem +1 == mAdapter.getItemCount()){
//                Snackbar.make(getActivity().findViewById(R.id.drawer_layout),
//                        "加载更多",Snackbar.LENGTH_SHORT).show();
//            }
//        }
//    };

    @Override
    public void addImage(List<ImageBean> list) {
        if(mData == null){
            mData = new ArrayList<ImageBean>();
        }
        mData.addAll(list);
        mAdapter.setData(mData);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
        View v = getActivity() == null?mRecyclerView.getRootView():getActivity().findViewById(R.id.load_layout);
        Snackbar.make(v,"加载失败",Snackbar.LENGTH_SHORT).show();
    }
//加载图片。
    @Override
    public void onRefresh() {
        if (mData != null){
            mData.clear();
        }
        mImagePersenter.loadImageList();
    }
}
