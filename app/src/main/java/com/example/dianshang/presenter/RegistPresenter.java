package com.example.dianshang.presenter;

import com.example.dianshang.basr.BasePresenter;
import com.example.dianshang.bean.RegisterBean;
import com.example.dianshang.model.RegistModel;
import com.example.dianshang.net.ResutCallback;
import com.example.dianshang.ui.RegistView;

public class RegistPresenter  extends BasePresenter<RegistView> {
    private RegistModel registModel;
    public void getData(String name, String pass) {
        registModel.getData(name,pass, new ResutCallback<RegisterBean>() {
            @Override
            public void onsuccess(RegisterBean registerBean) {
                int errno = registerBean.getErrno();
                if(errno==0){
                    mView.showToast("注册成功");
                }else {
                    mView.showToast(registerBean.getErrmsg());
                }
            }

            @Override
            public void onfail(String msg) {

            }
        });

    }

    @Override
    protected void initModel() {
        registModel = new RegistModel();
        addmodel(registModel);
    }
}
