package com.example.dianshang.presenter;

import android.view.View;

import com.example.dianshang.basr.BasePresenter;
import com.example.dianshang.basr.Constants;
import com.example.dianshang.bean.AddCart;
import com.example.dianshang.bean.GoodsDetail;
import com.example.dianshang.bean.RelateGoods;
import com.example.dianshang.model.GoodsDetailModel;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.ui.GoodsDetailView;

public class GoodsDetailPresenter  extends BasePresenter<GoodsDetailView> {
    private GoodsDetailModel mGoodsDetailModel;
    @Override
    protected void initModel() {
        mGoodsDetailModel = new GoodsDetailModel();
       addmodel(mGoodsDetailModel);

    }

    public  void  getDail(int id){
        mGoodsDetailModel.getGoodsDetail(id, new ResutCallback<GoodsDetail>() {
            @Override
            public void onsuccess(GoodsDetail goodsDetail) {
                if (goodsDetail.getErrno() == Constants.SUCCESS_CODE){
                    mView.setGoodsDetai(goodsDetail);
                }

            }

            @Override
            public void onfail(String msg) {

            }
        });
    }

    public  void  getRelateGoods(int id){
        mGoodsDetailModel.getRelateGoods(id, new ResutCallback<RelateGoods>() {
            @Override
            public void onsuccess(RelateGoods relateGoods) {
                if (relateGoods.getErrno() == Constants.SUCCESS_CODE){
                    mView.setRelateGoods(relateGoods);
                }


            }

            @Override
            public void onfail(String msg) {

            }
        });
    }

    public void getCartData() {
        mGoodsDetailModel.getCartData(new ResutCallback<AddCart>() {
            @Override
            public void onsuccess(AddCart addCart) {
                if (addCart.getErrno() == Constants.SUCCESS_CODE){
                    mView.setCartData(addCart);
                }

            }

            @Override
            public void onfail(String msg) {

            }
        });

    }

    public void addCart(int goods_id, int number, int productid) {
        mGoodsDetailModel.addCart(goods_id,number,productid, new ResutCallback<AddCart>() {
            @Override
            public void onsuccess(AddCart addCart) {
                if (addCart.getErrno() == Constants.SUCCESS_CODE){
                    mView.setAddCart(addCart);
                }
            }

            @Override
            public void onfail(String msg) {

            }
        });{

        }
    }
}
