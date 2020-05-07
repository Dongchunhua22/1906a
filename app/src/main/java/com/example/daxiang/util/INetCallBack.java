package com.example.daxiang.util;

public interface INetCallBack<T> {
    void  onSuccess(T t);
    void onError(Throwable throwable);
}
