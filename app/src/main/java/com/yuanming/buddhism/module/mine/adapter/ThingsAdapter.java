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

public class ThingsAdapter extends BaseRecyclerAdapter<CountLog> {

    public ThingsAdapter(RecyclerView context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        if ("1".equals(mList.get(position).getMantra_type())) {
            return 1;
        } else if("2".equals(mList.get(position).getMantra_type())){
            return 2;
        }else{
            return super.getItemViewType(position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==1){
            return new ViewHolder1(mInflater.inflate(R.layout.list_item_things, parent, false));
        }else if(viewType == 2){
            return new ViewHolder2(mInflater.inflate(R.layout.list_item_things_2, parent, false));
        }else{
            return new ViewHolder3(mInflater.inflate(R.layout.list_item_things_3, parent, false));
        }
    }

    @Override
    public void onBindViewHolders(RecyclerView.ViewHolder holder, int position) {
    }

    static class ViewHolder1 extends RecyclerView.ViewHolder{


        ViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder{


        ViewHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder3 extends RecyclerView.ViewHolder{


        ViewHolder3(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
