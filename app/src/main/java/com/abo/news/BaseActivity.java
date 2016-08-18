package com.abo.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;




/**
 * Created by abo on 16/8/12.
 */
public class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    protected Toolbar mToolbar;
    protected TextView toolbar_title;

    public static final int MODE_BACK = 0;
    public static final int MODE_DRAWER = 1;
    public static final int MODE_NONE = 2;
    public static final int MODE_HOME = 3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpData();
    }

    protected void setUpData() {
    }

    @Override
    public void setContentView( int layoutResID) {
        setContentView(layoutResID,-1,-1,MODE_BACK);
    }

    public void setContentView( int layoutResID,int titleResID) {
        setContentView(layoutResID,titleResID);
    }

    public void setContentView( int layoutResID,int titleResID,int menuID) {
        setContentView(layoutResID,titleResID,menuID);
    }

    public void setContentView(int layoutResID, int titleResID, int menuID, int mode){
        super.setContentView(layoutResID);
        setUpToolbar(titleResID,menuID,mode);
    }

    protected void setUpToolbar(int titleResID,int menuID,int mode) {
        if (mode != MODE_NONE){
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mode == MODE_BACK){
                setSupportActionBar(mToolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
            else if(mode == MODE_DRAWER){
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                     @Override
                    public void onClick(View v) {
                        onNavigationBtnClicked();
                    }
                });
            }
        }
        setUpTitle(titleResID);
        setUpMenu(menuID);
    }

    protected void setUpMenu(int menuID) {
        mToolbar.inflateMenu(menuID);
        mToolbar.setOnMenuItemClickListener(this);
    }

    protected void onNavigationBtnClicked() {
        finish();
    }

    protected void setUpTitle(int titleResId) {
        if (titleResId > 0 && toolbar_title != null) {
            toolbar_title.setText(titleResId);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
