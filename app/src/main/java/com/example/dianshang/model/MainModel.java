package com.example.dianshang.model;

import android.util.Log;

import com.example.dianshang.basr.BaseModel;
import com.example.dianshang.bean.TopicBean;
import com.example.dianshang.net.ApiService;
import com.example.dianshang.net.HttpUtil;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.net.RxUtils
        ;
import com.example.dianshang.presenter.ResultSubScriber;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class MainModel extends BaseModel {
    public void getData() {
      /*  ApiService apiService = HttpUtil.getInstance().getApiService();
        apiService.getTopic(1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<TopicBean>() {
                    @Override
                    public void onNext(TopicBean topicBean) {
                        Log.d("222", "onNext: "+topicBean.toString());

                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("1111", "onError: "+t.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
       /* ResultSubScriber<TopicBean> subScriber = HttpUtil.getInstance()
                .getApiService()
                .getTopic(1, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResultSubScriber<TopicBean>() {
                    @Override
                    protected void onSuccess(TopicBean topicBean) {
                        Log.d("11", "onSuccess: " + topicBean.toString());

                    }
                });
        addcompositeDisposable(subScriber);*/

        addDisposable(
                HttpUtil.getInstance()
                .getApiService()
                .getTopic(1,10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResultSubScriber<TopicBean>() {
                    @Override
                    protected void onSuccess(TopicBean topicBean) {

                    }
                })




        );


    }




}
