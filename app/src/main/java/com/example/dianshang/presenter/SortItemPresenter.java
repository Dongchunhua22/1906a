package com.example.dianshang.presenter;

import com.example.dianshang.basr.BasePresenter;
import com.example.dianshang.basr.Constants;
import com.example.dianshang.bean.SortItemBean;
import com.example.dianshang.model.SortItemModel;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.ui.SortItemView;
import com.example.dianshang.ui.SortView;

public class SortItemPresenter extends BasePresenter<SortItemView> {
    private SortItemModel mSortItemModel;
    @Override
    protected void initModel() {
        mSortItemModel = new SortItemModel();
        addmodel(mSortItemModel);

    }
    public void getData(int id) {
        mSortItemModel.getData(id, new ResutCallback<SortItemBean>() {
            @Override
            public void onsuccess(SortItemBean sortItemBean) {
                if (sortItemBean.getErrno() == Constants.SUCCESS_CODE){
                    mView.setData(sortItemBean);
                }
                
            }

            @Override
            public void onfail(String msg) {

            }

          
        });
    }
}
