package com.yuanming.buddhism.module.login;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseActivity;

public class LoginSuccessActivity extends BaseActivity {

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Explode explode = new Explode();
            explode.setDuration(500);
            getWindow().setExitTransition(explode);
            getWindow().setEnterTransition(explode);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_success;
    }
}
