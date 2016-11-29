package com.yuanming.buddhism.interf;


public interface HttpRequestListener<T extends Object> {
    void onSuccess(T result);

    void onError(int state,String error);
}
