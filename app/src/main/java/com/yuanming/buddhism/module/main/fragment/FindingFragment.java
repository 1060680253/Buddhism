package com.yuanming.buddhism.module.main.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;
import com.yuanming.buddhism.module.news.adapter.HorizontalPagerAdapter;
import com.yuanming.buddhism.util.TDevice;
import com.yuanming.buddhism.widget.ObservableScrollView;
import com.yuanming.buddhism.widget.SwitcherView;
import com.yuanming.buddhism.widget.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenghuan on 2016/11/25.
 * on phyt company
 */

public class FindingFragment extends BaseFragment implements ObservableScrollView.ScrollViewListener {

    @BindView(R.id.scrollView)
    ObservableScrollView scrollView;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.add_friends)
    TextView addFriends;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.switcherView)
    SwitcherView switcherView;
    private float headerHeight;//顶部高度
    private float minHeaderHeight;//顶部最低高度，即Bar的高度

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find_out;
    }

    @Override
    public void initView(View view) {
        scrollView.setScrollViewListener(this);
        headerHeight = TDevice.dpToPixel(105);
        minHeaderHeight = TDevice.dpToPixel(45);
        HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) view.findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(getContext()));
        ArrayList<String> strs = new ArrayList<>();
        strs.add("1啥时发的啥地方是大家佛教资讯12121");
        strs.add("2啥时发的啥地方是大家佛教资讯12121");
        strs.add("3啥时发的啥地方是大家佛教资讯12121");
        switcherView.setResource(strs);
        switcherView.startRolling();
    }


    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        int baseColor = getResources().getColor(R.color.colorAccent);
        //Y轴偏移量
        float scrollY = scrollView.getScrollY();
        //变化率
        float headerBarOffsetY = headerHeight - minHeaderHeight;//Toolbar与header高度的差值
        float offset = 1 - Math.max((headerBarOffsetY - scrollY) / headerBarOffsetY, 0f);
        if (offset == 1 || offset > 1) {
            searchView.setBackground(ContextCompat.getDrawable(mView.getContext(),R.drawable.edittext_background));
            addFriends.setTextColor(ContextCompat.getColor(mView.getContext(),R.color.white));
        } else {
            searchView.setBackgroundColor(ContextCompat.getColor(mView.getContext(),R.color.transparent));
            addFriends.setTextColor(ContextCompat.getColor(mView.getContext(),R.color.import_text));
        }
        toolBar.setBackgroundColor(getColorWithAlpha(offset, baseColor));
    }

    public static int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255.0F))) << 24;
        int rgb = 16777215 & baseColor;
        return a + rgb;
    }
}
