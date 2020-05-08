package com.example.dianshang.model;

import com.example.dianshang.basr.BaseModel;
import com.example.dianshang.bean.RegisterBean;
import com.example.dianshang.net.HttpUtil;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.net.RxUtils;
import com.example.dianshang.presenter.ResultSubScriber;

import retrofit2.http.HTTP;

public class RegistModel  extends BaseModel {
    public void getData(String name, String pass, final ResutCallback<RegisterBean> callback) {
        addDisposable(
                HttpUtil
                .getInstance()
                .getApiService()
                .register(name,pass)
                .compose(RxUtils.<RegisterBean>rxSchedulerHelper())
                .subscribeWith(new ResultSubScriber<RegisterBean>() {
                    @Override
                    protected void onSuccess(RegisterBean registerBean) {
                        callback.onsuccess(registerBean);
                    }
                })
        );

    }
}
