package com.example.dianshang.me;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dianshang.R;
import com.example.dianshang.basr.BaseApp;
import com.example.dianshang.basr.BaseFragment;
import com.example.dianshang.basr.BasePresenter;
import com.example.dianshang.basr.Constants;
import com.example.dianshang.bean.LoginBean;
import com.example.dianshang.logn.LoginActivity;
import com.example.dianshang.net.SpUtil;
import com.example.dianshang.presenter.CartPresenter;
import com.example.dianshang.presenter.MePresenter;
import com.example.dianshang.ui.CartView;
import com.example.dianshang.ui.Meview;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

public class MeFragment extends BaseFragment<MePresenter> implements Meview {
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv_name)
    TextView mTvName;

    public  static MeFragment newInstance(){
        MeFragment fragment = new MeFragment();
        return  fragment;
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        String name = (String) SpUtil.getParam(Constants.USERNAME, "");
        if (!TextUtils.isEmpty(name)) {
            mTvName.setText(name);
        }else {
            mTvName.setText("未登录");
        }

    }


    @OnClick({R.id.iv, R.id.tv_name})
    public void click(View v) {
        ////登录了避免再次点击的时候跳转登录页面
        //判断条件就是看本地是否有存储的token,如果有,代码登录了
        //的确可以这么写,但是好多地方都需要是否登录的状态,所以我们可以考虑在一个地方统一获取
        //sp本质是一个xml文件,从文件中取数据不如从内存中取块,所以这个判断逻辑可以添加到application
        /*String token = (String) SpUtil.getParam(Constants.TOKEN, "");
        if (TextUtils.isEmpty(token)) {
            go2Login();
        }*/
        if (!BaseApp.isLogin) {
            go2Login();
        }
    }

    private void go2Login() {
        startActivity(new Intent(getContext(), LoginActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void receiveLogin(LoginBean loginBean) {
        //设置用户名
        mTvName.setText(loginBean.getData().getUserInfo().getNickname());
        //登录了避免再次点击的时候跳转登录页面
    }


    @Override
    protected MePresenter initPresener() {
        return new MePresenter();
    }

    @Override
    protected int getlayout() {
        return R.layout.fragment_main ;
    }
}
