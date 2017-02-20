package com.abo.news;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.abo.news.image.imageFragment;
import com.abo.news.news.widget.NewsFragment;

public class MainActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
        //建立Drawer
        switch2News();
    }


    @Override
    protected void setUpData() {
        setSupportActionBar(mToolbar);
        setContentView(R.layout.activity_main,R.string.home_title, R.menu.menu_main,MODE_DRAWER);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,
                R.string.drawer_open,R.string.drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        setupDrawerContent(mNavigationView);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_item_news:
                        switch2News();
                        break;
                    case R.id.navigation_item_images:
                        switch2Image();
                        break;
                    case R.id.navigation_item_about:
                        switch2About();
                        break;
                    default:
                        switch2News();
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

//    /**
//     * 设置menu键
//     * @param menu
//     * @return
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main,menu);
//        return true;
//    }

    private void switch2About() {
    }

    private void switch2Image() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new imageFragment()).commit();
    }

    private void switch2News() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new NewsFragment()).commit();
    }
}
