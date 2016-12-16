package com.yuanming.buddhism.module.finding.fragment;

import android.view.View;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BasePresenter;
import com.yuanming.buddhism.base.BaseRecycleFragment;
import com.yuanming.buddhism.entity.CopyItem;
import com.yuanming.buddhism.entity.CountLog;
import com.yuanming.buddhism.entity.JsonList;
import com.yuanming.buddhism.module.finding.adapter.CopyScriptureAdapter;

import java.util.List;

/**
 * Created by chenghuan on 2016/12/16.
 * on phyt company
 */

public class CopyScriptureFragment extends BaseRecycleFragment<CopyScriptureAdapter,JsonList<CopyItem>,CopyItem,BasePresenter>{

    @Override
    protected void requestData() {
        onSuccess(null);
    }

    @Override
    protected CopyScriptureAdapter getAdapter() {
        return new CopyScriptureAdapter(mRecyclerView);
    }

    @Override
    protected List<CopyItem> handleReciveData(JsonList<CopyItem> result) {
        return CopyItem.strTransferList(getString(R.string.large_text));
    }

    @Override
    public void onItemClick(View view, int postion) {

    }
}
