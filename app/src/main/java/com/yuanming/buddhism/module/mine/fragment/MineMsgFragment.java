package com.yuanming.buddhism.module.mine.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;
import com.yuanming.buddhism.module.main.activity.CommonActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenghuan on 2016/11/22.
 * on phyt company
 */

public class MineMsgFragment extends BaseFragment {
    @BindView(R.id.iv_user)
    public ImageView iv_user;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_nick_name)
    TextView tvUserNickName;
    @BindView(R.id.tv_user_app_id)
    TextView tvUserAppId;
    @BindView(R.id.tv_user_mobile)
    TextView tvUserMobile;
    @BindView(R.id.tv_user_email)
    TextView tvUserEmail;
    @BindView(R.id.tv_user_birthday)
    TextView tvUserBirthday;
    @BindView(R.id.tv_intro_key)
    TextView tvIntroKey;
    @BindView(R.id.tv_user_intro)
    TextView tvUserIntro;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_msg;
    }

    private EditMsgDialog editMsgDialog;

    @OnClick({R.id.rl_photo, R.id.rl_user_name, R.id.rl_fa_id, R.id.rl_user_app_id, R.id.rl_user_intro, R.id.rl_user_mobile, R.id.rl_user_birthday, R.id.rl_user_email})
    public void onClick(View view) {

        if (editMsgDialog == null) {
            editMsgDialog = new EditMsgDialog(view.getContext());
        }
        switch (view.getId()) {
            case R.id.rl_photo:
                ((CommonActivity) getFActivity()).pickImage();
                break;
            case R.id.rl_user_name:
                editMsgDialog.show("请输入用户名",tvUserName.getText().toString());
                break;
            case R.id.rl_fa_id:
                editMsgDialog.show("请输入用户法号",tvUserNickName.getText().toString());
                break;
            case R.id.rl_user_app_id:
                editMsgDialog.show("请输入用户App号",tvUserAppId.getText().toString());
                break;
            case R.id.rl_user_intro:
                editMsgDialog.show("请输入用户个人介绍",tvUserIntro.getText().toString());
                break;
            case R.id.rl_user_mobile:
                editMsgDialog.show("请输入用户手机号",tvUserMobile.getText().toString());
                break;
            case R.id.rl_user_birthday:
                editMsgDialog.show("请输入用户生日",tvUserBirthday.getText().toString());
                break;
            case R.id.rl_user_email:
                editMsgDialog.show("请输入用户邮箱",tvUserEmail.getText().toString());
                break;
        }
    }

}
