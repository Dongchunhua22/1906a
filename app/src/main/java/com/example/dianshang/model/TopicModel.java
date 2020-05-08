package com.example.dianshang.model;

import com.example.dianshang.basr.BaseModel;
import com.example.dianshang.bean.TopicBean;
import com.example.dianshang.net.HttpUtil;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.net.RxUtils;
import com.example.dianshang.presenter.ResultSubScriber;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TopicModel  extends BaseModel {

    public void getTopic(int page, int size, final ResutCallback<TopicBean> Callback) {
        addDisposable(
                HttpUtil.getInstance()
                .getApiService()
                .getTopic(page,size)
                .compose(RxUtils.<TopicBean>rxSchedulerHelper())
                .subscribeWith(new ResultSubScriber<TopicBean>() {
                    @Override
                    protected void onSuccess(TopicBean topicBean) {
                        Callback.onsuccess(topicBean);
                    }
                })
        );
    }
}
