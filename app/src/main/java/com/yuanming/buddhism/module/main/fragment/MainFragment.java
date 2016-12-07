package com.yuanming.buddhism.module.main.fragment;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;
import com.yuanming.buddhism.util.TDevice;
import com.yuanming.buddhism.widget.update.UpdateAgent;
import com.yuanming.buddhism.widget.update.UpdateInfo;
import com.yuanming.buddhism.widget.update.UpdateManager;
import com.yuanming.buddhism.widget.update.UpdateUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/2.
 */

public class MainFragment extends BaseFragment {
    String mCheckUrl = "http://client.waimai.baidu.com/message/updatetag";

    String mUpdateUrl = "http://mobile.ac.qq.com/qqcomic_android.apk";
    @BindView(R.id.rl_img)
    RelativeLayout rl_img;

    @BindView(R.id.rl_img_2)
    RelativeLayout rl_img_2;
    @BindView(R.id.rl_img_3)
    RelativeLayout rl_img_3;
    @BindView(R.id.rl_img_4)
    RelativeLayout rl_img_4;
    @BindView(R.id.rl_total)
    RelativeLayout rl_total;
    private float finalRadius;
    @Override
    public void initView(View view) {
        super.initView(view);
        finalRadius = (float) Math.hypot(TDevice.dpToPixel(128), TDevice.dpToPixel(128));
        rl_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRevealAnimation(rl_img_2);
            }
        });
    }

    private void startRevealAnimation(final View view) {
        int potX,potY;
        float fromX,toX,fromY,toY;
        final View last_view;
        switch (view.getId()){
            case R.id.rl_img_2:
                fromX = toX = 0f;
                fromY = -2f;
                toY = 0f;
                last_view = rl_img;
                potX = (int)(last_view.getX()+(TDevice.dpToPixel(128)/2));
                potY = (int)last_view.getY();
                break;
            case R.id.rl_img_3:
                fromX = 2f;
                toX = 0f;
                fromY = toY = 0f;
                last_view = rl_img_2;
                potX = (int)(last_view.getX()+(TDevice.dpToPixel(128)));
                potY = (int)(last_view.getY()+(TDevice.dpToPixel(128)/2));
                break;
            case R.id.rl_img_4:
                fromX = toX = 0f;
                fromY = 2f;
                toY = 0f;
                last_view = rl_img_3;
                potX = (int)(last_view.getX()+(TDevice.dpToPixel(128)/2));
                potY = (int)(last_view.getY()+(TDevice.dpToPixel(128)));
                break;
            default:
                fromX = -2f;
                toX = 0f;
                fromY = toY = 0f;
                last_view = rl_img_4;
                potX = (int)(last_view.getX());
                potY = (int)(last_view.getY()+(TDevice.dpToPixel(128)/2));
                break;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Animator anim = ViewAnimationUtils.createCircularReveal(view, potX, potY, 0, finalRadius);
            anim.setInterpolator(AnimationUtils.loadInterpolator(mView.getContext(),
                    android.R.interpolator.fast_out_linear_in));
            anim.setDuration(1200);

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    last_view.setVisibility(View.GONE);
                    rl_total.removeView(last_view);
                    rl_total.addView(last_view,3);
                    switch (view.getId()){
                        case R.id.rl_img_2:
                            startRevealAnimation(rl_img_3);
                            break;
                        case R.id.rl_img_3:
                            startRevealAnimation(rl_img_4);
                            break;
                        case R.id.rl_img_4:
                            startRevealAnimation(rl_img);
                            break;
                        default:
                            startRevealAnimation(rl_img_2);
                            break;
                    }
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            anim.start();
        }else{
            TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,fromX,Animation.RELATIVE_TO_SELF,toX,Animation.RELATIVE_TO_SELF,fromY,Animation.RELATIVE_TO_SELF,toY);
            translateAnimation.setDuration(1200);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    last_view.setVisibility(View.GONE);
                    rl_total.removeView(last_view);
                    rl_total.addView(last_view,3);
                    switch (view.getId()){
                        case R.id.rl_img_2:
                            startRevealAnimation(rl_img_3);
                            break;
                        case R.id.rl_img_3:
                            startRevealAnimation(rl_img_4);
                            break;
                        case R.id.rl_img_4:
                            startRevealAnimation(rl_img);
                            break;
                        default:
                            startRevealAnimation(rl_img_2);
                            break;
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            view.startAnimation(translateAnimation);
            view.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @OnClick({R.id.check_update, R.id.check_update_cant_ignore, R.id.check_update_force, R.id.check_update_silent, R.id.check_update_no_newer, R.id.clean})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_update:
                check(true, true, false, false, true, 998);
                break;
            case R.id.check_update_cant_ignore:
                check(true, true, false, false, false, 998);
                break;
            case R.id.check_update_force:
                check(true, true, true, false, true, 998);
                break;
            case R.id.check_update_no_newer:
                check(true, false, false, false, true, 998);
                break;
            case R.id.check_update_silent:
                check(true, true, false, true, true, 998);
                break;
            case R.id.clean:
                UpdateUtil.clean(mView.getContext());
                Toast.makeText(mView.getContext(), "cleared", Toast.LENGTH_LONG).show();
                break;
        }
    }

    void check(boolean isManual, final boolean hasUpdate, final boolean isForce, final boolean isSilent, final boolean isIgnorable, final int notifyId) {
        UpdateManager.create(mView.getContext()).setUrl(mCheckUrl).setManual(isManual).setNotifyId(notifyId).setParser(new UpdateAgent.InfoParser() {
            @Override
            public UpdateInfo parse(String source) throws Exception {
                UpdateInfo info = new UpdateInfo();
                info.hasUpdate = hasUpdate;
                info.updateContent = "• 支持文字、贴纸、背景音乐，尽情展现欢乐气氛；\n• 两人视频通话支持实时滤镜，丰富滤镜，多彩心情；\n• 图片编辑新增艺术滤镜，一键打造文艺画风；\n• 资料卡新增点赞排行榜，看好友里谁是魅力之王。";
                info.versionCode = 587;
                info.versionName = "v5.8.7";
                info.url = mUpdateUrl;
                info.md5 = "56cf48f10e4cf6043fbf53bbbc4009e3";
                info.size = 10149314;
                info.isForce = isForce;
                info.isIgnorable = isIgnorable;
                info.isSilent = isSilent;
                return info;
            }
        }).check();
    }
}
