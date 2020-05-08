package com.example.dianshang.topic;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dianshang.R;
import com.example.dianshang.basr.BaseFragment;
import com.example.dianshang.basr.BaseRlvAdapter;
import com.example.dianshang.bean.TopicBean;
import com.example.dianshang.net.ToastUtil;
import com.example.dianshang.presenter.TopicPresenter;
import com.example.dianshang.ui.TopicView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TopicFragment extends BaseFragment<TopicPresenter> implements TopicView {
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    @BindView(R.id.recy)
    RecyclerView recy;
    private  int mPage=1;
    private  int mSize=10;
    private ArrayList<TopicBean.DataBeanX.DataBean> list;
    private RlvTocicadapter2 rlvTocicadapter;
    private int totalPages;

    public  static TopicFragment newInstance(){
        TopicFragment fragment = new TopicFragment();
        return  fragment;
    }

    @Override
    protected TopicPresenter initPresener() {
        return new TopicPresenter();
    }
    @Override
    protected void initData() {
        mPresenter.getTopic(mPage,mSize);

    }

    @Override
    protected void initView(View view) {
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        rlvTocicadapter = new RlvTocicadapter2(getContext(),list);
        recy.setAdapter(rlvTocicadapter);

        rlvTocicadapter.setOnItemClickListener(new BaseRlvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtil.showToastShort("点击position"+position);
            }
        });
       smart.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
           @Override
           public void onLoadmore(RefreshLayout refreshlayout) {
               if (mPage<totalPages){
                   mPage++;
                   mPresenter.getTopic(mPage,mSize);
               }else {
                   ToastUtil.showToastShort("没有更多数据了");
                   smart.finishLoadmore();
               }
           }

           @Override
           public void onRefresh(RefreshLayout refreshlayout) {
            mPage=1;
            rlvTocicadapter.mlist.clear();
            mPresenter.getTopic(mPage,mSize);
           }
       });


    }



    @Override
    protected int getlayout() {
        return R.layout.fragment_topic ;
    }


    @Override
    public void setTopic(TopicBean.DataBeanX bean) {
        rlvTocicadapter.addData(bean.getData());

        smart.finishLoadmore();
        smart.finishRefresh();
        totalPages = bean.getTotalPages();


    }
}
