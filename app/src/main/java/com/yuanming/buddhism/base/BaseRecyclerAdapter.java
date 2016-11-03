package com.yuanming.buddhism.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.yuanming.buddhism.interf.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder>{
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

    public BaseRecyclerAdapter(Context context) {
        this.mContext=context;
        mInflater = LayoutInflater.from(context);
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
    public void onViewDetachedFromWindow(BaseRecyclerViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }
}