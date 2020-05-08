package com.example.dianshang.sort;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dianshang.R;
import com.example.dianshang.basr.BaseActivity;
import com.example.dianshang.basr.Constants;
import com.example.dianshang.bean.GoodsList;
import com.example.dianshang.bean.SortItemBean;
import com.example.dianshang.goodsdetail.GoodsDetailActivity;
import com.example.dianshang.presenter.GoodsListPresenter;
import com.example.dianshang.ui.GoodsListView;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GoodsListActivity extends BaseActivity<GoodsListPresenter> implements GoodsListView {
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private int mPage = 1;
    private int mSize = 10;
    private int mCategoryId = 0;

    private int mPosition;
    private ArrayList<SortItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean> mData;
    private RlvMultiGoodsListAdapter mAdapter;
    private TextView mTvTitle;
    private TextView mTvSubTitle;



    @Override
    protected int getLayout() {
        return R.layout.activity_goods_list;
    }

    @Override
    protected GoodsListPresenter initPresenter() {
        return new GoodsListPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mPosition = getIntent().getIntExtra(Constants.POSITION,0);
        mData = (ArrayList<SortItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean>) getIntent().getSerializableExtra(Constants.DATA);
       // position = getIntent().getIntExtra(Constants.POSITION, 0);
        //data = (ArrayList<SortItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean>) getIntent().getSerializableExtra(Constants.DATA);

        mCategoryId = mData.get(mPosition).getId();
        initTab();


        //rlv
        mRlv.setLayoutManager(new GridLayoutManager(this, 2));
        ArrayList<GoodsList.GoodsListItemBean> list = new ArrayList<>();
        mAdapter = new RlvMultiGoodsListAdapter(list);
        mAdapter.bindToRecyclerView(mRlv);

        addHeader();
        //设置头部主副标题
        setTitle();

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                go2Detail(position);
            }
        });


    }

    private void go2Detail(int position) {
       /* Intent intent = new Intent(this, GoodsDetailActivity.class);
       GoodsList.GoodsListItemBean goodsListItemBean = mAdapter.getData().get(position);
        GoodsList.DataBeanX.GoodsListBean bean = (GoodsList.DataBeanX.GoodsListBean) goodsListItemBean.data;
        intent.putExtra(Constants.ID, bean.getId());
        startActivity(intent);*/

        GoodsList.GoodsListItemBean goodsListItemBean = mAdapter.getData().get(position);
        GoodsList.DataBeanX.GoodsListBean bean = (GoodsList.DataBeanX.GoodsListBean) goodsListItemBean.data;
        GoodsDetailActivity.startAct(this,bean.getId());
    }


    private void setTitle() {
        mTvTitle.setText(mData.get(mPosition).getFront_desc());
        mTvSubTitle.setText(mData.get(mPosition).getFront_name());
    }

    private void addHeader() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_goods_list_header, null);
        mTvTitle = inflate.findViewById(R.id.tv_title);
        mTvSubTitle = inflate.findViewById(R.id.tv_subtitle);
        mAdapter.addHeaderView(inflate);
    }
    private void initTab() {
        //tab
        for (int i = 0; i < mData.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mData.get(i).getName()));
        }

        //点击tab切换数据,
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //切换数据的id
                mPage = 1;
                mCategoryId = mData.get(tab.getPosition()).getId();

                //重新请求数据
                mpresenter.getData(mCategoryId, mPage, mSize);
                ///适配器里面的数据需要清空
                mAdapter.getData().clear();
                //点击tab需要切换主副标题
                mPosition = tab.getPosition();
                setTitle();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void setGoodList(GoodsList goodsList) {
        List<GoodsList.DataBeanX.GoodsListBean> list = goodsList.getData().getGoodsList();
        if (list != null && list.size() > 0) {
            //对GoodsListBean进行封装,变成GoodsList.GoodsListItemBean
            ArrayList<GoodsList.GoodsListItemBean> goodsListItemBeans = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                GoodsList.GoodsListItemBean bean = new GoodsList.GoodsListItemBean();
                //界面展示的数据
                GoodsList.DataBeanX.GoodsListBean data = list.get(i);
                bean.data = data;
                //子条目类型
                if (data.getId() % 2 == 0) {
                    bean.itemType = GoodsList.GoodsListItemBean.TYPE_ONE;
                }else {
                    bean.itemType = GoodsList.GoodsListItemBean.TYPE_TWO;
                }
                goodsListItemBeans.add(bean);
            }
            mAdapter.addData(goodsListItemBeans);
        }

    }
}
