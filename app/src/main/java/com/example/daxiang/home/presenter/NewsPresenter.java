package com.example.daxiang.home.presenter;

import com.example.daxiang.base.BasePresenter;
import com.example.daxiang.bean.NewsBean;
import com.example.daxiang.home.constract.NewsFragmentContract;
import com.example.daxiang.home.constract.RecommendContract;
import com.example.daxiang.home.model.NewsModel;
import com.example.daxiang.util.INetCallBack;

public class NewsPresenter extends BasePresenter<NewsFragmentContract.INewsView> implements RecommendContract.IRecommendPresenter  {
    private NewsFragmentContract.INewsMode iNewsMode;

    @Override
    public void getColumList() {

    }

    public NewsPresenter() {

        iNewsMode = new NewsModel();

    }

    @Override
    public void getRecommendList(String id) {
        iNewsMode.getRecommendList(id,new INetCallBack<NewsBean>() {
            @Override
            public void onSuccess(NewsBean newsBean) {

                mview.setRecommendList(newsBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });


    }
}
