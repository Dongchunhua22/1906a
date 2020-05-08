package com.example.dianshang.model;

import com.example.dianshang.basr.BaseModel;
import com.example.dianshang.bean.SortTabBean;
import com.example.dianshang.net.HttpUtil;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.net.RxUtils;
import com.example.dianshang.presenter.ResultSubScriber;

public class SortModel  extends BaseModel {
    public  void  getTab(final ResutCallback<SortTabBean> callback){
        addDisposable(
                HttpUtil.getInstance()
                        .getApiService()
                        .getSortTab()
                        .compose(RxUtils.<SortTabBean>rxSchedulerHelper())
                        .subscribeWith(new ResultSubScriber<SortTabBean>() {
                            @Override
                            protected void onSuccess(SortTabBean sortTabBean) {
                                callback.onsuccess(sortTabBean);
                            }
                        })
        );

    }
}
