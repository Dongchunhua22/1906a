package com.example.dianshang.cart;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dianshang.R;
import com.example.dianshang.basr.BaseApp;
import com.example.dianshang.basr.BaseFragment;
import com.example.dianshang.basr.BasePresenter;
import com.example.dianshang.bean.AddCart;
import com.example.dianshang.bean.CartListBean;
import com.example.dianshang.bean.CartListBeanDao;
import com.example.dianshang.bean.CheckEvent;
import com.example.dianshang.bean.SortTabBean;
import com.example.dianshang.bean.UpdateGoodsEvent;
import com.example.dianshang.presenter.CartPresenter;
import com.example.dianshang.ui.CartView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CartFragment extends BaseFragment<CartPresenter> implements CartView {

    //1.界面是共用的，所以需要有编辑和下单状态的表示
    public static final int TYPE_ORDER = 0;//下单状态
    public static final int TYPE_EDIT = 1;//编辑状态

    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.cb_choose)
    CheckBox mCb;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_edit)
    TextView mTvEdit;
    @BindView(R.id.tv_order)
    TextView mTvOrder;
    private RlvCartAdapter mAdapter;
    String mStrAll = BaseApp.getRes().getString(R.string.choose_all);
    String rmb = BaseApp.getRes().getString(R.string.rmb);
    private int mCheckedGoodsCount;
    private CartListBeanDao mCartListBeanDao;
    public int mCurrentState = TYPE_ORDER;
    public  static  CartFragment newInstance(){
        CartFragment fragment = new CartFragment();
        return  fragment;
    }
    @Override
    protected void initData() {
        mPresenter.getCartData();

    }

    @Override
    protected void initView(View view) {
        mCartListBeanDao = BaseApp.sContext.getDaoSession().getCartListBeanDao();
        List<CartListBean> dbList = mCartListBeanDao.queryBuilder().list();
        if (dbList != null && dbList.size()>0){
            ArrayList<Integer> ids = new ArrayList<>();
            for (int i = 0; i < dbList.size(); i++) {
                boolean isServerDelete = dbList.get(i).getIsServerDelete();
                if (isServerDelete){
                    ids.add(dbList.get(i).getProduct_id());
                }
            }

            if (ids.size()>0){
                mPresenter.deleteGoods(ids);
            }
        }


        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        ArrayList<CartListBean> list = new ArrayList<>();
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RlvCartAdapter(R.layout.item_cart, list);
        mAdapter.bindToRecyclerView(mRlv);

        mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //用户行为,而不是代码设置导致的监听回调
                if (buttonView.isPressed()) {
                    List<CartListBean> data = mAdapter.getData();
                    /*if (isChecked){
                        //列表所有商品全部选中
                        for (int i = 0; i < data.size(); i++) {
                            data.get(i).setChecked(1);
                        }
                    }else {
                        //列表商品全部不选中
                       for (int i = 0; i < data.size(); i++) {
                            data.get(i).setChecked(0);
                        }
                    }*/
                    //int ck = isChecked ? 1 : 0;

                   // for (int i = 0; i < data.size(); i++) {
                       // data.get(i).setChecked(ck);
                  //  }
                  //  mAdapter.notifyDataSetChanged();

                    if (mCurrentState == TYPE_ORDER) {
                        //下单状态
                        int ck = isChecked ? 1 : 0;
                        for (int i = 0; i < data.size(); i++) {
                            data.get(i).setChecked(ck);
                        }
                    } else {
                        //编辑状态
                        for (int i = 0; i < data.size(); i++) {
                            data.get(i).setEditChecked(isChecked);
                        }
                    }

                    mAdapter.notifyDataSetChanged();

                    //还需要修改总价和选中的商品数量
                    int count = 0;
                    float totalPrice = 0;
                    if (isChecked){
                        for (int i = 0; i < data.size(); i++) {
                            count += data.get(i).getNumber();
                            totalPrice +=data.get(i).getNumber()* data.get(i).getRetail_price();
                        }
                    }
                    setChooseGoodsNum(count);
                    setTotalPrice(totalPrice);
                }
            }
        });


    }

    @Override
    protected CartPresenter initPresener() {
        return new CartPresenter();
    }

    @Override
    protected int getlayout() {
        return R.layout.fragment_cart ;
    }


    @Override
    public void setCartData(AddCart addCart) {
        List<CartListBean> cartList = addCart.getData().getCartList();
        mAdapter.addData(cartList);


        AddCart.DataBean.CartTotalBean cartTotal = addCart.getData().getCartTotal();
        int checkedGoodsCount = cartTotal.getCheckedGoodsCount();
        setChooseGoodsNum(checkedGoodsCount);

        //金额
        float text =  cartTotal.getCheckedGoodsAmount();
        setTotalPrice( text);

    }

    private void setTotalPrice(float price) {
        mTvPrice.setText(rmb +price);
    }

    //设置选中商品数量的
    private void setChooseGoodsNum(int checkedGoodsCount) {
        if (checkedGoodsCount > 0) {
            mCb.setText(mStrAll + "(" + checkedGoodsCount + ")");
        }else {
            mCb.setText(mStrAll);
        }
        this.mCheckedGoodsCount=checkedGoodsCount;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void receiveCheckEvent(CheckEvent event){
        //修改选中商品的数量+总价
        //数量
        List<CartListBean> data = mAdapter.getData();
        int count = 0;
        float totalPrice = 0;
        //确定全选的状态
        boolean isChecked = true;
        for (int i = 0; i < data.size(); i++) {
         /*   AddCart.DataBean.CartListBean cartListBean = data.get(i);
            if (cartListBean.getChecked() == 1){
                //计算数量
                count += cartListBean.getNumber();
                totalPrice += cartListBean.getNumber() * cartListBean.getRetail_price();
            }else {
                //子条目只要有一个商品未选中，全选不选中
                isChecked = false;
            }*/





           CartListBean cartListBean = data.get(i);

            if (mCurrentState == TYPE_ORDER) {
                //下单状态
                if (cartListBean.getChecked() == 1) {
                    //计算数量
                    count += cartListBean.getNumber();
                    totalPrice += cartListBean.getNumber() * cartListBean.getRetail_price();
                } else {
                    //子条目只要有一个商品未选中，全选不选中
                    isChecked = false;
                }
            } else {
                //编辑状态
                if (cartListBean.getEditChecked()) {
                    //计算数量
                    count += cartListBean.getNumber();
                } else {
                    //子条目只要有一个商品未选中，全选不选中
                    isChecked = false;
                }
            }
        }
        setChooseGoodsNum(count);
        setTotalPrice(totalPrice);
        //设置全选状态
        mCb.setChecked(isChecked);
    }

    @OnClick({R.id.tv_edit, R.id.tv_order})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_edit:
                chageState();
                break;
            case R.id.tv_order:
                orderOrDelete();

                break;
        }
    }

    private void orderOrDelete() {
        if (mCurrentState==TYPE_ORDER){
            if (mCheckedGoodsCount>0){
                go2Order();
            }else {
                showToast("请至少选中");

            }
        }else {
            if (mCheckedGoodsCount<=0){
                showToast("请至少选中");
                return;
            }
            List<CartListBean> data = mAdapter.getData();
            ArrayList<Integer> ids = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                CartListBean cartListBean = data.get(i);
                boolean editChecked = cartListBean.getEditChecked();
                if (editChecked){
                    ids.add(cartListBean.getProduct_id());
                    cartListBean.setIsServerDelete(true);


                            data.remove(i);
                    i--;



                }

            }
            mAdapter.notifyDataSetChanged();
            mPresenter.deleteGoods(ids);

        }


    }

    private void go2Order() {
        showToast("跳转到下单页面");

    }


    //2.修改编辑或者下单状态
    private void chageState() {
        //修改状态
        if (mCurrentState == TYPE_ORDER) {
            mCurrentState = TYPE_EDIT;
        } else {
            mCurrentState = TYPE_ORDER;
        }

        chageUI();
    }


    private void chageUI() {
        ////修改fragment界面
        if (mCurrentState == TYPE_ORDER) {
            //总价
            mTvPrice.setVisibility(View.VISIBLE);
            mTvEdit.setText("编辑");
            mTvOrder.setText("下单");
            //修改全选数量,价格
            receiveCheckEvent(new CheckEvent());
        } else {
            mTvPrice.setVisibility(View.GONE);
            mTvEdit.setText("完成");
            mTvOrder.setText("删除所选");

            ///将所有控制编辑状态下复选框选中的数据改为false,要求每次进入编辑状态复选框都不选中
            List<CartListBean> data = mAdapter.getData();
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setEditChecked(false);
            }
            //修改全选数量
            setChooseGoodsNum(0);
            mCb.setChecked(false);


        }
        //刷新适配界面
        mAdapter.setState(mCurrentState);
    }
    @Subscribe
    public void receiveModifyGoodsNum(UpdateGoodsEvent event){
        mPresenter.updateNumber(event.productId,event.goodsId,event.number,event.id);
    }


}
