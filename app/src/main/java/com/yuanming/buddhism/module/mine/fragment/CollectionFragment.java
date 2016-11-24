package com.yuanming.buddhism.module.mine.fragment;

import android.view.View;

import com.yuanming.buddhism.base.BasePresenter;
import com.yuanming.buddhism.base.BaseRecycleFragment;
import com.yuanming.buddhism.entity.CountLog;
import com.yuanming.buddhism.entity.JsonList;
import com.yuanming.buddhism.module.mine.adapter.CollectionAdapter;

import java.util.List;

/**
 * Created by chenghuan on 2016/11/24.
 * on phyt company
 */

public class CollectionFragment extends BaseRecycleFragment<CollectionAdapter,JsonList<CountLog>,CountLog,BasePresenter> {

    @Override
    protected void requestData() {
        onSuccess(null);
    }


    public void setShowDelet(boolean isShow){
        if(mAdapter!=null){
            mAdapter.setShowDelet(isShow);
        }
    }

    @Override
    protected CollectionAdapter getAdapter() {
        return new CollectionAdapter(mRecyclerView);
    }

    @Override
    protected List<CountLog> handleReciveData(JsonList<CountLog> result) {
        return CountLog.getDatas();
    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}
