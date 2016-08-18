package com.abo.news.image.model;

import com.abo.news.Http.OkHttpUtils;
import com.abo.news.Http.Url;
import com.abo.news.beans.ImageBean;
import com.abo.news.image.imageJsonUtil;

import java.util.List;

/**
 * Created by abo on 16/8/17.
 */
public class imageModelImpl implements imageModel {


    /**
     * 获取图片信息
     * @param listener
     */
    @Override
    public void loadImageList(final OnLoadImageListListener listener) {
        String url = Url.IMAGES_URL;
        OkHttpUtils.ResultCallback<String> resultCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<ImageBean> imageBeen = imageJsonUtil.readJsonImageBeans(response);
                listener.Onsuccess(imageBeen);
            }

            @Override
            public void onFailure(Exception e) {
                listener.OnFailure("load iamge failure",e);
            }
        };
        OkHttpUtils.get(url,resultCallback);
    }

    public interface OnLoadImageListListener{
        void Onsuccess(List<ImageBean> list);
        void OnFailure(String msg, Exception e);
    }
}
