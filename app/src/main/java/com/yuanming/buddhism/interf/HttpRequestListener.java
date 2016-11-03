package com.yuanming.buddhism.interf;


import com.yuanming.buddhism.base.BaseEntity;

public interface HttpRequestListener<T extends BaseEntity> {
    void onSuccess(T result);

    void onError(int state,String error);
}
