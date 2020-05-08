package com.example.dianshang.model;

import com.example.dianshang.basr.BaseModel;
import com.example.dianshang.bean.GoodsList;
import com.example.dianshang.net.HttpUtil;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.net.RxUtils;
import com.example.dianshang.presenter.ResultSubScriber;

public class GoodsListModel extends BaseModel {

    public void getData(int categoryId, int page, int size, final ResutCallback<GoodsList> callback) {
        addDisposable(
                HttpUtil.getInstance()
                .getApiService()
                .getGoodList(categoryId,page,size)
                .compose(RxUtils.<GoodsList>rxSchedulerHelper())
                .subscribeWith(new ResultSubScriber<GoodsList>() {
                    @Override
                    protected void onSuccess(GoodsList goodsList) {
                        callback.onsuccess(goodsList);
                    }
                })


        );
    }
}
