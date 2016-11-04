package com.yuanming.buddhism.entity;

import com.yuanming.buddhism.base.BaseEntity;

import java.util.List;

/**
 * Created by chenghuan on 2016/11/4.
 * on phyt company
 */

public class JsonList<T extends BaseEntity> extends BaseEntity {

    private List<T> data;
    private String msg;
    private boolean success;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
