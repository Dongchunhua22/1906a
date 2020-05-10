package com.example.daxiang.login.model;

import com.example.daxiang.login.contract.AffirmContract;
import com.example.daxiang.util.INetCallBack;
public class AffirmRegisterModel implements AffirmContract.IAffirmMode {
    @Override
    public <T> void register(String phoneNum, String password, String affirm_password, INetCallBack<T> iNetCallBack) {

    }
}
