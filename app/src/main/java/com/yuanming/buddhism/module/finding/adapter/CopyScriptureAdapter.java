package com.yuanming.buddhism.module.finding.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
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
    private CopyTextWhatcher mWatcher;
    private CopyFocusListener mFocusLisener;
    public CopyScriptureAdapter(RecyclerView context) {
        super(context);
        mWatcher = new CopyTextWhatcher();
        mFocusLisener = new CopyFocusListener();
    }


    class CopyTextWhatcher implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(charSequence==null||StringUtils.isEmpty(charSequence.toString())){
                return;
            }
            ViewHolder viewHolder = ((ViewHolder)mRecyclerView.findViewHolderForAdapterPosition(cuccentIndex));
            if(viewHolder==null){
                return;
            }
            int index = cuccentIndex*6;
            if(focusViewId==R.id.et_value){
                mList.get(index+1).setFocus(true);
                mList.get(index).setValue(viewHolder.etValue.getText().toString());
                if(!StringUtils.isEmpty(mList.get(index).getKey())&&mList.get(index).getKey().equals(viewHolder.etValue.getText().toString())){
                    viewHolder.etValue.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                }else{
                    viewHolder.etValue.setTextColor(ContextCompat.getColor(mContext,R.color.cpb_red));
                }
                viewHolder.etValue1.setFocusable(true);
                viewHolder.etValue1.setFocusableInTouchMode(true);
                viewHolder.etValue1.requestFocus();
            }else if(focusViewId==R.id.et_value_1){
                mList.get(index+2).setFocus(true);
                mList.get(index+1).setValue(viewHolder.etValue1.getText().toString());
                if(!StringUtils.isEmpty(mList.get(index+1).getKey())&&mList.get(index+1).getKey().equals(viewHolder.etValue1.getText().toString())){
                    viewHolder.etValue1.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                }else{
                    viewHolder.etValue1.setTextColor(ContextCompat.getColor(mContext,R.color.cpb_red));
                }

                viewHolder.etValue2.setFocusable(true);
                viewHolder.etValue2.setFocusableInTouchMode(true);
                viewHolder.etValue2.requestFocus();
            }else if(focusViewId==R.id.et_value_2){
                mList.get(index+3).setFocus(true);
                mList.get(index+2).setValue(viewHolder.etValue2.getText().toString());
                if(!StringUtils.isEmpty(mList.get(index+2).getKey())&&mList.get(index+2).getKey().equals(viewHolder.etValue2.getText().toString())){
                    viewHolder.etValue2.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                }else{
                    viewHolder.etValue2.setTextColor(ContextCompat.getColor(mContext,R.color.cpb_red));
                }

                viewHolder.etValue3.setFocusable(true);
                viewHolder.etValue3.setFocusableInTouchMode(true);
                viewHolder.etValue3.requestFocus();
            }else if(focusViewId==R.id.et_value_3){
                mList.get(index+4).setFocus(true);
                mList.get(index+3).setValue(viewHolder.etValue3.getText().toString());
                if(!StringUtils.isEmpty(mList.get(index+3).getKey())&&mList.get(index+3).getKey().equals(viewHolder.etValue3.getText().toString())){
                    viewHolder.etValue3.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                }else{
                    viewHolder.etValue3.setTextColor(ContextCompat.getColor(mContext,R.color.cpb_red));
                }

                viewHolder.etValue4.setFocusable(true);
                viewHolder.etValue4.setFocusableInTouchMode(true);
                viewHolder.etValue4.requestFocus();
            }else if(focusViewId==R.id.et_value_4){
                mList.get(index+5).setFocus(true);
                mList.get(index+4).setValue(viewHolder.etValue4.getText().toString());
                if(!StringUtils.isEmpty(mList.get(index+4).getKey())&&mList.get(index+4).getKey().equals(viewHolder.etValue4.getText().toString())){
                    viewHolder.etValue4.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                }else{
                    viewHolder.etValue4.setTextColor(ContextCompat.getColor(mContext,R.color.cpb_red));
                }

                viewHolder.etValue5.setFocusable(true);
                viewHolder.etValue5.setFocusableInTouchMode(true);
                viewHolder.etValue5.requestFocus();
            }else{
                if(!StringUtils.isEmpty(viewHolder.etValue.getText().toString())&&!StringUtils.isEmpty(viewHolder.etValue1.getText().toString())
                        &&!StringUtils.isEmpty(viewHolder.etValue2.getText().toString())&&!StringUtils.isEmpty(viewHolder.etValue3.getText().toString())
                        &&!StringUtils.isEmpty(viewHolder.etValue4.getText().toString())&&!StringUtils.isEmpty(viewHolder.etValue5.getText().toString())){
                    viewHolder.llValue.setBackground(ContextCompat.getDrawable(mContext,R.drawable.not_edit_type_bg));
                    mList.get(index+5).setValue(viewHolder.etValue5.getText().toString());
                    if(!StringUtils.isEmpty(mList.get(index+5).getKey())&&mList.get(index+5).getKey().equals(viewHolder.etValue5.getText().toString())){
                        viewHolder.etValue5.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                    }else{
                        viewHolder.etValue5.setTextColor(ContextCompat.getColor(mContext,R.color.cpb_red));
                    }
                    mList.get(index).setFocus(false);
                    mList.get(index+1).setFocus(false);
                    mList.get(index+2).setFocus(false);
                    mList.get(index+3).setFocus(false);
                    mList.get(index+4).setFocus(false);
                    mList.get(index+5).setFocus(false);
                    viewHolder.etValue.removeTextChangedListener(mWatcher);
                    viewHolder.etValue1.removeTextChangedListener(mWatcher);
                    viewHolder.etValue2.removeTextChangedListener(mWatcher);
                    viewHolder.etValue3.removeTextChangedListener(mWatcher);
                    viewHolder.etValue4.removeTextChangedListener(mWatcher);
                    viewHolder.etValue5.removeTextChangedListener(mWatcher);
                    viewHolder.etValue.setFocusable(false);
                    viewHolder.etValue.setFocusableInTouchMode(false);
                    viewHolder.etValue1.setFocusable(false);
                    viewHolder.etValue1.setFocusableInTouchMode(false);
                    viewHolder.etValue2.setFocusable(false);
                    viewHolder.etValue2.setFocusableInTouchMode(false);
                    viewHolder.etValue3.setFocusable(false);
                    viewHolder.etValue3.setFocusableInTouchMode(false);
                    viewHolder.etValue4.setFocusable(false);
                    viewHolder.etValue4.setFocusableInTouchMode(false);
                    viewHolder.etValue5.setFocusable(false);
                    viewHolder.etValue5.setFocusableInTouchMode(false);
                    ViewHolder viewHolder1 = ((ViewHolder)mRecyclerView.findViewHolderForAdapterPosition(cuccentIndex+1));
                    if(viewHolder1==null){
                        return;
                    }
                    viewHolder1.llValue.setBackground(null);
                    int nextIndex = (cuccentIndex+1)*6;
                    mList.get(nextIndex).setFocus(true);
                    viewHolder1.etValue.setFocusable(true);
                    viewHolder1.etValue.setFocusableInTouchMode(true);
                    viewHolder1.etValue.requestFocus();
                    viewHolder1.etValue.addTextChangedListener(mWatcher);
                    viewHolder1.etValue1.addTextChangedListener(mWatcher);
                    viewHolder1.etValue2.addTextChangedListener(mWatcher);
                    viewHolder1.etValue3.addTextChangedListener(mWatcher);
                    viewHolder1.etValue4.addTextChangedListener(mWatcher);
                    viewHolder1.etValue5.addTextChangedListener(mWatcher);
                }else{
                    if(StringUtils.isEmpty(viewHolder.etValue.getText().toString())){
                        viewHolder.etValue.requestFocus();
                    }else if(StringUtils.isEmpty(viewHolder.etValue1.getText().toString())){
                        viewHolder.etValue1.requestFocus();
                    }else if(StringUtils.isEmpty(viewHolder.etValue2.getText().toString())){
                        viewHolder.etValue2.requestFocus();
                    }else if(StringUtils.isEmpty(viewHolder.etValue3.getText().toString())){
                        viewHolder.etValue3.requestFocus();
                    }else if(StringUtils.isEmpty(viewHolder.etValue4.getText().toString())){
                        viewHolder.etValue4.requestFocus();
                    }
                }

            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
    private int cuccentIndex = 0;
    private int focusViewId = 0;
    class  CopyFocusListener implements View.OnFocusChangeListener{
        @Override
        public void onFocusChange(View view, boolean b) {
            if(b){
                focusViewId = view.getId();
                cuccentIndex = (int)view.getTag();
            }

        }
    }

    @Override
    protected void onBindViewHolders(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        int index = position*6;
        CopyItem copyItem = mList.get(index);
        CopyItem copyItem_1 = mList.get(index+1);
        CopyItem copyItem_2 = mList.get(index+2);
        CopyItem copyItem_3 = mList.get(index+3);
        CopyItem copyItem_4 = mList.get(index+4);
        CopyItem copyItem_5 = mList.get(index+5);

        viewHolder.tvKey.setText(copyItem.getKey()+"");
        viewHolder.etValue.setTag(position);
        viewHolder.etValue.setOnFocusChangeListener(mFocusLisener);
        if(StringUtils.isEmpty(copyItem.getValue())){
            viewHolder.etValue.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
            viewHolder.etValue.setText("");
        }else {
            if(copyItem.getKey().equals(copyItem.getValue())){
                viewHolder.etValue.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
            }else{
                viewHolder.etValue.setTextColor(ContextCompat.getColor(mContext,R.color.cpb_red));
            }
            viewHolder.etValue.setText(copyItem.getValue());
        }

        if(copyItem_1!=null){
            viewHolder.tvKey1.setVisibility(View.VISIBLE);
            viewHolder.etValue1.setVisibility(View.VISIBLE);
            viewHolder.tvKey1.setText(copyItem_1.getKey()+"");
            viewHolder.etValue1.setTag(position);
            viewHolder.etValue1.setOnFocusChangeListener(mFocusLisener);

            if(StringUtils.isEmpty(copyItem_1.getValue())){
                viewHolder.etValue1.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                viewHolder.etValue1.setText("");
            }else {
                if(copyItem_1.getKey().equals(copyItem_1.getValue())){
                    viewHolder.etValue1.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                }else{
                    viewHolder.etValue1.setTextColor(ContextCompat.getColor(mContext,R.color.cpb_red));
                }
                viewHolder.etValue1.setText(copyItem_1.getValue());
            }
        }else{
            viewHolder.tvKey1.setVisibility(View.INVISIBLE);
            viewHolder.etValue1.setVisibility(View.INVISIBLE);
        }

        if(copyItem_2!=null){
            viewHolder.tvKey2.setVisibility(View.VISIBLE);
            viewHolder.etValue2.setVisibility(View.VISIBLE);
            viewHolder.tvKey2.setText(copyItem_2.getKey()+"");
            viewHolder.etValue2.setTag(position);
            viewHolder.etValue2.setOnFocusChangeListener(mFocusLisener);
            if(StringUtils.isEmpty(copyItem_2.getValue())){
                viewHolder.etValue2.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                viewHolder.etValue2.setText("");
            }else {
                if(copyItem_2.getKey().equals(copyItem_2.getValue())){
                    viewHolder.etValue2.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                }else{
                    viewHolder.etValue2.setTextColor(ContextCompat.getColor(mContext,R.color.cpb_red));
                }
                viewHolder.etValue2.setText(copyItem_2.getValue());
            }
        }else{
            viewHolder.tvKey2.setVisibility(View.INVISIBLE);
            viewHolder.etValue2.setVisibility(View.INVISIBLE);
        }

        if(copyItem_3!=null){
            viewHolder.tvKey3.setVisibility(View.VISIBLE);
            viewHolder.etValue3.setVisibility(View.VISIBLE);
            viewHolder.tvKey3.setText(copyItem_3.getKey()+"");
            viewHolder.etValue3.setTag(position);
            viewHolder.etValue3.setOnFocusChangeListener(mFocusLisener);
            if(StringUtils.isEmpty(copyItem_3.getValue())){
                viewHolder.etValue3.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                viewHolder.etValue3.setText("");
            }else {
                if(copyItem_3.getKey().equals(copyItem_3.getValue())){
                    viewHolder.etValue3.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                }else{
                    viewHolder.etValue3.setTextColor(ContextCompat.getColor(mContext,R.color.cpb_red));
                }
                viewHolder.etValue3.setText(copyItem_3.getValue());
            }
        }else{
            viewHolder.tvKey3.setVisibility(View.INVISIBLE);
            viewHolder.etValue3.setVisibility(View.INVISIBLE);
        }

        if(copyItem_4!=null){
            viewHolder.tvKey4.setVisibility(View.VISIBLE);
            viewHolder.etValue4.setVisibility(View.VISIBLE);
            viewHolder.tvKey4.setText(copyItem_4.getKey()+"");
            viewHolder.etValue4.setTag(position);
            viewHolder.etValue4.setOnFocusChangeListener(mFocusLisener);
            if(StringUtils.isEmpty(copyItem_4.getValue())){
                viewHolder.etValue4.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                viewHolder.etValue4.setText("");
            }else {
                if(copyItem_4.getKey().equals(copyItem_4.getValue())){
                    viewHolder.etValue4.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                }else{
                    viewHolder.etValue4.setTextColor(ContextCompat.getColor(mContext,R.color.cpb_red));
                }
                viewHolder.etValue4.setText(copyItem_4.getValue());
            }
        }else{
            viewHolder.tvKey4.setVisibility(View.INVISIBLE);
            viewHolder.etValue4.setVisibility(View.INVISIBLE);
        }

        if(copyItem_5!=null){
            viewHolder.tvKey4.setVisibility(View.VISIBLE);
            viewHolder.etValue4.setVisibility(View.VISIBLE);
            viewHolder.tvKey5.setText(copyItem_5.getKey()+"");
            viewHolder.etValue5.setTag(position);
            viewHolder.etValue5.setOnFocusChangeListener(mFocusLisener);
            if(StringUtils.isEmpty(copyItem_5.getValue())){
                viewHolder.etValue5.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                viewHolder.etValue5.setText("");
            }else {
                if(copyItem_5.getKey().equals(copyItem_5.getValue())){
                    viewHolder.etValue5.setTextColor(ContextCompat.getColor(mContext,R.color.import_text));
                }else{
                    viewHolder.etValue5.setTextColor(ContextCompat.getColor(mContext,R.color.cpb_red));
                }
                viewHolder.etValue5.setText(copyItem_5.getValue());
            }
        }else{
            viewHolder.tvKey4.setVisibility(View.INVISIBLE);
            viewHolder.etValue4.setVisibility(View.INVISIBLE);
        }


        if(copyItem.isFocus()){
            viewHolder.llValue.setBackground(null);
            viewHolder.etValue.setFocusable(true);
            viewHolder.etValue.setFocusableInTouchMode(true);
            if(copyItem_1!=null){
                if(copyItem_1.isFocus()){
                    viewHolder.etValue1.setFocusable(true);
                    viewHolder.etValue1.setFocusableInTouchMode(true);
                }else{
                    viewHolder.etValue1.setFocusable(false);
                    viewHolder.etValue1.setFocusableInTouchMode(false);
                }
            }

            if(copyItem_2!=null) {
                if (copyItem_2.isFocus()) {
                    viewHolder.etValue2.setFocusable(true);
                    viewHolder.etValue2.setFocusableInTouchMode(true);
                } else {
                    viewHolder.etValue2.setFocusable(false);
                    viewHolder.etValue2.setFocusableInTouchMode(false);
                }
            }

            if(copyItem_3!=null) {
                if (copyItem_3.isFocus()) {
                    viewHolder.etValue3.setFocusable(true);
                    viewHolder.etValue3.setFocusableInTouchMode(true);
                } else {
                    viewHolder.etValue3.setFocusable(false);
                    viewHolder.etValue3.setFocusableInTouchMode(false);
                }
            }

            if(copyItem_4!=null) {
                if (copyItem_4.isFocus()) {
                    viewHolder.etValue4.setFocusable(true);
                    viewHolder.etValue4.setFocusableInTouchMode(true);
                } else {
                    viewHolder.etValue4.setFocusable(false);
                    viewHolder.etValue4.setFocusableInTouchMode(false);
                }
            }

            if(copyItem_5!=null) {
                if (copyItem_5.isFocus()) {
                    viewHolder.etValue5.setFocusable(true);
                    viewHolder.etValue5.setFocusableInTouchMode(true);
                } else {
                    viewHolder.etValue5.setFocusable(false);
                    viewHolder.etValue5.setFocusableInTouchMode(false);
                }
            }
            viewHolder.etValue.addTextChangedListener(mWatcher);
            viewHolder.etValue1.addTextChangedListener(mWatcher);
            viewHolder.etValue2.addTextChangedListener(mWatcher);
            viewHolder.etValue3.addTextChangedListener(mWatcher);
            viewHolder.etValue4.addTextChangedListener(mWatcher);
            viewHolder.etValue5.addTextChangedListener(mWatcher);
        }else{
            viewHolder.llValue.setBackground(ContextCompat.getDrawable(mContext,R.drawable.not_edit_type_bg));
            viewHolder.etValue.setFocusable(false);
            viewHolder.etValue.setFocusableInTouchMode(false);
            viewHolder.etValue1.setFocusable(false);
            viewHolder.etValue1.setFocusableInTouchMode(false);
            viewHolder.etValue2.setFocusable(false);
            viewHolder.etValue2.setFocusableInTouchMode(false);
            viewHolder.etValue3.setFocusable(false);
            viewHolder.etValue3.setFocusableInTouchMode(false);
            viewHolder.etValue4.setFocusable(false);
            viewHolder.etValue4.setFocusableInTouchMode(false);
            viewHolder.etValue5.setFocusable(false);
            viewHolder.etValue5.setFocusableInTouchMode(false);

            viewHolder.etValue.removeTextChangedListener(mWatcher);
            viewHolder.etValue1.removeTextChangedListener(mWatcher);
            viewHolder.etValue2.removeTextChangedListener(mWatcher);
            viewHolder.etValue3.removeTextChangedListener(mWatcher);
            viewHolder.etValue4.removeTextChangedListener(mWatcher);
            viewHolder.etValue5.removeTextChangedListener(mWatcher);
        }
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
        EditText etValue;
        @BindView(R.id.et_value_1)
        EditText etValue1;
        @BindView(R.id.et_value_2)
        EditText etValue2;
        @BindView(R.id.et_value_3)
        EditText etValue3;
        @BindView(R.id.et_value_4)
        EditText etValue4;
        @BindView(R.id.et_value_5)
        EditText etValue5;
        @BindView(R.id.ll_value)
        LinearLayout llValue;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
