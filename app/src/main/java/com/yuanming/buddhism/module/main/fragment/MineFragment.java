package com.yuanming.buddhism.module.main.fragment;

import android.view.View;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;
import com.yuanming.buddhism.base.SwipeRefreshFragment;
import com.yuanming.buddhism.module.main.activity.CommonActivity;
import com.yuanming.buddhism.module.main.activity.CommonPage;
import com.yuanming.buddhism.module.mine.fragment.QrDialog;
import com.yuanming.buddhism.widget.RoundImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenghuan on 2016/11/3.
 * on phyt company
 */

public class MineFragment extends SwipeRefreshFragment {

    @BindView(R.id.riv_user)
    RoundImageView rivUser;
    @BindView(R.id.tv_collection)
    TextView tvCollection;

    @Override
    protected int getRefreshLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected boolean isShowLoadingLayout() {
        return false;
    }

    @Override
    protected void requestData() {
        onSuccess(null);
    }

    @OnClick({R.id.riv_user, R.id.tv_collection,R.id.iv_setting,R.id.iv_qr_code,R.id.ll_collection,R.id.rl_friends,R.id.ll_my_msg,R.id.rl_collection,R.id.rl_things,R.id.rl_do_thing,R.id.rl_activity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.riv_user:
                CommonActivity.startActivity(view.getContext(),CommonPage.MINEMSG);
                break;
//            case R.id.tv_collection:
//                CommonActivity.startActivity(view.getContext(),CommonPage.COLLAPSE);
//                break;
            case R.id.rl_do_thing:
                CommonActivity.startActivity(view.getContext(),CommonPage.COLLAPSE);
                break;
            case R.id.iv_setting:
                CommonActivity.startActivity(view.getContext(),CommonPage.SETTING);
                break;
            case R.id.iv_qr_code:
                QrDialog qrDialog = new QrDialog(view.getContext());
                qrDialog.show();
                break;
            case R.id.ll_collection:
            case R.id.rl_collection:
                CommonActivity.startActivity(view.getContext(),CommonPage.COLLECTION);
                break;
            case R.id.rl_friends:
                CommonActivity.startActivity(view.getContext(),CommonPage.MYFRIENDS);
                break;
            case R.id.ll_my_msg:
                CommonActivity.startActivity(view.getContext(),CommonPage.MYMSGS);
                break;
            case R.id.rl_things:
                CommonActivity.startActivity(view.getContext(),CommonPage.MYTHINGS);
                break;
            case R.id.rl_activity:
                CommonActivity.startActivity(view.getContext(),CommonPage.MYACTIVITY);
                break;
        }
    }
}
