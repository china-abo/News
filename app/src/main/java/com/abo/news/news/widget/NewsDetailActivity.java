package com.abo.news.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.abo.news.Http.ImageLoaderUtils;
import com.abo.news.R;
import com.abo.news.beans.NewsBean;
import com.abo.news.news.presenter.NewsDetailPresenter;
import com.abo.news.news.presenter.NewsDetailPresenterImpl;
import com.abo.news.news.view.NewsDetail;

import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * Created by abo on 16/8/4.
 */
public class NewsDetailActivity extends AppCompatActivity implements NewsDetail {

    private NewsBean mNewsBean;
    private NewsDetailPresenter mNewsDetailPresenter;
    private ProgressBar mProgressBar;
    private HtmlTextView mHtmlTextView;
    private Toolbar toolbar;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mHtmlTextView = (HtmlTextView) findViewById(R.id.htNewsContent);
        setSupportActionBar(toolbar);
        //设置新闻返回按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //获取新闻列表标题
        mNewsBean = (NewsBean) getIntent().getSerializableExtra("news");

        //设置标题
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collasping_toolbar);
        collapsingToolbarLayout.setTitle(mNewsBean.getTitle());

        //加载图片
        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.img_toolar),mNewsBean.getImgsrc());

        mNewsDetailPresenter = new NewsDetailPresenterImpl(getApplicationContext(),this);
        mNewsDetailPresenter.loadNewsDetail(mNewsBean.getDocid());


    }



//    @Override
//    protected void setUpData() {
//        setContentView(R.layout.activity_news_detail,R.menu.menu_main,MODE_BACK);
//        mProgressBar = (ProgressBar) findViewById(R.id.progress);
//        mHtmlTextView = (HtmlTextView) findViewById(R.id.htNewsContent);
////        setSupportActionBar(mToolbar);
//        //获取新闻列表标题
//        mNewsBean = (NewsBean) getIntent().getSerializableExtra("news");
//        //设置标题
//        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collasping_toolbar);
//        collapsingToolbarLayout.setTitle(mNewsBean.getTitle());
//        //加载图片
//        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.img_toolar),mNewsBean.getImgsrc());
//        mNewsDetailPresenter = new NewsDetailPresenterImpl(getApplicationContext(),this);
//        mNewsDetailPresenter.loadNewsDetail(mNewsBean.getDocid());
//    }

    @Override
    public void showNewsDetails(String newsDetail) {
        mHtmlTextView.setHtmlFromString(newsDetail,new HtmlTextView.LocalImageGetter());

    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
