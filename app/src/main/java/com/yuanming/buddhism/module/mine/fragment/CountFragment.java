package com.yuanming.buddhism.module.mine.fragment;

import android.view.View;
import android.widget.EditText;
import com.dd.CircularProgressButton;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chenghuan on 2016/11/3.
 * on phyt company
 */

public class CountFragment extends BaseFragment {

    @BindView(R.id.tv_counting_type)
    MaterialSpinner spinner;
    @BindView(R.id.et_counts)
    EditText etCounts;
    @BindView(R.id.et_pledge)
    EditText etPledge;
    @BindView(R.id.btn_counting)
    CircularProgressButton btnCounting;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_counts;
    }

    @OnClick({R.id.tv_counting_type, R.id.btn_counting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_counting_type:
                break;
            case R.id.btn_counting:
                break;
        }
    }

    @Override
    public void initView(View view) {
        spinner.setItems("尼玛石", "尼玛石1", "尼玛石2", "尼玛石3", "尼玛石4");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

            }
        });
    }
}
