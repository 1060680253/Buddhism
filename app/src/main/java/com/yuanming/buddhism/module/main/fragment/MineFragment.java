package com.yuanming.buddhism.module.main.fragment;

import android.view.View;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;
import com.yuanming.buddhism.module.main.activity.CommonActivity;
import com.yuanming.buddhism.module.main.activity.CommonPage;
import com.yuanming.buddhism.widget.RoundImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenghuan on 2016/11/3.
 * on phyt company
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.riv_user)
    RoundImageView rivUser;
    @BindView(R.id.tv_collection)
    TextView tvCollection;
    @BindView(R.id.tv_counting)
    TextView tvCounting;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @OnClick({R.id.riv_user, R.id.tv_collection, R.id.tv_counting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.riv_user:
                break;
            case R.id.tv_collection:
                break;
            case R.id.tv_counting:
                CommonActivity.startActivity(view.getContext(), CommonPage.COUNTS);
                break;
        }
    }
}
