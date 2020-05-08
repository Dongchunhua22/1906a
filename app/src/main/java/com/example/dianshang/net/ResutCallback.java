package com.example.dianshang.net;

public interface ResutCallback <T>{
    void onsuccess (T t);
    void  onfail(String msg);
}
