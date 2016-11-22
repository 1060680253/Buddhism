package com.yuanming.buddhism.module.main.activity;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.module.mine.fragment.CollapseFragment;
import com.yuanming.buddhism.module.mine.fragment.CountFragment;
import com.yuanming.buddhism.module.mine.fragment.CountLogsFragment;
import com.yuanming.buddhism.module.mine.fragment.GenCountLogsFragment;
import com.yuanming.buddhism.module.mine.fragment.MineMsgFragment;
import com.yuanming.buddhism.module.mine.fragment.SettingFragment;

/**
 * Created by chenghuan on 2016/11/3.
 * on phyt company
 */

public enum CommonPage {


    COUNTS(1, R.string.actionbar_title_counts,CountFragment.class),
    COUNTLOGS(2, R.string.actionbar_title_count_log, GenCountLogsFragment.class),
    COLLAPSE(3, R.string.actionbar_title_count_log, CollapseFragment.class),
    SETTING(4, R.string.actionbar_title_setting, SettingFragment.class),
    MINEMSG(5, R.string.actionbar_title_mine_msg, MineMsgFragment.class);

    private int title;
    private Class<?> clz;
    private int value;

    private CommonPage(int value, int title, Class<?> clz) {
        this.value = value;
        this.title = title;
        this.clz = clz;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static CommonPage getPageByValue(int val) {
        for (CommonPage p : values()) {
            if (p.getValue() == val)
                return p;
        }
        return null;
    }

}
