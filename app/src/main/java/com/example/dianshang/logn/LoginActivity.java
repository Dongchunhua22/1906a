package com.example.dianshang.logn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dianshang.R;
import com.example.dianshang.basr.BaseActivity;
import com.example.dianshang.basr.BaseApp;
import com.example.dianshang.basr.Constants;
import com.example.dianshang.bean.LoginBean;
import com.example.dianshang.net.ToastUtil;
import com.example.dianshang.presenter.LoginPresenter;
import com.example.dianshang.ui.LoginView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {
    @BindView(R.id.edit_nickname)
    EditText mEditNickname;
    @BindView(R.id.edit_pw)
    EditText mEditPw;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_register)
    TextView mTvRegister;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.btn_login, R.id.tv_register})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_register:
                go2Register();
                break;
        }
    }

    private void go2Register() {
        Intent intent = new Intent(this, RegistActivity.class);
        startActivityForResult(intent,200);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200&&resultCode==300){
            mEditNickname.setText(data.getStringExtra(Constants.USERNAME));
        }
    }

   /*  @Override
    public void show(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        if(msg.equals("登录成功")) {
            finish();
        }
    }
*/

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
        if(msg.equals("登录成功")) {
            finish();
        }
    }

    private void login() {
        String name = mEditNickname.getText().toString().trim();
        String pw = mEditPw.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pw)){
            ToastUtil.showToastShort("用户名或者密码不能为空");
            return;
        }

        mpresenter.login(name,pw);
    }


    @Override
    public void loginSuccess(LoginBean loginBean) {
        showToast("登录成功");
        finish();
        //登录成功需要修改application中的状态
        BaseApp.isLogin = true;
        //通知mefragment设置用户名称,并且点击的时候不能再进入登录页面了
        EventBus.getDefault().post(loginBean);
    }
}
