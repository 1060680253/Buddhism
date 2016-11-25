package com.yuanming.buddhism.module.mine.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenghuan on 2016/11/24.
 * on phyt company
 */

public class ActivityFragment extends BaseFragment {
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.materialup_tabs)
    TabLayout pagerStrip;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_activity;
    }

    @Override
    public void initView(View view) {
        List<String> titles = new ArrayList<>();
        titles.add("参加");
        titles.add("发布");
        TabPagerAdapter mPagerAdapter = new TabPagerAdapter(getChildFragmentManager(), titles);
        mViewPager.setAdapter(mPagerAdapter);
        pagerStrip.setupWithViewPager(mViewPager);
    }

    @OnClick({R.id.iv_back, R.id.iv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if(getFActivity()!=null){
                    getFActivity().finish();
                }
                break;
            case R.id.iv_add:
                break;
        }
    }

    public class TabPagerAdapter extends FragmentPagerAdapter {

        FragmentManager fm;
        private List<String> titles;

        public TabPagerAdapter(FragmentManager fm, List<String> title) {
            super(fm);
            this.titles = title;
            this.fm = fm;
        }

        @Override
        public int getCount() {
            return 2;
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

    private Fragment getFragment(int index) {
        return new ActivityListFragment();
    }
}
