package com.abo.news.news.model;

import android.util.Log;

import com.abo.news.Http.OkHttpUtils;
import com.abo.news.Http.Url;
import com.abo.news.beans.NewsBean;
import com.abo.news.news.widget.NewsFragment;
import com.abo.news.news.widget.NewsJsonUtil;

import java.util.List;

/**
 * Created by abo on 16/8/9.
 */
public class NewModelImpl implements NewModel {
    @Override
    public void loadNews(String url,final int type,final OnLoadNewListListener onLoadNewListListener ){
        OkHttpUtils.ResultCallback<String> resultCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<NewsBean> newsBeanList = NewsJsonUtil.readJsonNewsBeans(response,getId(type));
                onLoadNewListListener.onSuccess(newsBeanList);
                Log.d("bbbbbb","bbbaaa");

            }

            @Override
            public void onFailure(Exception e) {
                onLoadNewListListener.onFailure("load news list failure",e);
            }
        };
        OkHttpUtils.get(url, resultCallback);
    }

    private String getId(int type) {
        String id;
        switch (type){
            case NewsFragment.NEWS_TYPE_TOP:
                id = Url.TOP_ID;
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                id = Url.NBA_ID;
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                id = Url.CAR_ID;
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                id = Url.JOKE_ID;
                break;
            default:
                id = Url.TOP_ID;
        }
        return id;
    }


    public interface OnLoadNewListListener{
        void onSuccess(List<NewsBean> list);
        void onFailure(String msg,Exception e);
    }
}
