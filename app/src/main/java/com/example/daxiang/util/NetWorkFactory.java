package com.example.daxiang.util;

public class NetWorkFactory {
    private static int NET_TYPE = 1;

    public INetWork network;

    private static NetWorkFactory netWorkFactory;

    public static NetWorkFactory getInstance() {
        if(netWorkFactory == null){
            synchronized (RetrofitUtils.class){
                if (netWorkFactory == null){
                    netWorkFactory = new NetWorkFactory();
                }
            }
        }
        return netWorkFactory;
    }

    public INetWork getNetWork(){
        if(NET_TYPE == 1){
            network = RetrofitUtils.getInstance();
        }else {
            network = HttpUrlConnectionUtils.getInstance();
        }
        return  network;
    }
}
