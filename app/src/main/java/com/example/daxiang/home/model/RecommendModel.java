package com.example.daxiang.home.model;

import android.util.Log;

import com.example.daxiang.api.URLConstants;
import com.example.daxiang.home.constract.RecommendContract;
import com.example.daxiang.util.INetCallBack;
import com.example.daxiang.util.NetWorkFactory;
import com.example.daxiang.util.ParamsUtils;

import java.util.HashMap;

public class RecommendModel implements RecommendContract.IRecommendMode {
    @Override
    public <T> void getRecommendList(String id, INetCallBack<T> iNetCallBack) {
        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
        commonParams.put("id",id);
        commonParams.put("start","0");
        commonParams.put("number ","0");
        commonParams.put("point_time","0");
//        此处  -- 登录以后，  需要修改

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }
        NetWorkFactory.getInstance().getNetWork().get(URLConstants.RECOMMEND_LIST,commonParams,iNetCallBack);
    }

    @Override
    public <T> void getColumList(INetCallBack<T> iNetCallBack) {

        HashMap<String, String> commonParams = ParamsUtils.getCommonParams();
//        commonParams.put("token","");

        for (String key: commonParams.keySet()) {
            Log.e("TAG","key="+key+",values="+commonParams.get(key));
        }

        NetWorkFactory.getInstance().getNetWork().get(URLConstants.COLUM_LIST,commonParams,iNetCallBack);
    }
}
