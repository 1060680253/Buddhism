package com.yuanming.buddhism.module.mine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseRecyclerAdapter;
import com.yuanming.buddhism.entity.CountLog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenghuan on 2016/11/4.
 * on phyt company
 */

public class CollectionAdapter extends BaseRecyclerAdapter<CountLog> {
    private boolean isShowDelet = false;

    public  void setShowDelet(boolean isShowDelet){
        this.isShowDelet = isShowDelet;
        notifyDataSetChanged();
    }

    public CollectionAdapter(RecyclerView context) {
        super(context);
    }
    @Override
    public int getItemViewType(int position) {
        if ("1".equals(mList.get(position).getMantra_type())) {
            return 1;
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==1){
            return new ViewHolder1(mInflater.inflate(R.layout.list_item_collection_1, parent, false));
        }else{
            return new ViewHolder2(mInflater.inflate(R.layout.list_item_collection_2, parent, false));
        }
    }

    @Override
    public void onBindViewHolders(RecyclerView.ViewHolder holder, int position) {
        if ("1".equals(mList.get(position).getMantra_type())) {
            ViewHolder1 viewHolder1 = (ViewHolder1)holder;
            viewHolder1.delete.setVisibility(isShowDelet?View.VISIBLE:View.GONE);
        } else {
            ViewHolder2 viewHolder2 = (ViewHolder2)holder;
            viewHolder2.delete.setVisibility(isShowDelet?View.VISIBLE:View.GONE);
        }
    }

    static class ViewHolder1 extends RecyclerView.ViewHolder{
        @BindView(R.id.delete)
        TextView delete;

        ViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder{
        @BindView(R.id.delete)
        TextView delete;

        ViewHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
