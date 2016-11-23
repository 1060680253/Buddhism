package com.yuanming.buddhism.module.mine.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;
import com.yuanming.buddhism.module.main.activity.CommonActivity;
import com.yuanming.buddhism.module.main.activity.CommonPage;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenghuan on 2016/11/22.
 * on phyt company
 */

public class SettingFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @OnClick({R.id.rl_mine_msg, R.id.rl_about_us, R.id.rl_clean_memory, R.id.rl_update, R.id.rl_advise})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_mine_msg:
                CommonActivity.startActivity(view.getContext(), CommonPage.MINEMSG);
                break;
            case R.id.rl_about_us:

                break;
            case R.id.rl_clean_memory:

                break;
            case R.id.rl_update:

                break;
            case R.id.rl_advise:

                break;
        }
    }
}
