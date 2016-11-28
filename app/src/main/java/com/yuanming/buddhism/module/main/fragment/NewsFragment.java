package com.yuanming.buddhism.module.main.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenghuan on 2016/11/28.
 * on phyt company
 */

public class NewsFragment extends BaseFragment {

    @BindView(R.id.materialup_tabs)
    TabLayout materialupTabs;
    @BindView(R.id.pager)
    ViewPager pager;

    @Override
    public void initView(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @OnClick(R.id.search_badge)
    public void onClick() {

    }
}
