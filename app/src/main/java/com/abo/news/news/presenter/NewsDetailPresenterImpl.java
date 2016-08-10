package com.abo.news.news.presenter;

import android.content.Context;

import com.abo.news.beans.NewsDetailBean;
import com.abo.news.news.model.NewModel;
import com.abo.news.news.model.NewModelImpl;
import com.abo.news.news.view.NewsDetail;

/**
 * Created by abo on 16/8/10.
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter,NewModelImpl.OnLoadNewDetailListener {

    private NewsDetail mNewsDetail;
    private NewModel mNewModel;
    private Context mContext;

    public NewsDetailPresenterImpl(Context context, NewsDetail newsDetail){
        this.mContext = context;
        this.mNewsDetail = newsDetail;
        mNewModel = new NewModelImpl();

    }

    @Override
    public void loadNewsDetail(String docID) {
        mNewsDetail.showProgress();
        mNewModel.loadNewsDetail(docID,this);
    }

    @Override
    public void onSuccess(NewsDetailBean list) {
        mNewsDetail.showNewsDetails(list.getBody());
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsDetail.hideProgress();

    }
}
