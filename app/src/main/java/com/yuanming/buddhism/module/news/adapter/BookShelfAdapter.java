package com.yuanming.buddhism.module.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseRecyclerAdapter;
import com.yuanming.buddhism.entity.CountLog;

import butterknife.ButterKnife;

/**
 * Created by chenghuan on 2016/11/29.
 * on phyt company
 */

public class BookShelfAdapter extends BaseRecyclerAdapter<CountLog> {

    public BookShelfAdapter(RecyclerView context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position%3==0) {
            return 1;
        } else if(position%3==2){
            return 2;
        }else {
            return super.getItemViewType(position);
        }
    }

    @Override
    protected void onBindViewHolders(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==1){
            return new ViewHolderRight(mInflater.inflate(R.layout.list_item_shelf_left, parent, false));
        }else if(viewType == 2){
            return new ViewHolderLeft(mInflater.inflate(R.layout.list_item_shelf_right, parent, false));
        }else{
            return new ViewHolder(mInflater.inflate(R.layout.list_item_shelf, parent, false));
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder{


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderRight extends RecyclerView.ViewHolder{


        ViewHolderRight(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderLeft extends RecyclerView.ViewHolder{


        ViewHolderLeft(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
