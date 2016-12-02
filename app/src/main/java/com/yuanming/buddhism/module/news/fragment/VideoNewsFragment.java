package com.yuanming.buddhism.module.news.fragment;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.FrameLayout;

import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;
import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BasePresenter;
import com.yuanming.buddhism.base.BaseRecycleFragment;
import com.yuanming.buddhism.entity.CountLog;
import com.yuanming.buddhism.entity.JsonList;
import com.yuanming.buddhism.module.news.adapter.HorizontalPagerAdapter;
import com.yuanming.buddhism.module.news.adapter.MusicNewsAdapter;
import com.yuanming.buddhism.module.news.adapter.VideoNewsAdapter;
import com.yuanming.buddhism.util.TDevice;
import com.yuanming.buddhism.widget.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.List;

import butterknife.BindView;

/**
 * Created by chenghuan on 2016/11/28.
 * on phyt company
 */

public class VideoNewsFragment extends BaseRecycleFragment<VideoNewsAdapter,JsonList<CountLog>,CountLog,BasePresenter> {
    @BindView(R.id.video_full_container)
    FrameLayout videoFullContainer;
    ListVideoUtil listVideoUtil;
    int lastVisibleItem;
    int firstVisibleItem;
    @Override
    public void initView(View view) {
        listVideoUtil = new ListVideoUtil(view.getContext());
        listVideoUtil.setFullViewContainer(videoFullContainer);
        listVideoUtil.setHideStatusBar(true);
        super.initView(view);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView view, int scrollState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                getLayoutManager().findFirstVisibleItemPositions(null);
            }

        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler_video;
    }

    @Override
    protected void requestData() {
        onSuccess(null);
    }

    @Override
    protected VideoNewsAdapter getAdapter() {
        return new VideoNewsAdapter(mRecyclerView,listVideoUtil);
    }

    @Override
    protected List<CountLog> handleReciveData(JsonList<CountLog> result) {
        return CountLog.getDatas();
    }

    @Override
    protected View getHeadView() {
        View headView = mInflater.inflate(R.layout.view_header_video,null);
        HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) headView.findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(getContext()));
        return headView;
    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}
