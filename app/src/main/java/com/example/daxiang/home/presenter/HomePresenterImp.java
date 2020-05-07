package com.example.daxiang.home.presenter;

import android.util.Log;

import com.example.daxiang.base.BasePresenter;
import com.example.daxiang.home.constract.HomeConstract;
import com.example.daxiang.home.model.HomeModelImp;
import com.example.daxiang.home.view.HomeActivity;
import com.example.daxiang.util.INetCallBack;
import com.example.daxiang.util.User;

public class HomePresenterImp extends BasePresenter<HomeActivity> implements HomeConstract.IHomePresenter {

    private final HomeModelImp homeModelImp;
    //private HomeConstract.IHomeView homeView;

    public HomePresenterImp() {
      //  this.homeView = homeView;
        homeModelImp = new HomeModelImp(this);
    }


    @Override
    public void callHomevBanniew(String string) {

    }

    @Override
    public void getBannerView() {
        homeModelImp.getHomeBannview(new INetCallBack<User>() {
            @Override
            public void onSuccess(User user) {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }

    @Override
    public void getHomeTabList() {

    }
}
