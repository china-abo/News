package com.abo.news.image.model;

import com.abo.news.image.model.imageModelImpl.OnLoadImageListListener;

/**
 * Created by abo on 16/8/17.
 */
public interface imageModel  {
    void loadImageList(OnLoadImageListListener listener);
}
