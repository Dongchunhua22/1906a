package com.example.dianshang.sort;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dianshang.R;
import com.example.dianshang.basr.BaseApp;
import com.example.dianshang.bean.GoodsList;

import java.util.List;

public class RlvMultiGoodsListAdapter extends BaseMultiItemQuickAdapter<GoodsList.GoodsListItemBean, BaseViewHolder> {
    String rmb = BaseApp.getRes().getString(R.string.rmb);

    public RlvMultiGoodsListAdapter(List<GoodsList.GoodsListItemBean> data) {
        super(data);
        //// 绑定 layout 对应的 type
        addItemType(GoodsList.GoodsListItemBean.TYPE_ONE, R.layout.item_goods_list);
        addItemType(GoodsList.GoodsListItemBean.TYPE_TWO, R.layout.item_goods_list2);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsList.GoodsListItemBean item) {
        switch (helper.getItemViewType()) {
            case GoodsList.GoodsListItemBean.TYPE_ONE:
                initOne(helper,item);
                break;
            case GoodsList.GoodsListItemBean.TYPE_TWO:
                initTwo(helper,item);
                break;
            default:
                break;
        }

    }
    private void initTwo(BaseViewHolder helper, GoodsList.GoodsListItemBean item) {
        GoodsList.DataBeanX.GoodsListBean bean = (GoodsList.DataBeanX.GoodsListBean) item.data;
        helper.setText(R.id.tv_title,bean.getName());
        helper.setText(R.id.tv_price,rmb+bean.getRetail_price());
        Glide.with(mContext).load(bean.getList_pic_url()).into((ImageView) helper.getView(R.id.iv));

    }

    private void initOne(BaseViewHolder helper, GoodsList.GoodsListItemBean item) {
        GoodsList.DataBeanX.GoodsListBean bean = (GoodsList.DataBeanX.GoodsListBean) item.data;
        helper.setText(R.id.tv_title,bean.getName());
        helper.setText(R.id.tv_price,rmb+bean.getRetail_price());
        Glide.with(mContext).load(bean.getList_pic_url()).into((ImageView) helper.getView(R.id.iv));
    }
}
