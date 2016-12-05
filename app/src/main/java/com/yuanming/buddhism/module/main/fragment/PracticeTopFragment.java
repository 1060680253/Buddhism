package com.yuanming.buddhism.module.main.fragment;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;
import com.yuanming.buddhism.entity.MsgEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

/**
 * Created by chenghuan on 2016/12/5.
 * on phyt company
 */

public class PracticeTopFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.view_practice_pager_top;
    }


    @OnClick(R.id.ll_next_page)
    public void onClick() {
    }
}
