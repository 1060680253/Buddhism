package com.yuanming.buddhism.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yuanming.buddhism.R;

/**
 * Created by chenghuan on 2016/10/22.
 * on phyt company
 */
public class LoadingView extends LinearLayout {
    private Context mContext;
    public LoadingView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        inflate(mContext, R.layout.view_loading, this);
        ImageView circle = (ImageView) findViewById(R.id.circle);
        RotateAnimation operatingAnim = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        operatingAnim.setRepeatCount(Animation.INFINITE);
        operatingAnim.setDuration(2000);
        operatingAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        circle.setAnimation(operatingAnim);
    }


}
