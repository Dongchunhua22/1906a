package com.example.daxiang.home.model;

import com.example.daxiang.base.BaseModel;
import com.example.daxiang.home.constract.HomeConstract;
import com.example.daxiang.home.presenter.HomePresenterImp;
import com.example.daxiang.util.INetCallBack;
import com.example.daxiang.util.NetWorkFactory;

public class HomeModelImp extends BaseModel implements HomeConstract.IHomeModel {
    private HomeConstract.IHomePresenter iHomePresenter;

    public HomeModelImp(HomeConstract.IHomePresenter iHomePresenter) {

        this.iHomePresenter = iHomePresenter;
    }


    @Override
    public <T> void getHomeBannview(INetCallBack<T> netCallBack) {
       // 网络请求i结束了   参数如何构建
        iHomePresenter.callHomevBanniew("");
        NetWorkFactory.getInstance().getNetWork().get("/app/ssdf",netCallBack);
    }

    @Override
    public <T> void getHomeTabList(INetCallBack<T> iNetCallBack) {

    }
}
