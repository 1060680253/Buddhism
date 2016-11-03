package com.yuanming.buddhism.module.main.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;

import com.brioal.bottomtab.entity.TabEntity;
import com.brioal.bottomtab.interfaces.OnTabSelectedListener;
import com.brioal.bottomtab.view.BottomLayout;
import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseActivity;
import com.yuanming.buddhism.module.main.fragment.MainFragment;
import com.yuanming.buddhism.module.main.fragment.MineFragment;
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
        fragments.put(3,new MineFragment());
        final ArrayList<TabEntity> mList = new ArrayList<>();
        mList.add(new TabEntity(R.mipmap.ic_launcher, "首页"));
        mList.add(new TabEntity(R.mipmap.ic_launcher, "圈子"));
        mList.add(new TabEntity(R.mipmap.ic_launcher, "发现"));
        mList.add(new TabEntity(R.mipmap.ic_launcher, "我的"));
        mBottomLayout.setList(mList); //设置数据源
        mBottomLayout.setNews(5, 3);
        mTvActionTitle.setText(mList.get(0).getText());
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
                mTvActionTitle.setText(mList.get(position).getText());
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
