package com.example.dianshang.mainpage;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.dianshang.R;
import com.example.dianshang.basr.BaseFragment;
import com.example.dianshang.basr.BasePresenter;
import com.example.dianshang.presenter.CartPresenter;
import com.example.dianshang.presenter.MainPagePresenter;
import com.example.dianshang.presenter.MainPresenter;
import com.example.dianshang.ui.CartView;
import com.example.dianshang.ui.MainpageView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainpageFragment extends BaseFragment<MainPagePresenter> implements MainpageView {
    @BindView(R.id.tv)
    TextView mTv;

    public  static MainpageFragment newInstance(){
        MainpageFragment fragment = new MainpageFragment();
        return  fragment;
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected MainPagePresenter initPresener() {
        return new MainPagePresenter();
    }

    @Override
    protected int getlayout() {
        return R.layout.fragment_mainpage ;
    }
    @OnClick({R.id.tv})
    public void click(View v){
        go2Search();
    }
    private void go2Search() {
        startActivity(new Intent(getContext(), SearchActivity.class));
    }

}
