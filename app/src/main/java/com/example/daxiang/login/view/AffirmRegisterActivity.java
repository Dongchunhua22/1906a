package com.example.daxiang.login.view;

import android.widget.Button;
import android.widget.EditText;

import com.example.daxiang.R;
import com.example.daxiang.base.BaseActivity;
import com.example.daxiang.login.bean.AffirmRegisterBean;
import com.example.daxiang.login.contract.AffirmContract;
import com.example.daxiang.login.prenster.AffirmRegisterPresenter;

public class AffirmRegisterActivity extends BaseActivity<AffirmRegisterPresenter> implements AffirmContract.IAffirmView {
    private EditText affreg_passward;
    private EditText affreg_affirmpassword;
    private Button arrirm_regbug;
    @Override
    protected AffirmRegisterPresenter initPresenter() {
        return new AffirmRegisterPresenter();
    }

    @Override
    public void initView() {
        affreg_passward = findViewById(R.id.affreg_passward);
        affreg_affirmpassword = findViewById(R.id.affreg_affirmpassword);
        arrirm_regbug  = findViewById(R.id.arrirm_regbug);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_affirm_register;
    }

    @Override
    public void registerResult(AffirmRegisterBean registerBean) {

    }
}
