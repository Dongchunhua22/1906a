package com.example.dianshang.topic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dianshang.R;
import com.example.dianshang.bean.TopicBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvTocicadapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<TopicBean.DataBeanX.DataBean> list;

    public RlvTocicadapter(Context context, ArrayList<TopicBean.DataBeanX.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.top, parent, false);

        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH) holder;
        vh.mtv.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getScene_pic_url()).into(vh.miv);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<TopicBean.DataBeanX.DataBean> bean) {
       list.addAll(bean);
       notifyDataSetChanged();
    }

    class VH extends  RecyclerView.ViewHolder{
        @BindView(R.id.tv)
        TextView mtv;
        @BindView(R.id.iv)
        ImageView miv;

        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
