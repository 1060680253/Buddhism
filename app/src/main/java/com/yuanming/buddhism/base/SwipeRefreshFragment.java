package com.yuanming.buddhism.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.interf.HttpRequestListener;
import com.yuanming.buddhism.widget.EmptyLayout;

import butterknife.BindView;

/**
 * Created by chenghuan on 2016/11/21.
 * on phyt company
 */

public abstract class SwipeRefreshFragment<T extends BasePresenter,E extends BaseEntity> extends BaseFragment<T> implements SwipeRefreshLayout.OnRefreshListener,HttpRequestListener<E> {
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.error_layout)
    EmptyLayout mErrorLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_refresh;
    }

    protected abstract int getRefreshLayoutId();

    @Override
    protected void inJectChildView(View viewGroup) {
        mInflater.inflate(getRefreshLayoutId(),(FrameLayout)viewGroup.findViewById(R.id.container));
    }

    @Override
    public void initView(View view) {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);
        mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                requestData();
            }
        });
    }

    @Override
    public void onRefresh() {
        setSwipeRefreshLoadingState();
        requestData();
    }

    /**
     * 设置顶部正在加载的状态
     */
    private void setSwipeRefreshLoadingState() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(true);
            // 防止多次重复刷新
            mSwipeRefreshLayout.setEnabled(false);
        }
    }

    /**
     * 设置顶部加载完毕的状态
     */
    private void setSwipeRefreshLoadedState() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            mSwipeRefreshLayout.setEnabled(true);
        }
    }

    @Override
    public void onSuccess(E result) {
        if(!isVisible()){
            return;
        }
        mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        setSwipeRefreshLoadedState();
    }

    @Override
    public void onError(int state, String error) {
        if(!isVisible()){
            return;
        }
        setSwipeRefreshLoadedState();
        if(mErrorLayout.isShown()){
            mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
        }
    }

    protected abstract void requestData();

}
