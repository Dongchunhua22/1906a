package com.example.dianshang.presenter;

import com.example.dianshang.basr.BasePresenter;
import com.example.dianshang.basr.Constants;
import com.example.dianshang.bean.GoodsList;
import com.example.dianshang.model.GoodsListModel;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.ui.GoodsListView;

public class GoodsListPresenter extends BasePresenter<GoodsListView> {

    private GoodsListModel mGoodsListModel;
    @Override
    protected void initModel() {
        mGoodsListModel = new GoodsListModel();
        addmodel(mGoodsListModel);
    }

    public void getData(int categoryId, int page, int size) {
       mGoodsListModel.getData(categoryId,page,size, new ResutCallback<GoodsList>() {
           @Override
           public void onsuccess(GoodsList goodsList) {
               if (goodsList.getErrno() == Constants.SUCCESS_CODE){
                   mView.setGoodList(goodsList);
               }

           }

           @Override
           public void onfail(String msg) {

           }
       });
    }
}
