package com.yuanming.buddhism.module.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.transition.Explode;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.yuanming.buddhism.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenghuan on 2016/12/1.
 * on phyt company
 */

public class LoginDialog extends Dialog {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_go)
    Button btGo;
    @BindView(R.id.cv)
    CardView cv;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private Context mContext;

    public LoginDialog(Context context) {
        this(context, R.style.Theme_Dialog_From_Bottom);
    }

    public LoginDialog(Context context, int theme)
    {
        super(context, theme);
        // TODO Auto-generated constructor stub
        this.mContext = context;
        init();
    }

    private void init()
    {
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initValues();
    }

    protected int getLayoutId() {
        return R.layout.dialog_login;
    }


    public void animateRevealShow() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator mAnimator = ViewAnimationUtils.createCircularReveal(cv,fab.getRight(), ((RelativeLayout.LayoutParams)fab.getLayoutParams()).topMargin, fab.getWidth() / 2, cv.getHeight());
            mAnimator.setDuration(500);
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    cv.setVisibility(View.VISIBLE);
                    super.onAnimationStart(animation);
                }
            });
            mAnimator.start();
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ScaleAnimation scaleAnimation = new ScaleAnimation(0f,1f,0f,1f,fab.getWidth()/2,fab.getWidth()/2);
            scaleAnimation.setDuration(400);
            scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    fab.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animateRevealShow();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            fab.startAnimation(scaleAnimation);
        }
    };

    public void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            handler.sendEmptyMessageDelayed(0,200);
        }
    }

    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setExitTransition(null);
                    getWindow().setEnterTransition(null);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, fab, fab.getTransitionName());
                    mContext.startActivity(new Intent(mContext, RegisterActivity.class), options.toBundle());

                } else {
                    mContext.startActivity(new Intent(mContext, RegisterActivity.class));
                }
                break;
            case R.id.bt_go:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();
                    explode.setDuration(500);

                    getWindow().setExitTransition(explode);
                    getWindow().setEnterTransition(explode);
                    ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)mContext);
                    Intent i2 = new Intent(mContext, LoginSuccessActivity.class);
                    mContext.startActivity(i2, oc2.toBundle());
                    break;
                }
        }
    }

    public void animateRevealClose() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator mAnimator = ViewAnimationUtils.createCircularReveal(cv, fab.getRight(), ((RelativeLayout.LayoutParams)fab.getLayoutParams()).topMargin, cv.getHeight(), fab.getWidth() / 2);
            mAnimator.setDuration(500);
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    cv.setVisibility(View.INVISIBLE);
                    super.onAnimationEnd(animation);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(1f,0f,1f,0f,fab.getWidth()/2,fab.getWidth()/2);
                    scaleAnimation.setDuration(400);
                    scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            fab.setVisibility(View.INVISIBLE);
                            LoginDialog.super.onBackPressed();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    fab.startAnimation(scaleAnimation);

                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                }
            });
            mAnimator.start();
        }
    }

    private void initValues()
    {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        lp.width = dm.widthPixels;//让dialog的宽占满屏幕的宽
        lp.gravity = Gravity.CENTER;//出现在底部
        window.setAttributes(lp);

    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
    }
}
