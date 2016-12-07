package com.yuanming.buddhism.module.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.transition.Fade;
import android.transition.Slide;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.Window;

import com.brioal.bottomtab.entity.TabEntity;
import com.brioal.bottomtab.interfaces.OnTabSelectedListener;
import com.brioal.bottomtab.view.BottomLayout;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseActivity;
import com.yuanming.buddhism.module.main.fragment.FindingFragment;
import com.yuanming.buddhism.module.main.fragment.MainFragment;
import com.yuanming.buddhism.module.main.fragment.MineFragment;
import com.yuanming.buddhism.module.main.fragment.NewsFragment;
import com.yuanming.buddhism.module.main.fragment.PracticeFragment;
import com.yuanming.buddhism.widget.ZoomOutPageTransformer;
import com.yuanming.buddhism.widget.update.UpdateAgent;
import com.yuanming.buddhism.widget.update.UpdateInfo;
import com.yuanming.buddhism.widget.update.UpdateManager;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.vp_horizontal_ntb)
    ViewPager viewPager;
    @BindView(R.id.main_tab)
    BottomLayout mBottomLayout;
    private SparseArray<Fragment> fragments;

    @Override
    public void initView() {
        fragments = new SparseArray<>();
        fragments.put(0,new NewsFragment());
        fragments.put(1,new PracticeFragment());
        fragments.put(2,new FindingFragment());
        fragments.put(3,new MineFragment());
        final ArrayList<TabEntity> mList = new ArrayList<>();
        mList.add(new TabEntity(R.mipmap.ic_launcher, "资讯"));
        mList.add(new TabEntity(R.mipmap.ic_launcher, "修行"));
        mList.add(new TabEntity(R.mipmap.ic_launcher, "发现"));
        mList.add(new TabEntity(R.mipmap.ic_launcher, "我的"));
        mBottomLayout.setList(mList); //设置数据源
        mBottomLayout.setNews(5, 3);
        //设置Item点击事件
        mBottomLayout.setSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onSelected(int position) {
                viewPager.setCurrentItem(position,true);
                mBottomLayout.cleanNews(position); //清除未读消息
            }
        });
        viewPager.setAdapter(mAdapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                mBottomLayout.setCurrentIndex(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

//    void check(boolean isManual, final boolean hasUpdate, final boolean isForce, final boolean isSilent, final boolean isIgnorable, final int notifyId) {
//        UpdateManager.create(this).setUrl(mCheckUrl).setManual(isManual).setNotifyId(notifyId).setParser(new UpdateAgent.InfoParser() {
//            @Override
//            public UpdateInfo parse(String source) throws Exception {
//                UpdateInfo info = new UpdateInfo();
//                info.hasUpdate = hasUpdate;
//                info.updateContent = "• 支持文字、贴纸、背景音乐，尽情展现欢乐气氛；\n• 两人视频通话支持实时滤镜，丰富滤镜，多彩心情；\n• 图片编辑新增艺术滤镜，一键打造文艺画风；\n• 资料卡新增点赞排行榜，看好友里谁是魅力之王。";
//                info.versionCode = 587;
//                info.versionName = "v5.8.7";
//                info.url = mUpdateUrl;
//                info.md5 = "56cf48f10e4cf6043fbf53bbbc4009e3";
//                info.size = 10149314;
//                info.isForce = isForce;
//                info.isIgnorable = isIgnorable;
//                info.isSilent = isSilent;
//                return info;
//            }
//        }).check();
//    }


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        setTransiton();
        super.onCreate(savedInstanceState, persistentState);
    }

    private void setTransiton() {
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setAllowReturnTransitionOverlap(false);
        Fade fade = new Fade();
        fade.setDuration(800);
        getWindow().setExitTransition(fade);


        Slide slide = new Slide(Gravity.BOTTOM);
        slide.setDuration(500);
        getWindow().setReenterTransition(slide);

    }

    @Override
    public void onActivityReenter(int requestCode, Intent data) {
        super.onActivityReenter(requestCode, data);
    }

    @Override
    public void onBackPressed() {
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
    }

    @Override
    protected boolean hasActionBar() {
        return false;
    }

    FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragments.get(arg0);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}
