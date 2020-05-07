package com.example.daxiang.home.constract;

import com.example.daxiang.base.BaseView;
import com.example.daxiang.bean.NewsBean;
import com.example.daxiang.util.INetCallBack;

public class NewsFragmentContract {

    public interface INewsView extends BaseView {
        void  setRecommendList(NewsBean newsBean);
    }
    public interface INewsMode{
        <T> void getRecommendList(String tabID, INetCallBack<T> iNetCallBack);
    }
    public interface INewsPresenter{
        void getRecommend(String string);
    }
}
