package com.yuanming.buddhism.module.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseRecyclerAdapter;
import com.yuanming.buddhism.base.BaseRecyclerViewHolder;
import com.yuanming.buddhism.entity.CountLog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenghuan on 2016/11/4.
 * on phyt company
 */

public class CountLogsAdapter extends BaseRecyclerAdapter<CountLog> {

    public CountLogsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.list_item_count_logs, parent, false));
    }

    @Override
    public void onBindViewHolders(BaseRecyclerViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        CountLog countLog = mList.get(position);
        if(countLog==null){
            throw new RuntimeException("CountLogsAdapter inner Countlog is null");
        }
        viewHolder.tvCounts.setText(String.format(mContext.getResources().getString(R.string.counts_log_count),countLog.getRecite_count()));
        viewHolder.tvTime.setText(countLog.getTime());
        viewHolder.tvPledge.setText(countLog.getPersonal_pledge());
        viewHolder.tvType.setText(countLog.getRecite_type());
    }

    static class ViewHolder extends BaseRecyclerViewHolder{
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_counts)
        TextView tvCounts;
        @BindView(R.id.tv_pledge)
        TextView tvPledge;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
