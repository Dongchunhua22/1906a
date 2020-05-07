package com.example.daxiang.util;

import android.util.Log;

import com.example.daxiang.api.URLConstants;
import com.example.daxiang.base.BaseModel;

import java.util.HashMap;

public class MainModel  extends BaseModel implements MainContract.IMainMode{
    public  MainModel(){

    }

    @Override
    public <T> void getRecommendList(INetCallBack<T> netCallBack) {
        //        测试添加参数
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("start","0");
        commonParams.put("number","0");
        commonParams.put("point_time ","0");

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }

        NetWorkFactory.getInstance().getNetWork().get(URLConstants.VEDIO_LIST,commonParams ,netCallBack);
    }
}
