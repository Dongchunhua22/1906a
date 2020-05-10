package com.example.daxiang.login.prenster;


import com.example.daxiang.base.BasePresenter;
import com.example.daxiang.login.contract.AffirmContract;
import com.example.daxiang.login.model.AffirmRegisterModel;

public class AffirmRegisterPresenter extends BasePresenter<AffirmContract.IAffirmView> implements AffirmContract.IAffirmPresenter {

    private AffirmContract.IAffirmMode iAffirmMode;
    public AffirmRegisterPresenter() {

        iAffirmMode = new AffirmRegisterModel();

    }

    @Override
    public void register(String phoneNum, String password, String affirm_password) {

    }
}
