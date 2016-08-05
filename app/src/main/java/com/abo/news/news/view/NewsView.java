package com.abo.news.news.view;

import com.abo.news.beans.news;

import java.util.List;

/**
 * Created by abo on 16/8/5.
 * 显示进度条；
 * 添加新闻列表；
 * 加载成功隐藏进度条；
 * 加载失败提醒；
 */
public interface NewsView {
    void showProgress();
    void addNews(List<news> newsList);
    void hideProgress();
    void showLoadFail();
}
