package com.example.daxiang;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Network;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.daxiang.app.AppManager;
import com.example.daxiang.base.BaseActivity;
import com.example.daxiang.util.MainContract;
import com.example.daxiang.util.MainPresenter;
import com.example.daxiang.util.NetWorkFactory;

public class MainActivity extends BaseActivity<MainPresenter>implements MainContract.IMainView {
    private long fristKeyBackTime = 0;
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //NetWorkFactory.getInstance().getNetWork().get("");
        mPresenter.getRecommendList();

    }

    @Override
    public void initListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //        菜单、home、返回键 点击了返回键
//        点击屏幕的时候，   按下，抬起， 移动  判断时间为两秒
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP ){
//            获取当前点击返回键的时间   --   两个时间
            long currentTime = System.currentTimeMillis();
            if(currentTime-fristKeyBackTime>2000){
//                表示不能退出
                fristKeyBackTime = currentTime;
                Toast.makeText(this, "在点击一次返回键，退出当前应用", Toast.LENGTH_SHORT).show();
            }else{
//                成立   应用关闭掉
                AppManager.getInstance().appExit();
            }
        }
        return super.onKeyUp(keyCode, event);
    }

}
