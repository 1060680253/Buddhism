package com.yuanming.buddhism.module.main.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;
import com.yuanming.buddhism.base.BaseRecycleFragment;
import com.yuanming.buddhism.module.login.LoginActvity;
import com.yuanming.buddhism.module.login.LoginDialog;
import com.yuanming.buddhism.module.news.fragment.LatestFragment;
import com.yuanming.buddhism.module.news.fragment.MusicFragment;
import com.yuanming.buddhism.module.news.fragment.PagesFragment;
import com.yuanming.buddhism.module.news.fragment.ShelfFragment;
import com.yuanming.buddhism.module.news.fragment.VideoNewsFragment;
import com.yuanming.buddhism.widget.ENSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenghuan on 2016/11/28.
 * on phyt company
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.materialup_tabs)
    TabLayout pagerStrip;
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.search_badge)
    ENSearchView search_badge;

    @Override
    public void initView(View view) {
        List<String> titles = new ArrayList<>();
        titles.add("最新");
        titles.add("文章");
        titles.add("图书");
        titles.add("音频");
        titles.add("视频");
        TabPagerAdapter mPagerAdapter = new TabPagerAdapter(getChildFragmentManager(), titles);
        mViewPager.setAdapter(mPagerAdapter);
        pagerStrip.setupWithViewPager(mViewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @OnClick(R.id.search_badge)
    public void onClick() {
        search_badge.start();
        search_badge.setAnimationListener(new ENSearchView.AnimationEndListener() {
            @Override
            public void animationEnd(View view) {
                startActivity(new Intent(mView.getContext(), LoginActvity.class));
            }
        });

//        LoginDialog loginDialog = new LoginDialog(mView.getContext());
//        loginDialog.show();
    }

    private class TabPagerAdapter extends FragmentPagerAdapter {

        FragmentManager fm;
        private List<String> titles;

        public TabPagerAdapter(FragmentManager fm, List<String> title) {
            super(fm);
            this.titles = title;
            this.fm = fm;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            return getFragment(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    private SparseArray<BaseRecycleFragment> sparseArray;

    private Fragment getFragment(int index) {
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
        }
        BaseRecycleFragment recycleFragment = sparseArray.get(index);
        if (recycleFragment != null) {
            return recycleFragment;
        }
        if (index == 1) {
            recycleFragment = new PagesFragment();
        } else if (index == 2) {
            recycleFragment = new ShelfFragment();
        } else if (index == 3) {
            recycleFragment = new MusicFragment();
        } else if (index == 4) {
            recycleFragment = new VideoNewsFragment();
        } else {
            recycleFragment = new LatestFragment();
        }
        sparseArray.put(index, recycleFragment);
        return recycleFragment;
    }
}
