package com.yuanming.buddhism.module.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseRecyclerAdapter;
import com.yuanming.buddhism.entity.CountLog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenghuan on 2016/11/28.
 * on phyt company
 */

public class VideoNewsAdapter extends BaseRecyclerAdapter<CountLog> {
    private ViewGroup rootView;
    private OrientationUtils orientationUtils;
    public final static String TAG = "TT2";

    private boolean isFullVideo;

    private ListVideoUtil listVideoUtil;
    public VideoNewsAdapter(RecyclerView context, ListVideoUtil listVideoUtil) {
        super(context);
        this.listVideoUtil = listVideoUtil;
    }
    @Override
    protected void onBindViewHolders(RecyclerView.ViewHolder viewHolder, final int position) {
        final ViewHolder holder = (ViewHolder)viewHolder;
        //增加封面
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.drawable.fojiao);
        holder.gsyVideoPlayer.setThumbImageView(imageView);

        final String url = "http://baobab.wdjcdn.com/14564977406580.mp4";

        //默认缓存路径
        holder.gsyVideoPlayer.setUp(url, true , "");
        //增加title
        holder.gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);

        //设置返回键
        holder.gsyVideoPlayer.getBackButton().setVisibility(View.GONE);

        //设置全屏按键功能
        holder.gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolveFullBtn(holder.gsyVideoPlayer);
            }
        });
        holder.gsyVideoPlayer.setRotateViewAuto(true);
        holder.gsyVideoPlayer.setLockLand(true);
        holder.gsyVideoPlayer.setPlayTag(TAG);
        holder.gsyVideoPlayer.setShowFullAnimation(true);
        //循环
        holder.gsyVideoPlayer.setLooping(true);
        holder.gsyVideoPlayer.setPlayPosition(position);
        holder.gsyVideoPlayer.setStandardVideoAllCallBack(sampleListener);
    }

    //小窗口关闭被点击的时候回调处理回复页面
    SampleListener sampleListener = new SampleListener() {
        @Override
        public void onPrepared(String url, Object... objects) {
            super.onPrepared(url, objects);
            Debuger.printfLog("onPrepared");
        }

        @Override
        public void onQuitSmallWidget(String url, Object... objects) {
            super.onQuitSmallWidget(url, objects);
            Debuger.printfLog("onQuitSmallWidget");
        }

        @Override
        public void onClickBlankFullscreen(String url, Object... objects) {
            super.onClickBlankFullscreen(url, objects);
            Debuger.printfLog("onClickBlankFullscreen");
        }

        @Override
        public void onEnterFullscreen(String url, Object... objects) {
            super.onEnterFullscreen(url, objects);
            Debuger.printfLog("onEnterFullscreen");
        }
    };

    /**
     * 全屏幕按键处理
     */
    private void resolveFullBtn(final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(mContext, false, true);
        isFullVideo = true;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.list_item_video, parent, false));
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.video_item_player)
        StandardGSYVideoPlayer gsyVideoPlayer;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
