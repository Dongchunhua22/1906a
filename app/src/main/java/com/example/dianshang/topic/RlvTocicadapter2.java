package com.example.dianshang.topic;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dianshang.R;
import com.example.dianshang.basr.BaseApp;
import com.example.dianshang.basr.BaseRlvAdapter;
import com.example.dianshang.bean.TopicBean;

import java.util.ArrayList;


public class RlvTocicadapter2 extends BaseRlvAdapter<TopicBean.DataBeanX.DataBean>  {
    String yuan = BaseApp.getRes().getString(R.string.yuan);

    public RlvTocicadapter2(Context context, ArrayList list) {
        super(context, list);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_topic;
    }

    @Override
    protected void bindData(BaseViewHolder holder, TopicBean.DataBeanX.DataBean dataBean) {

        holder.setText(R.id.tv_title,dataBean.getTitle());
        holder.setText(R.id.tv_subtitle,dataBean.getSubtitle());
        holder.setText(R.id.tv_price,dataBean.getPrice_info()+yuan);
        ImageView iv = (ImageView) holder.findView(R.id.iv);
        Glide.with(context).load(dataBean.getScene_pic_url()).into(iv);


    }
}
