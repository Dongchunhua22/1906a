package com.example.dianshang.model;

import com.example.dianshang.basr.BaseModel;
import com.example.dianshang.basr.BasePresenter;
import com.example.dianshang.bean.SortItemBean;
import com.example.dianshang.net.HttpUtil;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.net.RxUtils;
import com.example.dianshang.presenter.ResultSubScriber;
import com.example.dianshang.ui.SortItemView;

public class SortItemModel  extends BaseModel {
    public void getData(int id, final ResutCallback<SortItemBean> callBack) {
       addDisposable(HttpUtil.getInstance()
               .getApiService()
               .getSortItem(id)
               .compose(RxUtils.<SortItemBean>rxSchedulerHelper())
               .subscribeWith(new ResultSubScriber<SortItemBean>() {
                   @Override
                   protected void onSuccess(SortItemBean sortItemBean) {
                       callBack.onsuccess(sortItemBean);
                   }
               })
       );
    }

}
