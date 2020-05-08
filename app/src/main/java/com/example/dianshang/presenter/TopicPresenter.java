package com.example.dianshang.presenter;

import android.view.View;

import com.example.dianshang.basr.BasePresenter;
import com.example.dianshang.basr.Constants;
import com.example.dianshang.bean.TopicBean;
import com.example.dianshang.model.TopicModel;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.ui.SortView;
import com.example.dianshang.ui.TopicView;

public class TopicPresenter extends BasePresenter<TopicView> {

    private TopicModel topicModel;

    @Override
    protected void initModel() {
        topicModel = new TopicModel();
        addmodel(topicModel);

    }

    public void getTopic(int page, int size) {
        topicModel.getTopic(page,size, new ResutCallback<TopicBean>() {
            @Override
            public void onsuccess(TopicBean topicBean) {
                if (topicBean.getErrno()== Constants.SUCCESS_CODE){
                    mView.setTopic(topicBean.getData());

                }

            }

            @Override
            public void onfail(String msg) {

            }
        });

    }



}
