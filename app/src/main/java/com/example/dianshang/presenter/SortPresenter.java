package com.example.dianshang.presenter;

import com.example.dianshang.basr.BasePresenter;
import com.example.dianshang.basr.Constants;
import com.example.dianshang.bean.SortTabBean;
import com.example.dianshang.model.SortModel;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.ui.MainpageView;
import com.example.dianshang.ui.SortView;

public class SortPresenter extends BasePresenter<SortView> {
    private SortModel mSortModel;
    @Override
    protected void initModel() {
        mSortModel = new SortModel();
        //addModel(mSortModel);

    }

    public void getTab() {
        mSortModel.getTab(new ResutCallback<SortTabBean>() {
            @Override
            public void onsuccess(SortTabBean sortTabBean) {
                if (sortTabBean.getErrno()== Constants.SUCCESS_CODE)
                    mView.setTab(sortTabBean);

            }

            @Override
            public void onfail(String msg) {

            }
        });

    }


}
