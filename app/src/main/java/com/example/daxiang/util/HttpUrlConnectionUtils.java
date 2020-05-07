package com.example.daxiang.util;

import java.util.HashMap;

public class HttpUrlConnectionUtils implements  INetWork {
    private static  HttpUrlConnectionUtils httpUrlConnetionUtils;

    private HttpUrlConnectionUtils() {
    }

    public static HttpUrlConnectionUtils getInstance() {
        if(httpUrlConnetionUtils == null){
            synchronized (RetrofitUtils.class){
                if (httpUrlConnetionUtils == null){
                    httpUrlConnetionUtils = new HttpUrlConnectionUtils();
                }
            }
        }
        return httpUrlConnetionUtils;
    }

    @Override
    public <T> void get(String url, INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void get(String url, HashMap<String, String> s, INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void post(String url, INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void post(String url, HashMap<String, String> s, INetCallBack<T> netCallBack) {

    }
}
