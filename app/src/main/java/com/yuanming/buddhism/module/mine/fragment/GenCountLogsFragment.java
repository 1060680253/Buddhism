package com.yuanming.buddhism.module.mine.fragment;

import android.view.View;

import com.baiiu.filter.DropDownMenu;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.yuanming.buddhism.R;
import com.yuanming.buddhism.base.BaseFragment;
import com.yuanming.buddhism.module.mine.adapter.DropMenuAdapter;

import butterknife.BindView;

/**
 * Created by chenghuan on 2016/11/4.
 * on phyt company
 */

public class GenCountLogsFragment extends BaseFragment implements OnFilterDoneListener {

    @BindView(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;

    @Override
    protected int getLayoutId() {
        return R.layout.frament_count_logs_header;
    }

    @Override
    public void initView(View view) {
        String[] titleList = new String[]{"排序", "时间筛选", "念诵类型"};
        dropDownMenu.setMenuAdapter(new DropMenuAdapter(view.getContext(), titleList, this));
        getChildFragmentManager().beginTransaction().replace(R.id.container,new CountLogsFragment()).commit();
    }

    @Override
    public void onFilterDone(int position, String positionTitle, String urlValue) {

    }
}
