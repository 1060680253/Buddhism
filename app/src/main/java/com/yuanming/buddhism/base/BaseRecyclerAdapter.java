package com.yuanming.buddhism.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.yuanming.buddhism.interf.RecyclerItemClickListener;
import com.yuanming.buddhism.widget.recycler.RecyclerViewUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T extends BaseEntity> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    protected Context mContext;
    protected List<T> mList =new ArrayList<>();
    protected LayoutInflater mInflater;
    private int lastPosition = -1;

    public List<T> getList() {
        return mList;
    }

    public void addListNotify(List<T> mList){
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }

    public void removed(T t){
        this.mList.remove(t);
        notifyDataSetChanged();
    }

    public BaseRecyclerAdapter(RecyclerView context) {
        this.mRecyclerView=context;
        this.mContext = context.getContext();
        mInflater = LayoutInflater.from(context.getContext());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public T getItem(int postsion){
        return mList.get(postsion);
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }
    protected RecyclerItemClickListener mItemClickListener;
    public void setOnItemClickListener(RecyclerItemClickListener listener){
        this.mItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(mItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(view,position);
                }
            });
        }
        onBindViewHolders(holder,position);
    }

    protected abstract void onBindViewHolders(RecyclerView.ViewHolder holder, final int position);

    protected RecyclerView mRecyclerView;
    protected int mAdapterPosition = 0;
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        mAdapterPosition = RecyclerViewUtils.getAdapterPosition(mRecyclerView, holder);
    }

    public int getAdapterPosition() {
        return mAdapterPosition;
    }
}
