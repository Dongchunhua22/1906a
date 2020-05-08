package com.example.dianshang.basr;

import com.example.dianshang.bean.RelateGoods;
import com.example.dianshang.bean.TopicBean;
import com.example.dianshang.net.ResutCallback;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {
    CompositeDisposable mcompositeDisposable;

    public void destroy() {
        if (mcompositeDisposable!=null){
            mcompositeDisposable.clear();
        }


    }

    public  void  addDisposable(Disposable disposable){
        if (mcompositeDisposable==null){
            mcompositeDisposable=new CompositeDisposable();
        }
        mcompositeDisposable.add(disposable);
    }



}
