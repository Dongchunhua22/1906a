package com.example.dianshang.sort;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dianshang.R;
import com.example.dianshang.basr.BaseFragment;
import com.example.dianshang.basr.Constants;
import com.example.dianshang.bean.SortItemBean;
import com.example.dianshang.bean.SortTabBean;
import com.example.dianshang.presenter.SortItemPresenter;
import com.example.dianshang.presenter.SortPresenter;
import com.example.dianshang.ui.SortItemView;

import java.util.ArrayList;

import butterknife.BindView;

public class SortItemFragment  extends BaseFragment<SortItemPresenter> implements SortItemView {
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private String mTitle;
    private int mId;


    private RlvSortItemAdapter mAdapter;
    private ImageView mIv;


    public static SortItemFragment newInstance(String title,int id){
        SortItemFragment fragment = new SortItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA,title);
        bundle.putInt(Constants.ID,id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected SortItemPresenter initPresener() {
        return new SortItemPresenter();
    }
    @Override
    protected void initData() {
       mPresenter.getData(mId);

    }

    @Override
    protected void initView(View view) {
        mTitle = getArguments().getString(Constants.DATA);
        mId = getArguments().getInt(Constants.ID);

        ArrayList<SortItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean> list = new ArrayList<>();
        mAdapter = new RlvSortItemAdapter(R.layout.item_sort_item, list);
        mRlv.setLayoutManager(new GridLayoutManager(getContext(),3));
        //
        //mRlv.setAdapter(adapter);
        mAdapter.bindToRecyclerView(mRlv);

        addHeader();

      /*  mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                go2GoodsList(position);
            }
        });*/
      mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
          @Override
          public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
              go2GoodsList(position);
          }
      });

    }


    private void go2GoodsList(int position) {
        Intent intent = new Intent(getContext(), GoodsListActivity.class);
      /*  intent.putExtra(Constants.POSITION,position);
        intent.putExtra(Constants.DATA,
                (ArrayList<SortItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean>)mAdapter.getData());*/
      intent.putExtra(Constants.POSITION,position);
      intent.putExtra(Constants.DATA,(ArrayList<SortItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean>)mAdapter.getData());
       startActivity(intent);
    }

    private void addHeader() {

        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_sort_header, null);
        TextView tv = inflate.findViewById(R.id.tv);
        tv.setText("-- "+mTitle+"分类 --");
        mIv = inflate.findViewById(R.id.iv);
        mAdapter.addHeaderView(inflate);
    }






    @Override
    protected int getlayout() {
        return R.layout.fragment_sort_item;
    }


    @Override
    public void setData(SortItemBean sortItemBean) {
        mAdapter.addData(sortItemBean.getData().getCurrentCategory().getSubCategoryList());
        Glide.with(getContext()).load(sortItemBean.getData().getCurrentCategory().getBanner_url()).into(mIv);

    }
}
