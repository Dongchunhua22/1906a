package com.example.dianshang.model;

import com.example.dianshang.basr.BaseModel;
import com.example.dianshang.bean.LoginBean;
import com.example.dianshang.net.HttpUtil;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.net.RxUtils;
import com.example.dianshang.presenter.ResultSubScriber;

public class LoginModel extends BaseModel {
    public void login(String name, String pw, final ResutCallback<LoginBean> callback) {
        addDisposable(
                HttpUtil.getInstance()
                .getApiService()
                .login(name,pw)
                .compose(RxUtils.<LoginBean>rxSchedulerHelper())
                .subscribeWith(new ResultSubScriber<LoginBean>() {
                    @Override
                    protected void onSuccess(LoginBean loginBean) {
                        callback.onsuccess(loginBean);
                    }
                })
        );

    }
}
