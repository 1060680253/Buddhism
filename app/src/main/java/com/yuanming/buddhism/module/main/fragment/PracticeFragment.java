package com.yuanming.buddhism.module.main.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;
import com.yuanming.buddhism.entity.MsgEvent;
import com.yuanming.buddhism.module.main.activity.CommonActivity;
import com.yuanming.buddhism.module.main.activity.CommonPage;
import com.yuanming.buddhism.widget.VerticalViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenghuan on 2016/12/3.
 * on phyt company
 */

public class PracticeFragment extends BaseFragment {

    @BindView(R.id.view_pager)
    VerticalViewPager viewPager;

    public static Fragment instance(int inx){
        if(inx==0){
            return new PracticeTopFragment();
        }else{
            return new PracticeBottomFragment();
        }
    }
    @OnClick(R.id.iv_partice)
    public void onClick() {
        CommonActivity.startActivity(mView.getContext(), CommonPage.COPYSCRIPTURE);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_practice;
    }

    @Override
    public void initView(View view) {
        viewPager.setAdapter(new VerticalPagerAdapter(getChildFragmentManager()));
    }

    public class VerticalPagerAdapter extends FragmentStatePagerAdapter {
        public VerticalPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PracticeFragment.instance(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
