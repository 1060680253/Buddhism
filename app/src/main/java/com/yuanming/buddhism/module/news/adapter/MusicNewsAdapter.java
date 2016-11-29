package com.yuanming.buddhism.module.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseRecyclerAdapter;
import com.yuanming.buddhism.entity.CountLog;

import butterknife.ButterKnife;

/**
 * Created by chenghuan on 2016/11/28.
 * on phyt company
 */

public class MusicNewsAdapter extends BaseRecyclerAdapter<CountLog> {

    public MusicNewsAdapter(RecyclerView context) {
        super(context);
    }

    @Override
    protected void onBindViewHolders(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.list_item_music, parent, false));
    }

    static class ViewHolder extends RecyclerView.ViewHolder{


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
