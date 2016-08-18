package com.abo.news.image.view;

import com.abo.news.beans.ImageBean;

import java.util.List;

/**
 * Created by abo on 16/8/17.
 */
public interface imageView {
    void addImage(List<ImageBean> list);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg();
}
