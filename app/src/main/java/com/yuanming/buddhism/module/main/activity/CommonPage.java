package com.yuanming.buddhism.module.main.activity;

import com.yuanming.buddhism.R;
import com.yuanming.buddhism.module.mine.fragment.CountFragment;

/**
 * Created by chenghuan on 2016/11/3.
 * on phyt company
 */

public enum CommonPage {


    COUNTS(1, R.string.actionbar_title_counts,CountFragment.class);

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
