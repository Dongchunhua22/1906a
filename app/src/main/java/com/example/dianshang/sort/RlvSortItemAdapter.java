package com.example.dianshang.sort;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dianshang.R;
import com.example.dianshang.bean.SortItemBean;

import java.util.List;

public class RlvSortItemAdapter  extends BaseQuickAdapter<SortItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean, BaseViewHolder> {

    public RlvSortItemAdapter(int layoutResId, @Nullable List<SortItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, SortItemBean.DataBean.CurrentCategoryBean.SubCategoryListBean item) {
        ImageView iv = helper.getView(R.id.iv);
        Glide.with(mContext).load(item.getWap_banner_url()).into(iv);
        helper.setText(R.id.tv,item.getName());

    }


}
