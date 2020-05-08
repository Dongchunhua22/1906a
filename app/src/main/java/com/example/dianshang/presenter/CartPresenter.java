package com.example.dianshang.presenter;

import com.example.dianshang.basr.BaseApp;
import com.example.dianshang.basr.BasePresenter;
import com.example.dianshang.basr.Constants;
import com.example.dianshang.bean.AddCart;
import com.example.dianshang.bean.CartListBean;
import com.example.dianshang.bean.CartListBeanDao;
import com.example.dianshang.model.CartModel;
import com.example.dianshang.net.LogUtil;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.ui.CartView;
import com.example.dianshang.ui.MainpageView;

import java.util.ArrayList;
import java.util.List;

public class CartPresenter extends BasePresenter<CartView> {
    private CartModel mCartModel;
    private CartListBeanDao mcartListBeanDao;

    @Override
    protected void initModel() {
        mCartModel = new CartModel();
        addmodel(mCartModel);
        mcartListBeanDao = BaseApp.sContext.getDaoSession().getCartListBeanDao();

    }




    public  void  getCartData(){
        mCartModel.getCartData(new ResutCallback<AddCart>() {
            @Override
            public void onsuccess(AddCart addCart) {
                if (addCart.getErrno() == Constants.SUCCESS_CODE){
                    mView.setCartData(addCart);

                    if (addCart.getData().getCartList() != null && addCart.getData().getCartList().size() > 0) {
                        mcartListBeanDao.insertOrReplaceInTx(addCart.getData().getCartList());
                    }

                    LogUtil.print("数据库size:" + mcartListBeanDao.queryBuilder().list().size());
                }

            }

            @Override
            public void onfail(String msg) {

            }
        });

    }

    public void updateNumber(int productId, int goodsId, int number, Long id) {
        mCartModel.updateNumber(productId,goodsId,number,id, new ResutCallback<AddCart>() {
            @Override
            public void onsuccess(AddCart addCart) {

            }

            @Override
            public void onfail(String msg) {

            }
        });


    }

    public void deleteGoods(final ArrayList<Integer>ids) {
        StringBuilder productIds = new StringBuilder();
        for (int i = 0; i < ids.size(); i++) {
            productIds.append(ids.size()+"");

        }
        mCartModel.deleteGoods(productIds.toString(), new ResutCallback<AddCart>() {
            @Override
            public void onsuccess(AddCart addCart) {
                deleteSuccess(ids);



            }

            @Override
            public void onfail(String msg) {

            }
        });

    }

    private void deleteSuccess(ArrayList<Integer> ids) {
        for (int i = 0; i < ids.size(); i++) {
            List<CartListBean> list = mcartListBeanDao.queryBuilder().where(CartListBeanDao.Properties.Product_id.eq(ids.get(i))).list();
            if (list != null && list.size() > 0) {
                mcartListBeanDao.deleteInTx(list);
            }
        }

    }
}
