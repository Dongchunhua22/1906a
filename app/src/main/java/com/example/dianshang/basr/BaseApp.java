package com.example.dianshang.basr;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.dianshang.bean.DaoMaster;
import com.example.dianshang.bean.DaoSession;
import com.example.dianshang.net.SpUtil;

public class BaseApp extends Application {
    public static BaseApp sContext;
    public static boolean isLogin;
    public static String mToken;

    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static Resources getRes() {
        return sContext.getResources();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

        String token = (String) SpUtil.getParam(Constants.TOKEN, "");
        if (TextUtils.isEmpty(token)) {
            isLogin = false;
        }else {
            isLogin = true;
        }
        setDatabase();
    }

    private void setDatabase() {

        //通过DaoMaster内部类DevOpenHelper可以获取一个SQLiteOpenHelper 对象

        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。

        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。

        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。

        // 此处MyDb表示数据库名称 可以任意填写,需要加尾缀.db

        mHelper = new DaoMaster.DevOpenHelper(this, "MyDb.db", null);

        SQLiteDatabase db = mHelper.getWritableDatabase();

        //Android 9 默认使用了wal模式,需要关闭wal模式
        db.disableWriteAheadLogging();

        mDaoMaster = new DaoMaster(db);

        mDaoSession = mDaoMaster.newSession();

    }
    public DaoSession getDaoSession(){
        return mDaoSession;
    }
}
