package com.example.dianshang.sort;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

import com.example.dianshang.R;
import com.example.dianshang.basr.BaseFragment;
import com.example.dianshang.basr.BasePresenter;
import com.example.dianshang.bean.SortTabBean;
import com.example.dianshang.net.LogUtil;
import com.example.dianshang.presenter.CartPresenter;
import com.example.dianshang.presenter.SortPresenter;
import com.example.dianshang.ui.CartView;
import com.example.dianshang.ui.SortView;

import java.util.ArrayList;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.SimpleTabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class SortFragment extends BaseFragment<SortPresenter> implements SortView {
    @BindView(R.id.tablayout)
    VerticalTabLayout mTabLayout;
    @BindView(R.id.vp)
    ViewPager2 mVp;

    private SortTabBean mSortTabBean;

    public  static SortFragment newInstance(){
        SortFragment fragment = new SortFragment();
        return  fragment;
    }

    @Override
    protected SortPresenter initPresener() {
        return new SortPresenter();
    }
    @Override
    protected void initData() {
        mPresenter.getTab();


    }

    @Override
    protected void initView(View view) {
        mVp.setUserInputEnabled(true);
        //关联viewpager和tablayout,但是我们使用的是viewpager2,只能手动关联
        //mTabLayout.setupWithViewPager(mVp);
        //手动关联tab和viewpager2
        //tab 选中监听
        mTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                //tab选中
                //切换viewpager的页面
                mVp.setCurrentItem(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {
                //tab重复选中
            }
        });

        //viewpager2的翻页监听
        mVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                //页面选中
                //tab选中
                LogUtil.print("onPageSelected:"+position);
                mTabLayout.setTabSelected(position);
                //new TabLayout().getTabAt(position).select();
            }
        });

    }



    @Override
    protected int getlayout() {
        return R.layout.fragment_sort ;
    }







    @Override
    public void setTab(SortTabBean sortTabBean) {
        this.mSortTabBean=sortTabBean;
        initTab();
        initVp();
    }

    private void initVp() {
        ArrayList<BaseFragment> list = new ArrayList<>();
        for (int i = 0; i < mSortTabBean.getData().getCategoryList().size(); i++) {
            SortTabBean.DataBean.CategoryListBean categoryListBean = mSortTabBean.getData().getCategoryList().get(i);
            String name = categoryListBean.getName();

            list.add(SortItemFragment.newInstance(name,categoryListBean.getId()));
        }
        VpSortAdapter adapter = new VpSortAdapter(getActivity(),list);
        mVp.setAdapter(adapter);
    }



    private void initTab() {
        mTabLayout.setTabAdapter(new SimpleTabAdapter() {
            @Override
            public int getCount() {
                return mSortTabBean.getData().getCategoryList().size();
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                SortTabBean.DataBean.CategoryListBean categoryListBean = mSortTabBean.getData().getCategoryList().get(position);
                return new ITabView.TabTitle.Builder()
                        .setContent(categoryListBean.getName())
                        .build();
            }
        });
    }

  /*  @Override
    public void setTab(SortTabBean sortTabBean) {
        this.mSortTabBean=sortTabBean;
        initTab();
        initVp();
    }*/
}
