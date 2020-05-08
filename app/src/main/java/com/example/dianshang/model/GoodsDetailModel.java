package com.example.dianshang.model;

import com.example.dianshang.basr.BaseModel;
import com.example.dianshang.bean.AddCart;
import com.example.dianshang.bean.GoodsDetail;
import com.example.dianshang.bean.RelateGoods;
import com.example.dianshang.net.HttpUtil;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.net.RxUtils;
import com.example.dianshang.presenter.ResultSubScriber;

public class GoodsDetailModel  extends BaseModel {
    public void getGoodsDetail(int id, final ResutCallback<GoodsDetail> callback) {
        addDisposable(
                HttpUtil.getInstance()
                .getApiService()
                .getGoodsDetail(id)
                .compose(RxUtils.<GoodsDetail>rxSchedulerHelper())
                .subscribeWith(new ResultSubScriber<GoodsDetail>() {
                    @Override
                    protected void onSuccess(GoodsDetail goodsDetail) {
                        callback.onsuccess(goodsDetail);
                    }
                })

        );

    }

    public void getRelateGoods(int id, final ResutCallback<RelateGoods> callback) {
        addDisposable (
                HttpUtil.getInstance()
                .getApiService()
                .getRelateGoods(id)
                .compose(RxUtils.<RelateGoods>rxSchedulerHelper())
                .subscribeWith(new ResultSubScriber<RelateGoods>() {
                    @Override
                    protected void onSuccess(RelateGoods goodsDetail) {
                        callback.onsuccess(goodsDetail);
                    }
                })

        );
    }


    public void addCart(int goods_id, int number, int productId,
                        final ResutCallback<AddCart> callBack) {
        addDisposable(
                HttpUtil.getInstance()
                .getApiService()
                .addCart2(goods_id,number,productId)
                .compose(RxUtils.<AddCart>rxSchedulerHelper())
                .subscribeWith(new ResultSubScriber<AddCart>() {
                    @Override
                    protected void onSuccess(AddCart addCart) {
                        callBack.onsuccess(addCart);
                    }
                })
        );

    }

    public void getCartData(final ResutCallback<AddCart> callback) {
        addDisposable(
                HttpUtil.getInstance()
                .getApiService()
                .getCartData2()
                .compose(RxUtils.<AddCart>rxSchedulerHelper())
                .subscribeWith(new ResultSubScriber<AddCart>() {
                    @Override
                    protected void onSuccess(AddCart addCart) {
                        callback.onsuccess(addCart);
                    }
                })
        );
    }
}
