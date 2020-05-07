package com.example.daxiang.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public  abstract class BaseActivity<P extends  BasePresenter>  extends AppCompatActivity implements  BaseView{
    protected   P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mPresenter=initPresenter();
        mPresenter.AttachView(this);
        initView();
        initData();
        initListener();


    }
//实例化p层对象
    protected abstract P initPresenter();

    public  abstract  void  initView();
    public  abstract  void  initData();
    public  abstract  void  initListener();
    public abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.disAttachView();
    }
}
