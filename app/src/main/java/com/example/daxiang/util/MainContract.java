package com.example.daxiang.util;

import com.example.daxiang.base.BaseView;

public class MainContract {
    public interface IMainView extends BaseView {

    }

    public interface IMainMode{
        <T>  void getRecommendList(INetCallBack<T> netCallBack);
    }

    public interface IMainPresenter{

        void getRecommendList();

//            它两交互时候，需要什么操作
    }
}
