package com.example.daxiang.home.constract;

import com.example.daxiang.base.BaseView;
import com.example.daxiang.util.INetCallBack;

public class HomeConstract {
    public  interface  IHomeView extends BaseView {
        void  setBannView(String string);

        void setTabList(String string);

    }

    public  interface IHomeModel{
       // void  getHomeBannview();
       <T> void getHomeBannview(INetCallBack<T> iNetCallBack);
       //<T> void getHomeBannview(String id,INetCallBack<T> iNetCallBack);
       <T> void getHomeTabList(INetCallBack<T> iNetCallBack);

    }
    public  interface IHomePresenter{
        void  callHomevBanniew(String string);
        void  getBannerView();
        void getHomeTabList();

    }
}
