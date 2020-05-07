package com.example.daxiang.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment <T extends BasePresenter> extends Fragment implements BaseView {
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  LayoutInflater.from(container.getContext()).inflate(getLayoutID(),container,false);

        mPresenter =  initPresenter();
        if(null != mPresenter ){
            mPresenter.AttachView(this);
        }

        initView(view);
        initData();
        initLinstener();


        return view;
    }

    protected abstract void initView(View view);


    protected abstract T initPresenter();


    protected abstract void initLinstener();

    protected abstract void initData();



    public abstract  int getLayoutID();
}
