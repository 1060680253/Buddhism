package com.yuanming.buddhism.module.mine.fragment;

import android.view.View;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BasePresenter;
import com.yuanming.buddhism.base.BaseRecycleFragment;
import com.yuanming.buddhism.entity.CountLog;
import com.yuanming.buddhism.entity.JsonList;
import com.yuanming.buddhism.module.mine.adapter.ThingsAdapter;

import java.util.List;

/**
 * Created by chenghuan on 2016/11/24.
 * on phyt company
 */

public class ThingsFragment extends BaseRecycleFragment<ThingsAdapter,JsonList<CountLog>,CountLog,BasePresenter> {

    @Override
    protected void requestData() {
        onSuccess(null);
    }

    @Override
    protected ThingsAdapter getAdapter() {
        return new ThingsAdapter(mRecyclerView);
    }

    @Override
    protected List<CountLog> handleReciveData(JsonList<CountLog> result) {
        return CountLog.getDatas();
    }

    @Override
    protected View getHeadView() {
        View headView = mInflater.inflate(R.layout.view_header_my_tings,null);
        return headView;
    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}
