package com.yuanming.buddhism;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;

import com.brioal.bottomtab.entity.TabEntity;
import com.brioal.bottomtab.interfaces.OnTabSelectedListener;
import com.brioal.bottomtab.view.BottomLayout;
import com.yuanming.buddhism.base.BaseActivity;
import com.yuanming.buddhism.widget.ZoomOutPageTransformer;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.vp_horizontal_ntb)
    ViewPager viewPager;
    @BindView(R.id.main_tab)
    BottomLayout mBottomLayout;
    private SparseArray<Fragment> fragments;

    @Override
    public void initView() {
        fragments = new SparseArray<>();
        fragments.put(0,new MainFragment());
        fragments.put(1,new MainFragment());
        fragments.put(2,new MainFragment());
        fragments.put(3,new MainFragment());
        fragments.put(4,new MainFragment());
        ArrayList mList = new ArrayList<>();
        mList.add(new TabEntity(R.mipmap.ic_launcher, "推荐"));
        mList.add(new TabEntity(R.mipmap.ic_launcher, "游戏"));
        mList.add(new TabEntity(R.mipmap.ic_launcher, "软件"));
        mList.add(new TabEntity(R.mipmap.ic_launcher, "应用圈"));
        mList.add(new TabEntity(R.mipmap.ic_launcher, "管理"));
        mBottomLayout.setList(mList); //设置数据源
        mBottomLayout.setNews(5, 4);
        //设置Item点击事件
        mBottomLayout.setSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onSelected(int position) {
                viewPager.setCurrentItem(position,true);
                mBottomLayout.cleanNews(position); //清除未读消息
            }
        });
        viewPager.setAdapter(mAdapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                mBottomLayout.setCurrentIndex(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragments.get(arg0);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
