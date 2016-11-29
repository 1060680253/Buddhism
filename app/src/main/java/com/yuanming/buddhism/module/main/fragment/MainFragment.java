package com.yuanming.buddhism.module.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;
import com.yuanming.buddhism.widget.update.UpdateAgent;
import com.yuanming.buddhism.widget.update.UpdateInfo;
import com.yuanming.buddhism.widget.update.UpdateManager;
import com.yuanming.buddhism.widget.update.UpdateUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/2.
 */

public class MainFragment extends BaseFragment {
    String mCheckUrl = "http://client.waimai.baidu.com/message/updatetag";

    String mUpdateUrl = "http://mobile.ac.qq.com/qqcomic_android.apk";
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find_out;
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
