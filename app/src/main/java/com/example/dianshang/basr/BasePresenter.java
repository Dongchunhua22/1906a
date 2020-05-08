package com.example.dianshang.basr;

import com.example.dianshang.BaseView;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {
    private ArrayList<BaseModel>mbasemodels;
    public  BasePresenter(){
        initModel();
    }

    protected abstract void initModel();


    public V mView;

    public  void attachView(V view) {
        this.mView=view;
    }
    public  void detachView(){
        mView=null;
        if (mbasemodels!=null){
            for (int i = 0; i < mbasemodels.size(); i++) {
                mbasemodels.get(i).destroy();

            }
        }


    }

    public  void addmodel(BaseModel model){
        if (mbasemodels==null){
            mbasemodels=new ArrayList<>();
        }
        mbasemodels.add(model);

    }



}
