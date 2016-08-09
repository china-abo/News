package com.abo.news.news.presenter;

import android.util.Log;

import com.abo.news.Http.Url;
import com.abo.news.beans.NewsBean;
import com.abo.news.news.model.NewModel;
import com.abo.news.news.model.NewModelImpl;
import com.abo.news.news.view.NewsView;
import com.abo.news.news.widget.NewsFragment;

import java.util.List;

/**
 * Created by abo on 16/8/8.
 */
public class NewsPresenterImpl implements NewsPresenter,NewModelImpl.OnLoadNewListListener {
    private NewsView mNewsView;
    private NewModel mNewModel;
    private static final String TAG = "NewsPresenterImpl";
    public NewsPresenterImpl(NewsView newsView) {
        this.mNewsView = newsView;
        this.mNewModel = new NewModelImpl();
    }

    @Override
    public void loadNews(int type, int page) {
        String url = getUrl(type,page);
        Log.d("TAG",url);
        if(page == 0){
            mNewsView.showProgress();
        }
        mNewModel.loadNews(url,type,this);

    }

    private String getUrl(int type, int pageIdex){
        StringBuffer stringBuffer = new StringBuffer();
        switch (type){
            case NewsFragment.NEWS_TYPE_TOP:
                stringBuffer.append(Url.TOP_URL).append(Url.TOP_ID);
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                stringBuffer.append(Url.COMMON_URL).append(Url.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                stringBuffer.append(Url.COMMON_URL).append(Url.CAR_ID);
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                stringBuffer.append(Url.COMMON_URL).append(Url.JOKE_ID);
                break;
            default:
                stringBuffer.append(Url.TOP_URL).append(Url.TOP_ID);
                break;
        }
        stringBuffer.append("/").append(pageIdex).append(Url.END_URL);
        return stringBuffer.toString();
    }

    @Override
    public void onSuccess(List<NewsBean> list) {
        mNewsView.addNews(list);
        mNewsView.showProgress();

    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsView.hideProgress();
        mNewsView.showLoadFail();
    }
}
