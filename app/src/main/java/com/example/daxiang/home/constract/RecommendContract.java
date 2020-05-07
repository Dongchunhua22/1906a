package com.example.daxiang.home.constract;

import com.example.daxiang.base.BaseView;
import com.example.daxiang.bean.ColunmBean;
import com.example.daxiang.bean.NewsBean;
import com.example.daxiang.util.INetCallBack;

public class RecommendContract {

    public interface IRecommendView extends BaseView {
        void  setRecommendList(NewsBean string);
        void setColumList(ColunmBean columList);
    }
    public interface IRecommendMode{
        <T> void getRecommendList(String id, INetCallBack<T> iNetCallBack);
        <T> void getColumList(INetCallBack<T> iNetCallBack);
    }


    public interface IRecommendPresenter {

        void getColumList();
        void getRecommendList(String id);

    }
}
