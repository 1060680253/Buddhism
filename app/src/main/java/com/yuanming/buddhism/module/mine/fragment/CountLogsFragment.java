package com.yuanming.buddhism.module.mine.fragment;

import android.view.View;

import com.yuanming.buddhism.base.BasePresenter;
import com.yuanming.buddhism.base.BaseRecycleFragment;
import com.yuanming.buddhism.entity.CountLog;
import com.yuanming.buddhism.entity.JsonList;
import com.yuanming.buddhism.module.mine.adapter.CountLogsAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public class CountLogsFragment extends BaseRecycleFragment<CountLogsAdapter, JsonList<CountLog>, CountLog, BasePresenter> {


    @Override
    protected void requestData() {
        onSuccess(null);
    }

    @Override
    protected CountLogsAdapter getAdapter() {
        return new CountLogsAdapter(mRecyclerView);
    }

    @Override
    protected List<CountLog> handleReciveData(JsonList<CountLog> result) {
        return CountLog.getDatas();
    }

    @Override
    public void onItemClick(View view, int postion) {

    }

}
