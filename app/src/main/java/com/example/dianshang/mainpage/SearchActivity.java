package com.example.dianshang.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dianshang.R;
import com.example.dianshang.basr.BaseActivity;
import com.example.dianshang.basr.BaseApp;
import com.example.dianshang.bean.HistoryBean;
import com.example.dianshang.bean.HistoryBeanDao;
import com.example.dianshang.net.LogUtil;
import com.example.dianshang.presenter.SearchPresenter;
import com.example.dianshang.ui.SearchView;
import com.google.android.material.internal.FlowLayout;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchPresenter>implements SearchView {
    @BindView(R.id.et)
    EditText mEt;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.tv_history_title)
    TextView mTvHistoryTitle;
    @BindView(R.id.iv_delete)
    ImageView mIvDelete;
    @BindView(R.id.fl_history)
    FlowLayout mFlHistory;
    @BindView(R.id.tv_hot_title)
    TextView mTvHotTitle;
    @BindView(R.id.fl_hot)
    FlowLayout mFlHot;
    @BindView(R.id.cl_record)
    ConstraintLayout mClRecord;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.cl_result)
    ConstraintLayout mClResult;
    private HistoryBeanDao mHistoryBeanDao;

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mHistoryBeanDao = BaseApp.sContext.getDaoSession().getHistoryBeanDao();

        //一进入搜索界面，将之前的历史搜索记录读取出来并显示
        getHistoryFromDb();

        initListener();

    }
    private void getHistoryFromDb() {
        List<HistoryBean> list = mHistoryBeanDao.queryBuilder().list();
        if (list != null && list.size() > 0) {
            //根据搜索的时间排序
            Collections.sort(list, new Comparator<HistoryBean>() {
                @Override
                public int compare(HistoryBean o1, HistoryBean o2) {
                    //升序:时间小的在前面,返回>0的数据
                    //降序:返回小于0的数据
                    //按照时间顺序 降序(时间大的在前面,时间小的在后面)
                    //返回值是0,代表2个对象要比较的东西一样
                    //+1:
                    //-1:
                    //对任意类型集合对象进行整体排序，排序时将此接口的实现传递给Collections.sort方法或者Arrays.sort方法排序.
                    //实现int compare(T o1, T o2);方法，返回正数，零，负数各代表大于，等于，小于。
                    return (int) (o2.time - o1.time);
                }
            });
            //界面上展示
            mFlHistory.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
                TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.item_label, null, false);
                tv.setText(list.get(i).keyword);
                mFlHistory.addView(tv);
            }
        }
    }

    private void initListener() {
        //edittext 文本改变监听
        mEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //文本改变之前
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //文本发生改变
                LogUtil.print("文本:" + s);
                //如果文本内容为空，显示搜索历史，如果不为空，显示搜索结果
                if (TextUtils.isEmpty(s)) {
                    mClRecord.setVisibility(View.VISIBLE);
                    mClResult.setVisibility(View.GONE);
                } else {
                    mClRecord.setVisibility(View.GONE);
                    mClResult.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //文本改变之后
            }
        });

        //搜索按钮的点击监听
        mEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //根据输入内容搜索
                    search();
                }
                return false;
            }
        });
    }

    private void search() {
        String content = mEt.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            showToast("搜索内容不能为空");
            return;
        }
        //https://cdwan.cn/api/goods/list?keyword=母亲&page=1&size=10&sort=default&order=desc&categoryId=0
        //keyword:关键字
        //page:页码,1开始
        //size:每页数量
        //sort:分类,每页传default
        //order:排序 desc降序 ,asce升序
        //categoryid:分类id,没有传0
       // mpresenter.search(content);

        //不仅仅去服务器搜索东西,还需要将本条记录添加到数据库中
        //实体类需要有哪些信息
        //keyword:搜索的内容,它是作为唯一标识
        //最近一次搜索这个内容的时间,毫秒值 long
        HistoryBean historyBean = new HistoryBean(content, System.currentTimeMillis());
        mHistoryBeanDao.insertOrReplace(historyBean);

        getHistoryFromDb();
    }


    @OnClick({R.id.tv_cancel, R.id.iv_delete})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_cancel:
                break;
            case R.id.iv_delete:
                break;
        }
    }


}
