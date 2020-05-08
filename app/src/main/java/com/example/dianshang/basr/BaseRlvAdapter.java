package com.example.dianshang.basr;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dianshang.R;
import com.example.dianshang.topic.RlvTocicadapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRlvAdapter<T> extends RecyclerView.Adapter<BaseRlvAdapter.BaseViewHolder>    {
    public   Context context;
    public ArrayList<T>mlist;
    private OnItemClickListener mOnItemClickListener;

    public BaseRlvAdapter(Context context, ArrayList<T> mlist) {
        this.context = context;
        this.mlist = mlist;
    }


    @NonNull
    @Override
    public BaseRlvAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View inflate = LayoutInflater.from(context).inflate(getLayout(), parent, false);
        final BaseViewHolder holder = new BaseViewHolder(inflate);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.onItemClick(inflate,holder.getLayoutPosition());
                }
            }
        });

        return holder;
    }

    protected abstract int getLayout();

    @Override
    public void onBindViewHolder(@NonNull BaseRlvAdapter.BaseViewHolder holder, int position) {
        T t = mlist.get(position);
        bindData(holder,t);

    }

    protected abstract void bindData(BaseViewHolder holder, T t);
    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void addData(List<T> list) {
        if (list != null && list.size() > 0) {
            mlist.addAll(list);
            notifyDataSetChanged();
        }
    }

    //刷新数据,下拉刷新使用
    public void updateData(List<T> list){
        if (list != null && list.size()>0){
            mlist.clear();
            mlist.addAll(list);
            notifyDataSetChanged();
        }
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder{
        SparseArray<View> mViews = new SparseArray();


        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public  View findView(@IdRes int id){
            View view = mViews.get(id);
            if (view==null){
                view = itemView.findViewById(id);
                mViews.append(id,view);
            }
            return  view;
        }
        public  void  setText(@IdRes int id,String text){
            try {
                TextView view = (TextView) findView(id);
                view.setText(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }
}
