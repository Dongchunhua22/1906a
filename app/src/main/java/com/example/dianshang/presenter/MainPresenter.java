package com.example.dianshang.presenter;

import com.example.dianshang.MainView;
import com.example.dianshang.basr.BasePresenter;
import com.example.dianshang.model.MainModel;

public class MainPresenter  extends BasePresenter<MainView> {

    private MainModel mainModel;

    public void getData() {
        mainModel.getData();
    }

    @Override
    protected void initModel() {
        mainModel = new MainModel();
        addmodel(mainModel);
    }
}
