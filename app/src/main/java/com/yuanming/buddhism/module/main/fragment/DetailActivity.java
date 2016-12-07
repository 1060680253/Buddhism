package com.yuanming.buddhism.module.main.fragment;

import android.os.Build;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseActivity;

/**
 * Created by chenghuan on 2016/12/6.
 * on phyt company
 */

public class DetailActivity extends BaseActivity{
    private ImageView mAlbumImage;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }
    private boolean isBack = false;
    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            explode.setDuration(500);
            getWindow().setExitTransition(explode);
            getWindow().setEnterTransition(explode);
        }
        isBack = false;
        mAlbumImage = (ImageView) findViewById(R.id.details_album_image);
        final ImageView backgroundImage = (ImageView) findViewById(R.id.details_background_image);

        View textContainer = findViewById(R.id.details_text_container);
        TextView albumTitleText = (TextView) textContainer.findViewById(R.id.details_album_title);
        getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

                    mAlbumImage.setVisibility(View.GONE);

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(0f,1f,0f,1f,mAlbumImage.getWidth()/2,mAlbumImage.getHeight()/2);
                scaleAnimation.setDuration(600);
                mAlbumImage.startAnimation(scaleAnimation);
                mAlbumImage.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBack = true;
        finishAfterTransition();
    }

}
