package com.example.dianshang.basr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dianshang.BaseView;
import com.example.dianshang.net.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment <P extends BasePresenter> extends Fragment implements BaseView {
    private Unbinder mUnbinder;
    public P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getlayout(), null);
        mUnbinder = ButterKnife.bind(this, inflate);


        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      mPresenter= initPresener();
      if (mPresenter!=null){
          mPresenter.attachView(this);
      }
      initView(view);
      initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mUnbinder.unbind();
        }
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    protected abstract P initPresener();


    protected abstract int getlayout();

    @Override
    public void showToast(String msg) {
        ToastUtil.showToastShort(msg);
    }
}
