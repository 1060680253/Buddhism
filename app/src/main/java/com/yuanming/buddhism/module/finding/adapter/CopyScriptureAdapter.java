package com.yuanming.buddhism.module.finding.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseRecyclerAdapter;
import com.yuanming.buddhism.entity.CopyItem;
import com.yuanming.buddhism.util.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenghuan on 2016/12/16.
 * on phyt company
 */

public class CopyScriptureAdapter extends BaseRecyclerAdapter<CopyItem> {

    public CopyScriptureAdapter(RecyclerView context) {
        super(context);
    }

    private SparseArray<TextWatcher> mWatchers = new SparseArray<>();

    @Override
    protected void onBindViewHolders(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String  str = charSequence.toString();
                if(!StringUtils.isEmpty(str)){
                    if(str.length()>=1){
                        viewHolder.etValue.setText(str.charAt(0)+"");
                    }
                    if(str.length()>=2){
                        viewHolder.etValue1.setText(str.charAt(1)+"");
                    }
                    if(str.length()>=3){
                        viewHolder.etValue2.setText(str.charAt(2)+"");
                    }
                    if(str.length()>=4){
                        viewHolder.etValue3.setText(str.charAt(3)+"");
                    }
                    if(str.length()>=5){
                        viewHolder.etValue4.setText(str.charAt(4)+"");
                    }
                    if(str.length()>=6){
                        viewHolder.etValue5.setText(str.charAt(5)+"");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        int index = position*6;
        CopyItem copyItem = mList.get(index);
        CopyItem copyItem_1 = mList.get(index+1);
        CopyItem copyItem_2 = mList.get(index+2);
        CopyItem copyItem_3 = mList.get(index+3);
        CopyItem copyItem_4 = mList.get(index+4);
        CopyItem copyItem_5 = mList.get(index+5);
        viewHolder.tvKey.setText(copyItem.getKey()+"");
        viewHolder.tvKey1.setText(copyItem_1.getKey()+"");
        viewHolder.tvKey2.setText(copyItem_2.getKey()+"");
        viewHolder.tvKey3.setText(copyItem_3.getKey()+"");
        viewHolder.tvKey4.setText(copyItem_4.getKey()+"");
        viewHolder.tvKey5.setText(copyItem_5.getKey()+"");
//        TextWatcher watcher = mWatchers.get(position);
//        if (watcher == null) {
//            watcher = new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////                    viewHolder.etValue.removeTextChangedListener(this);
//                    if (charSequence == null || StringUtils.isEmpty(charSequence.toString())) {
//
//                    } else {
//                        mList.get(position).setValue(charSequence.toString());
//                        mList.get(position).setFocus(false);
//                        viewHolder.etValue.clearFocus();
//                        if ((position + 1) < (getItemCount() - 1)) {
//                            mList.get(position + 1).setFocus(true);
//                            ViewHolder viewHolder1 = ((ViewHolder)mRecyclerView.findViewHolderForAdapterPosition(position+1));
//                            if(viewHolder1!=null){
//                                viewHolder1.etValue.requestFocus();
//                            }
//                            if (((position + 1) / 6) != (position / 6)) {
//                                mRecyclerView.getLayoutManager().scrollToPosition(position + 6);
//                            }
//                        }
//                    }
//
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable editable) {
//                }
//            };
//            mWatchers.put(position, watcher);
//        }
//        viewHolder.etValue.removeTextChangedListener(watcher);
//        viewHolder.etValue.addTextChangedListener(watcher);
//        if(copyItem.isFocus()){
//            viewHolder.etValue.requestFocus();
//        }
//        if (!StringUtils.isEmpty(copyItem.getValue())) {
//            viewHolder.etValue.setText(copyItem.getValue());
//        }else{
//            viewHolder.etValue.setText("");
//        }
//        viewHolder.tvKey.setText(copyItem.getKey());

    }

    @Override
    public int getItemCount() {
        return mList.size() / 6;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.list_item_copy_scripture_new, parent, false));
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_key)
        TextView tvKey;
        @BindView(R.id.tv_key_1)
        TextView tvKey1;
        @BindView(R.id.tv_key_2)
        TextView tvKey2;
        @BindView(R.id.tv_key_3)
        TextView tvKey3;
        @BindView(R.id.tv_key_4)
        TextView tvKey4;
        @BindView(R.id.tv_key_5)
        TextView tvKey5;
        @BindView(R.id.ll_key)
        LinearLayout llKey;
        @BindView(R.id.et_value)
        TextView etValue;
        @BindView(R.id.et_value_1)
        TextView etValue1;
        @BindView(R.id.et_value_2)
        TextView etValue2;
        @BindView(R.id.et_value_3)
        TextView etValue3;
        @BindView(R.id.et_value_4)
        TextView etValue4;
        @BindView(R.id.et_value_5)
        TextView etValue5;
        @BindView(R.id.ll_value)
        LinearLayout llValue;
        @BindView(R.id.et_input)
        EditText etInput;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


//    static class ViewHolder extends RecyclerView.ViewHolder{
//        @BindView(R.id.tv_key)
//        TextView tvKey;
//        @BindView(R.id.et_value)
//        EditText etValue;
//
//        ViewHolder(View view) {
//            super(view);
//            ButterKnife.bind(this, view);
//        }
//    }
}
