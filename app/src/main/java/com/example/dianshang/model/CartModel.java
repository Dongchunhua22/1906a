package com.example.dianshang.model;

import com.example.dianshang.basr.BaseModel;
import com.example.dianshang.bean.AddCart;
import com.example.dianshang.bean.GoodsDetail;
import com.example.dianshang.net.HttpUtil;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.net.RxUtils;
import com.example.dianshang.presenter.ResultSubScriber;

import retrofit2.http.HTTP;

public class CartModel extends BaseModel {
    public  void getCartData(final ResutCallback<AddCart>callback){
        addDisposable(
                HttpUtil.getInstance()
                .getApiService()
                .getCartData2()
                .compose(RxUtils.<AddCart>rxSchedulerHelper())
                .subscribeWith(new ResultSubScriber<AddCart>() {
                    @Override
                    protected void onSuccess(AddCart goodsDetail) {
                        callback.onsuccess(goodsDetail);
                    }
                })
        );
    }



    /*public void updateNumber(int productId, int goodsId, int number, int id, final ResutCallback<AddCart> callback) {
        addDisposable(
                HttpUtil.getInstance()
                .getApiService()
                .updateGoodsNumber(productId,goodsId,number,id)
                .compose(RxUtils.<AddCart>rxSchedulerHelper())
                .subscribeWith(new ResultSubScriber<AddCart>() {
                    @Override
                    protected void onSuccess(AddCart goodsDetail) {
                        callback.onsuccess(goodsDetail);
                    }
                })
        );
    }*/

    public void updateNumber(int productId, int goodsId, int number, Long id, final ResutCallback<AddCart> callback) {
        addDisposable(
                HttpUtil.getInstance()
                .getApiService()
                .updateGoodsNumber(productId,goodsId,number,id)
                .compose(RxUtils.<AddCart>rxSchedulerHelper())
                .subscribeWith(new ResultSubScriber<AddCart>() {
                    @Override
                    protected void onSuccess(AddCart goodsDetail) {
                        callback.onsuccess(goodsDetail);
                    }
                })
        );


    }

    public void deleteGoods(String productIds, final ResutCallback<AddCart> callback) {
        addDisposable(
                HttpUtil.getInstance()
                .getApiService()
                .deleteGoods(productIds)
                .compose(RxUtils.<AddCart>rxSchedulerHelper())
                .subscribeWith(new ResultSubScriber<AddCart>() {
                    @Override
                    protected void onSuccess(AddCart goodsDetail) {
                        callback.onsuccess(goodsDetail);
                    }
                })
        );



    }



}
