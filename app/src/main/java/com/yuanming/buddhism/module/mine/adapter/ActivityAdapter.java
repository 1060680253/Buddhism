package com.yuanming.buddhism.module.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseRecyclerAdapter;
import com.yuanming.buddhism.entity.CountLog;

import butterknife.ButterKnife;

/**
 * Created by chenghuan on 2016/11/4.
 * on phyt company
 */

public class ActivityAdapter extends BaseRecyclerAdapter<CountLog> {

    public ActivityAdapter(RecyclerView context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.list_item_activity, parent, false));
    }

    @Override
    public void onBindViewHolders(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        CountLog countLog = mList.get(position);
        if(countLog==null){
            throw new RuntimeException("CountLogsAdapter inner Countlog is null");
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder{


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
