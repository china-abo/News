package com.abo.news.image.persenter;

import com.abo.news.beans.ImageBean;
import com.abo.news.image.model.imageModel;
import com.abo.news.image.model.imageModelImpl;
import com.abo.news.image.view.imageView;

import java.util.List;

/**
 * Created by abo on 16/8/17.
 */
public class imagePersenterImpl implements imagePersenter, imageModelImpl.OnLoadImageListListener {

    private imageModel mImageModel;
    private imageView mImageView;

    public imagePersenterImpl(imageView img){
        this.mImageView = img;
        this.mImageModel = new imageModelImpl();
    }

    @Override
    public void loadImageList() {
        mImageView.showProgress();
        mImageModel.loadImageList(this);

    }

    @Override
    public void Onsuccess(List<ImageBean> list) {
        mImageView.addImage(list);
        mImageView.hideProgress();
    }

    @Override
    public void OnFailure(String msg, Exception e) {
        mImageView.hideProgress();
        mImageView.showLoadFailMsg();
    }
}
