package com.example.daxiang.util;

import java.util.HashMap;

public interface INetWork {
  /* void  get(String url);
   void  get(String url,HashMap<String,String>s);

    void  post(String url);
    void  post(String url,HashMap<String,String>s);*/

    <T> void get(String url,INetCallBack<T> netCallBack);
    <T> void get(String url, HashMap<String,String> s,INetCallBack<T> netCallBack);

    <T> void post(String url,INetCallBack<T> netCallBack);
    <T> void post(String url, HashMap<String,String> s,INetCallBack<T> netCallBack);

}
