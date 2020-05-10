package com.example.daxiang.login.model;

import android.util.Log;


import com.example.daxiang.api.URLConstants;
import com.example.daxiang.login.contract.LoginContract;
import com.example.daxiang.util.INetCallBack;
import com.example.daxiang.util.NetWorkFactory;
import com.example.daxiang.util.ParamsUtils;

import java.util.HashMap;

public class LoginModel implements LoginContract.ILoginMode {
    @Override
    public <T> void getVerified(String phoneNum, String type, INetCallBack<T> iNetCallBack) {

        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",phoneNum);
        commonParams.put("type",type);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }

        NetWorkFactory.getInstance().getNetWork().post(URLConstants.SENDVERIFIED,commonParams,iNetCallBack);
    }

    @Override
    public <T> void login(String mobile, String smsCode, INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",mobile);
        commonParams.put("sms_code",smsCode);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().post(URLConstants.LOGIN,commonParams,iNetCallBack);
    }


    @Override
    public <T> void checkSmsCode(String phoneNum, String smsCode, String type, INetCallBack<T> iNetCallBack) {
        Log.e("TAG",phoneNum+"验证m层码值："+smsCode);



        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("mobile",phoneNum);
        commonParams.put("type",type);
        commonParams.put("sms_code",smsCode);

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }

        NetWorkFactory.getInstance().getNetWork().post(URLConstants.CHECKSMSCODE,commonParams,iNetCallBack);



    }
}
