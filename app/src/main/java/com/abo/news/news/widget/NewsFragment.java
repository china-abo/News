package com.abo.news.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abo.news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abo on 16/8/4.
 */
public class NewsFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news,null);
        mTabLayout = (TabLayout) v.findViewById(R.id.news_tab_layout);
        mViewPager = (ViewPager) v.findViewById(R.id.news_view_page);

        setupViewPager(mViewPager);

        return v;
    }

    /**
     * @param mViewPager
     */
    private void setupViewPager(ViewPager mViewPager){
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
//        adapter.AddFragment();
    }

    /**
     *
     */
    public static class MyPagerAdapter extends FragmentPagerAdapter{

        private List<Fragment> mFragments = new ArrayList<>();
        private List<String> mFragmentTitle = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void AddFragment(Fragment fragment,String title){
            mFragments.add(fragment);
            mFragmentTitle.add(title);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitle.get(position);
        }
    }
}
