package com.yuanming.buddhism.module.news.fragment;

import android.view.View;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BasePresenter;
import com.yuanming.buddhism.base.BaseRecycleFragment;
import com.yuanming.buddhism.entity.CountLog;
import com.yuanming.buddhism.entity.JsonList;
import com.yuanming.buddhism.module.news.adapter.PageNewsAdapter;

import java.util.List;

/**
 * Created by chenghuan on 2016/11/28.
 * on phyt company
 */

public class PagesFragment extends BaseRecycleFragment<PageNewsAdapter,JsonList<CountLog>,CountLog,BasePresenter> {

    @Override
    protected void requestData() {
        onSuccess(null);
    }

    @Override
    protected PageNewsAdapter getAdapter() {
        return new PageNewsAdapter(mRecyclerView);
    }

    @Override
    protected List<CountLog> handleReciveData(JsonList<CountLog> result) {
        return CountLog.getDatas();
    }

    @Override
    protected int getLineNum() {
        return 3;
    }

    @Override
    protected View getHeadView() {
        View headView = mInflater.inflate(R.layout.view_header_page_news,null);
        return headView;
    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}
