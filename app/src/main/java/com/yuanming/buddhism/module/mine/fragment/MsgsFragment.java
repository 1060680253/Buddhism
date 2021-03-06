package com.yuanming.buddhism.module.mine.fragment;

import android.view.View;

import com.yuanming.buddhism.base.BasePresenter;
import com.yuanming.buddhism.base.BaseRecycleFragment;
import com.yuanming.buddhism.entity.CountLog;
import com.yuanming.buddhism.entity.JsonList;
import com.yuanming.buddhism.module.mine.adapter.MsgsAdapter;

import java.util.List;

/**
 * Created by chenghuan on 2016/11/24.
 * on phyt company
 */

public class MsgsFragment extends BaseRecycleFragment<MsgsAdapter,JsonList<CountLog>,CountLog,BasePresenter> {

    @Override
    protected void requestData() {
        onSuccess(null);
    }

    @Override
    protected MsgsAdapter getAdapter() {
        return new MsgsAdapter(mRecyclerView);
    }

    @Override
    protected List<CountLog> handleReciveData(JsonList<CountLog> result) {
        return CountLog.getDatas();
    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}
