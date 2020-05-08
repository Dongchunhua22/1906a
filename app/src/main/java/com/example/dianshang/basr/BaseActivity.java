package com.example.dianshang.basr;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dianshang.BaseView;
import com.example.dianshang.net.ToastUtil;

import butterknife.ButterKnife;

public abstract  class BaseActivity<P extends  BasePresenter>  extends AppCompatActivity implements BaseView {
   public P mpresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        mpresenter=initPresenter();
        if (mpresenter!=null){
            mpresenter.attachView(this);
        }

        initView();
        initData();

    }

    protected abstract int getLayout();

    protected abstract P initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mpresenter!=null){
            mpresenter.detachView();
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToastShort(msg);
    }
}
