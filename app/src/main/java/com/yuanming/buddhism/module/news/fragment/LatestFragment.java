package com.yuanming.buddhism.module.news.fragment;

import android.view.View;
import android.widget.ImageView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BasePresenter;
import com.yuanming.buddhism.base.BaseRecycleFragment;
import com.yuanming.buddhism.entity.CountLog;
import com.yuanming.buddhism.entity.JsonList;
import com.yuanming.buddhism.http.img.PictureLoader;
import com.yuanming.buddhism.module.news.adapter.LatestNewsAdapter;
import com.yuanming.buddhism.widget.imagecycle.ADInfo;
import com.yuanming.buddhism.widget.imagecycle.ImageCycleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenghuan on 2016/11/28.
 * on phyt company
 */

public class LatestFragment extends BaseRecycleFragment<LatestNewsAdapter,JsonList<CountLog>,CountLog,BasePresenter> {

    @Override
    protected void requestData() {
        onSuccess(null);
    }

    @Override
    protected LatestNewsAdapter getAdapter() {
        return new LatestNewsAdapter(mRecyclerView);
    }

    @Override
    protected List<CountLog> handleReciveData(JsonList<CountLog> result) {
        return CountLog.getDatas();
    }

    @Override
    public void onItemClick(View view, int postion) {

    }
    private ImageCycleView mAdView;
    @Override
    protected View getHeadView() {
        View headView = mInflater.inflate(R.layout.view_header_latest_news,null);
        mAdView = (ImageCycleView)headView.findViewById(R.id.ad_view);
        mAdView.setRefreshLayout(mSwipeRefreshLayout);
        ArrayList<ADInfo> adInfos = new ArrayList<>();
        adInfos.add(new ADInfo("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2047421984,4261114811&fm=116&gp=0.jpg"));
        adInfos.add(new ADInfo("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=248160052,2154302438&fm=116&gp=0.jpg"));
        mAdView.setImageResources(adInfos, mAdCycleViewListener);
        return headView;
    }

    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {

        }

        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            PictureLoader.getInstance().displayImgOriginal(imageView, imageURL);// 使用ImageLoader对图片进行加装！
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        if(mAdView!=null){
            mAdView.startImageCycle();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if(mAdView!=null) {
            mAdView.pushImageCycle();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mAdView!=null) {
            mAdView.pushImageCycle();
        }
    }
}
