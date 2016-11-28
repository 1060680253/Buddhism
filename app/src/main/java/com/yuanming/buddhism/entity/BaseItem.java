package com.yuanming.buddhism.entity;

import com.yuanming.buddhism.base.BaseEntity;

/**
 * Created by chenghuan on 2016/11/28.
 * on phyt company
 */

public class BaseItem<T extends Object> extends BaseEntity{

    private boolean success;
    private String msg;
    private int state;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
