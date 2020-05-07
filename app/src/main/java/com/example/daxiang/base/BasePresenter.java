package com.example.daxiang.base;
//V：代表V层的具体实现类
public class BasePresenter <T extends  BaseView> {
    //p层获取完数据要把数据传给v层
    protected  T mview;
//1.p层关联v层

    public  void  AttachView(T baseview){
        //把局部变量赋值给全局变量，
        mview=baseview;
    }
    public  void  disAttachView(){
        mview=null;
    }

}
