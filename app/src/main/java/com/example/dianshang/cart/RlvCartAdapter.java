package com.example.dianshang.cart;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dianshang.R;
import com.example.dianshang.basr.BaseApp;
import com.example.dianshang.bean.AddCart;
import com.example.dianshang.bean.CartListBean;
import com.example.dianshang.bean.CheckEvent;
import com.example.dianshang.bean.UpdateGoodsEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class RlvCartAdapter extends
        BaseQuickAdapter<CartListBean, BaseViewHolder> {
    String rmb = BaseApp.getRes().getString(R.string.rmb);
    private int mCurrentState;
    public boolean mIsModifyNumber = false;//是否修改过商品数量

    public RlvCartAdapter(int layoutResId, @Nullable List<CartListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CartListBean item) {
        TextView tvCount = helper.getView(R.id.tv_count);
        final TextView tvNumber = helper.getView(R.id.tv_number);
        TextView tvMinus = helper.getView(R.id.tv_minus);
        TextView tvPlus = helper.getView(R.id.tv_plus);
        LinearLayout llConstainer = helper.getView(R.id.ll_container);

        helper.setText(R.id.tv_goods_price,rmb+item.getRetail_price());
        helper.setText(R.id.tv_goods_name,item.getGoods_name());
        helper.setText(R.id.tv_count,"x"+item.getNumber());
        CheckBox cb = helper.getView(R.id.cb);
        Glide.with(mContext).load(item.getList_pic_url()).into((ImageView) helper.getView(R.id.iv_goods));


      /*  int checked = item.getChecked();
        if (checked == 1){
            //选中
            cb.setChecked(true);
        }else {
            cb.setChecked(false);
        }*/

        if (mCurrentState == CartFragment.TYPE_ORDER){
            //下单状态
            int checked = item.getChecked();
            if (checked == 1) {
                //选中
                cb.setChecked(true);
            } else {
                cb.setChecked(false);
            }
            //数量
            tvCount.setVisibility(View.VISIBLE);
            //加减商品数量
            llConstainer.setVisibility(View.GONE);
        }else {
            //编辑状态
            boolean editChecked = item.getEditChecked();
            cb.setChecked(editChecked);
            //数量
            tvCount.setVisibility(View.GONE);
            //加减商品数量
            llConstainer.setVisibility(View.VISIBLE);

            tvNumber.setText(item.getNumber()+"");
        }
        //根据用户的交互,需要修改数据
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //控件被按压,用户交互的时候才需要该数据
                if (buttonView.isPressed()){
                    //三元运算符
                    //boolean 是true,取值1,false 取值2
                    // boolean  ?  值1  :  值2
                    /*if (boolean){
                        值1
                    }else{
                        值2
                    }*/

                  /*  int ck = isChecked ? 1 : 0;
                    item.setChecked(ck);

                    //用户修改状态后需要通知framgnet修改选中商品的数量+总价,确定全选的状态
                    EventBus.getDefault().post(new CheckEvent());*/

                    //三元运算符
                    //boolean 是true,取值1,false 取值2
                    // boolean  ?  值1  :  值2
                    /*if (boolean){
                        值1
                    }else{
                        值2
                    }*/

                    if (mCurrentState == CartFragment.TYPE_ORDER){
                        //下单
                        int ck = isChecked ? 1 : 0;
                        item.setChecked(ck);
                    }else {
                        //编辑
                        item.setEditChecked(isChecked);
                    }

                    //用户修改状态后需要通知framgnet修改选中商品的数量+总价,确定全选的状态
                    EventBus.getDefault().post(new CheckEvent());
                }
            }
        });

        tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = item.getNumber();
                if (number > 1){
                    tvNumber.setText(number-1+"");
                    item.setNumber(number-1);


                    //将数据同步到服务器
                    //为了避免频繁的调用修改服务器商品数量的接口,我们统一在点击完成的时候去
                    //调用接口
                    //但是我们的接口只支持1个1个商品的改,所以只能在这里去调用接口了
                    mIsModifyNumber = true;

                    EventBus.getDefault().post(new UpdateGoodsEvent(item.getProduct_id(),
                            item.getGoods_id(),item.getNumber(),item.getId()));
                }
            }
        });


        tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果有库存的话,数量不能超过库存上限
                item.setNumber(item.getNumber()+1);
                tvNumber.setText(item.getNumber()+"");
                //将数据同步到服务器
                //为了避免频繁的调用修改服务器商品数量的接口,我们统一在点击完成的时候去
                //调用接口
                mIsModifyNumber = true;
                EventBus.getDefault().post(new UpdateGoodsEvent(item.getProduct_id(),
                        item.getGoods_id(),item.getNumber(),item.getId()));

            }
        });

    }
    public void setState(int currentState) {
        this.mCurrentState = currentState;
        notifyDataSetChanged();
    }
}
