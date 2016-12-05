package com.yuanming.buddhism.module.main.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by chenghuan on 2016/12/5.
 * on phyt company
 */

public class StartActivity extends BaseActivity {

    @BindView(R.id.splah_text)
    TextView splahText;

    @Override
    protected boolean hasActionBar() {
        return false;
    }

    private void initStatus() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void initView() {
        initStatus();
        ScaleAnimation scaleAnimation = new ScaleAnimation(0f,1f,0f,1f,splahText.getX()+splahText.getWidth()/2,splahText.getY()+splahText.getHeight()/2);
        scaleAnimation.setDuration(1500L);
        scaleAnimation.setInterpolator(new BounceInterpolator());
        splahText.startAnimation(scaleAnimation);
        splahText.setVisibility(View.VISIBLE);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }
}
