package com.example.dianshang.goodsdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.dianshang.R;
import com.example.dianshang.basr.BaseActivity;
import com.example.dianshang.basr.BaseApp;
import com.example.dianshang.basr.Constants;
import com.example.dianshang.bean.AddCart;
import com.example.dianshang.bean.GoodsDetail;
import com.example.dianshang.bean.RelateGoods;
import com.example.dianshang.logn.LoginActivity;
import com.example.dianshang.presenter.GoodsDetailPresenter;
import com.example.dianshang.ui.GoodsDetailView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

public class GoodsDetailActivity    extends BaseActivity<GoodsDetailPresenter>
        implements GoodsDetailView {
    private int mId;
    private GoodDetailAdapter mAdapter;
    private GoodsDetail mGoodsDetail;

    public static void startAct(Context context, int id){
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.putExtra(Constants.ID, id);
        context.startActivity(intent);
    }

    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.iv_like)
    ImageView mIvLike;
    @BindView(R.id.iv_cart)
    ImageView mIvCart;
    @BindView(R.id.tv_add_cart)
    TextView mTvAddCart;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;
    @BindView(R.id.cl_bottom)
    ConstraintLayout mClBottom;


    @Override
    protected int getLayout() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected GoodsDetailPresenter initPresenter() {
        return new GoodsDetailPresenter();
    }

    @Override
    protected void initData() {
        mpresenter.getDail(mId);
        if (BaseApp.isLogin){
            mpresenter.getCartData();
        }


    }

    @Override
    protected void initView() {
        mId = getIntent().getIntExtra(Constants.ID, 0);

        final ArrayList<GoodsDetail.GoodsDetailItemBean> list = new ArrayList<>();
        //使用线性的,下面的关联商品的列表效果不对,使用网格的上面的效果
        //需要使用骚操作:
        //使用网格布局管理器,但是要让上面的是线性效果
        //网格布局管理器,将一行分成了2个格子,一个布局占一个,就是网格效果,如果有个布局它占了1行的两个格子
        //那么就变成了线性的效果
        //怎么让一个布局,占两个格子???
        //mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new GoodDetailAdapter(list);
        mAdapter.bindToRecyclerView(mRlv);
        //怎么让一个布局,占两个格子???
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            //i是索引
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                int itemType = list.get(position).getItemType();
                switch (itemType) {
                    //如果是商品列表,每个条目占用1个格子
                    case GoodsDetail.GoodsDetailItemBean.TYPE_GOODS_LIST:
                        return 1;
                    case GoodsDetail.GoodsDetailItemBean.TYPE_BANNER:
                    case GoodsDetail.GoodsDetailItemBean.TYPE_ISSUE:
                    case GoodsDetail.GoodsDetailItemBean.TYPE_TITLE:
                    case GoodsDetail.GoodsDetailItemBean.TYPE_HTML:
                        return 2;
                }
                //每个条目占用几个格子
                return 0;
            }
        });

    }
    @OnClick({R.id.iv_like, R.id.iv_cart, R.id.tv_add_cart, R.id.tv_buy})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_like:
                break;
            case R.id.iv_cart:
                break;
            case R.id.tv_add_cart:
                addCart();
                break;
            case R.id.tv_buy:
                break;
        }
    }
    private void addCart() {
        //添加购物车之前,判断是否登录,如果没有登录,跳转到登录页面
        if (BaseApp.isLogin) {
            if (mGoodsDetail != null) {
                GoodsDetail.DataBeanX.ProductListBean bean = mGoodsDetail.getData().getProductList().get(0);
                mpresenter.addCart(bean.getGoods_id(), 1, bean.getId());
            }
        } else {
            //未登录
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    @Override
    public void setGoodsDetai(GoodsDetail goodsDetail) {
        this.mGoodsDetail=goodsDetail;
        //		网络回来的数据并没有itemType，需要把数据处理成封装的实体类
        //
        ArrayList<GoodsDetail.GoodsDetailItemBean> list = new ArrayList<>();
        //banner:data,gallery
        GoodsDetail.GoodsDetailItemBean top = new GoodsDetail.GoodsDetailItemBean();
        top.itemType= GoodsDetail.GoodsDetailItemBean.TYPE_BANNER;
        top.data = goodsDetail.getData();
        list.add(top);
        //html
        GoodsDetail.GoodsDetailItemBean html = new GoodsDetail.GoodsDetailItemBean();
        html.itemType = GoodsDetail.GoodsDetailItemBean.TYPE_HTML;
        html.data = goodsDetail.getData().getInfo().getGoods_desc();
        list.add(html);

        //title:常见问题
        GoodsDetail.GoodsDetailItemBean issueTitle = new GoodsDetail.GoodsDetailItemBean();
        issueTitle.data = "-- 常见问题 --";
        issueTitle.itemType = GoodsDetail.GoodsDetailItemBean.TYPE_TITLE;
        list.add(issueTitle);

        //issue:
        for (int i = 0; i < goodsDetail.getData().getIssue().size(); i++) {
            GoodsDetail.GoodsDetailItemBean bean = new GoodsDetail.GoodsDetailItemBean();
            bean.data = goodsDetail.getData().getIssue().get(i);
            bean.itemType = GoodsDetail.GoodsDetailItemBean.TYPE_ISSUE;
            list.add(bean);

        }
        GoodsDetail.GoodsDetailItemBean lookTitle = new GoodsDetail.GoodsDetailItemBean();
        lookTitle.data = "-- 大家都在看 --";
        lookTitle.itemType = GoodsDetail.GoodsDetailItemBean.TYPE_TITLE;
        list.add(lookTitle);

        mAdapter.addData(list);

        mpresenter.getRelateGoods(mId);

    }




    @Override
    public void setRelateGoods(RelateGoods relateGoods) {
        ArrayList<GoodsDetail.GoodsDetailItemBean> list = new ArrayList<>();
        for (int i = 0; i < relateGoods.getData().getGoodsList().size(); i++) {
            GoodsDetail.GoodsDetailItemBean bean = new GoodsDetail.GoodsDetailItemBean();
            bean.data = relateGoods.getData().getGoodsList().get(i);
            bean.itemType = GoodsDetail.GoodsDetailItemBean.TYPE_GOODS_LIST;
            list.add(bean);
        }

        mAdapter.addData(list);

    }

    @Override
    public void setCartData(AddCart addCart) {
        addBadge(addCart.getData().getCartTotal().getGoodsCount());
    }

    @Override
    public void setAddCart(AddCart addCart) {
        showToast("添加成功");
        //添加红色的气泡,里面显示购物车商品数量
        addBadge(addCart.getData().getCartTotal().getGoodsCount());
    }

   /* @Override
    public void setCartData(AddCart addCart) {
        showToast("添加成功");
        //添加红色的气泡,里面显示购物车商品数量
        addBadge(addCart.getData().getCartTotal().getGoodsCount());
    }
*/





    ////添加红色的气泡,里面显示购物车商品数量
    private void addBadge(int goodsCount) {
        new QBadgeView(this).bindTarget(mIvCart).setBadgeNumber(goodsCount);
    }
}
