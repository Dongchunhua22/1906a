package com.example.daxiang.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daxiang.R;
import com.example.daxiang.base.BaseActivity;
import com.example.daxiang.login.bean.VerfiedBean;
import com.example.daxiang.login.contract.LoginContract;
import com.example.daxiang.login.prenster.LoginPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity  extends BaseActivity<LoginPresenter> implements LoginContract.ILoginView  {
    private EditText phone_num;
    private EditText verfied;
    private Button send_verfied_bug;
    private Button login;

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void initView() {
        phone_num = findViewById(R.id.phone_num);
        verfied= findViewById(R.id.verified);
        send_verfied_bug= findViewById(R.id.send_verfied_bug);
        login= findViewById(R.id.login_but);

    }

    @Override
    public void initData() {

    }
    private   String edit_phone_num;
    private String edit_sms_code;
    @Override
    public void initListener() {

        //        发送验证码
    /*    send_verfied_bug.setOnClickListener(v->{
            //判断咱们手机号是否为空，判断手机号是否正确，发送咱们验证码
            String phonenum = phone_num.getText().toString();
            if( !TextUtils.isEmpty(phonenum) && isMobileNO(phonenum)){
//                  表示可以发送验证码  type 4表示登录发送验证码
                mPresenter.getVerified(phonenum,"4");
            }else Toast.makeText(LoginActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();
        });
     */
     send_verfied_bug.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             //判断咱们手机号是否为空，判断手机号是否正确，发送咱们验证码
             String phonenum = phone_num.getText().toString();
             if( !TextUtils.isEmpty(phonenum) && isMobileNO(phonenum)){
//                  表示可以发送验证码  type 4表示登录发送验证码
                 mPresenter.getVerified(phonenum,"4");
             }else Toast.makeText(LoginActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();

         }
     });

//登录
       /* login.setOnClickListener(v->{
            edit_phone_num = phone_num.getText().toString();
            edit_sms_code = verfied.getText().toString();
            if( !TextUtils.isEmpty(edit_phone_num) && isMobileNO(edit_phone_num)){
                if( !TextUtils.isEmpty(edit_sms_code)){
//                    需要用正则表达式判断验证码是否是6位，且都是数字
                    Pattern pattern = Pattern.compile("\\d{6}");
                    boolean matches = pattern.matcher(edit_sms_code).matches();
                    if(matches){
                        //                    判断你得手机号，和你发送得验证码是否正确，如果正确，调用登录接口
//                        提交服务器进行判读
                        Log.e("TAG",edit_phone_num+"验证码值："+edit_sms_code);

                        mPresenter.checkSmsCode(edit_phone_num,edit_sms_code,"4");
//                        服务器给我们下发得验证码短信，接收     手机号给他，验证码也给他，
//                                              ture
//                    如果不正确，提示用户
                    }else Toast.makeText(LoginActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(LoginActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(LoginActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();
        });*/
     login.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             edit_phone_num = phone_num.getText().toString();
             edit_sms_code = verfied.getText().toString();
             if( !TextUtils.isEmpty(edit_phone_num) && isMobileNO(edit_phone_num)){
                 if( !TextUtils.isEmpty(edit_sms_code)){
//                    需要用正则表达式判断验证码是否是6位，且都是数字
                     Pattern pattern = Pattern.compile("\\d{6}");
                     boolean matches = pattern.matcher(edit_sms_code).matches();
                     if(matches){
                         //                    判断你得手机号，和你发送得验证码是否正确，如果正确，调用登录接口
//                        提交服务器进行判读
                         Log.e("TAG",edit_phone_num+"验证码值："+edit_sms_code);

                         mPresenter.checkSmsCode(edit_phone_num,edit_sms_code,"4");
//                        服务器给我们下发得验证码短信，接收     手机号给他，验证码也给他，
//                                              ture
//                    如果不正确，提示用户
                     }else Toast.makeText(LoginActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
                 }else Toast.makeText(LoginActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
             }else Toast.makeText(LoginActivity.this, "请输入正确得手机号", Toast.LENGTH_SHORT).show();

         }
     });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
//没有注册过，需要先注册


    //    发送验证码返回
    @Override
    public void getVerified(VerfiedBean s) {
        if(s.getCode() ==1){
            Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "错误", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void getLoginResult(String string) {

    }

    //    验证码是否正确返回
    @Override
    public void checkSmsCodeResult(VerfiedBean verfiedBean) {
        if(verfiedBean.getCode() ==1){
//            表明验证码正确
//            自动去登录操作了。
//            edit_phone_num,edit_sms_code

            mPresenter.login(edit_phone_num,edit_sms_code);

        }else Toast.makeText(this, "验证码输入错误", Toast.LENGTH_SHORT).show();


    }


    /**
     * 验证手机号码
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles){
        boolean flag = false;
        try{
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        }catch(Exception e){
//            LOG.error("验证手机号码错误", e);
            Log.e("TAG","手机号错误"+e.getMessage());
            flag = false;
        }
        return flag;
    }

    public void startRegister(View view) {
        Intent intent = new Intent(this,RegisterMSMCodeActivity.class);
        startActivity(intent);
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }*/
}
