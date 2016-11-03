package com.yuanming.buddhism.module.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseActivity;
import com.yuanming.buddhism.constant.Constants;

import java.lang.ref.WeakReference;

/**
 * Created by chenghuan on 2016/11/3.
 * on phyt company
 */

public class CommonActivity extends BaseActivity {
    protected int mPageValue = -1;
    protected Bundle mBundle = null;
    protected WeakReference<Fragment> mFragment;

    public static void startActivity(Context context,CommonPage page){
        startActivity(context,page,null);
    }

    public static void startActivity(Context context,CommonPage page,Bundle bundle){
        Intent intent = new Intent(context,CommonActivity.class);
        intent.putExtra(Constants.BUNDLE_COMMON_PAGE,page.getValue());
        intent.putExtra(Constants.BUNDLE_COMMON_EXTRA,bundle);
        context.startActivity(intent);
    }

    @Override
    protected void initBundle(Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            mPageValue = savedInstanceState.getInt(Constants.BUNDLE_COMMON_PAGE);
            mBundle = savedInstanceState.getBundle(Constants.BUNDLE_COMMON_EXTRA);
        }
    }

    @Override
    public void initView() {
        CommonPage page = CommonPage.getPageByValue(mPageValue);
        if (page == null) {
            throw new IllegalArgumentException("can not find page by value:"
                    + mPageValue);
        }
        mTvActionTitle.setText(page.getTitle());
        if(page.getValue()==1){
            getRightContiner().setVisibility(View.VISIBLE);
            TextView textView = new TextView(this);
            textView.setText(R.string.right_title_counts_log);
            textView.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
            textView.setTextSize(17);
            getRightContiner().addView(textView);
            getRightContiner().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        try {
            Fragment fragment = (Fragment) page.getClz().newInstance();
            if (mBundle != null) {
                fragment.setArguments(mBundle);
            }
            addFramentMethod(fragment);
            mFragment = new WeakReference<>(fragment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "generate fragment error. by value:" + mPageValue);
        }

    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }
}
