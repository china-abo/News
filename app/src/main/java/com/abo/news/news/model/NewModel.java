package com.abo.news.news.model;

/**
 * Created by abo on 16/8/8.
 */
public interface NewModel {

    void loadNews(String url, int type,NewModelImpl.OnLoadNewListListener listener);

    void loadNewsDetail(String docid,NewModelImpl.OnLoadNewDetailListener listener);
}
