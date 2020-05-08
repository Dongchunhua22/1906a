package com.example.dianshang.goodsdetail;

import android.content.Context;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dianshang.R;
import com.example.dianshang.basr.BaseApp;
import com.example.dianshang.bean.GoodsDetail;
import com.example.dianshang.bean.RelateGoods;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class GoodDetailAdapter extends BaseMultiItemQuickAdapter<GoodsDetail.GoodsDetailItemBean, BaseViewHolder> {
    String rmb = BaseApp.getRes().getString(R.string.rmb);
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public GoodDetailAdapter(List<GoodsDetail.GoodsDetailItemBean> data) {
        super(data);
        addItemType(GoodsDetail.GoodsDetailItemBean.TYPE_BANNER, R.layout.item_goods_banner);
        addItemType(GoodsDetail.GoodsDetailItemBean.TYPE_HTML, R.layout.item_goods_html);
        addItemType(GoodsDetail.GoodsDetailItemBean.TYPE_TITLE, R.layout.item_goods_title);
        addItemType(GoodsDetail.GoodsDetailItemBean.TYPE_ISSUE, R.layout.item_goods_issue);
        addItemType(GoodsDetail.GoodsDetailItemBean.TYPE_GOODS_LIST, R.layout.item_goods_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsDetail.GoodsDetailItemBean item) {
        int viewType = helper.getItemViewType();
        switch (viewType){
            case GoodsDetail.GoodsDetailItemBean.TYPE_BANNER:
                initBanner(helper,item);
                break;
            case GoodsDetail.GoodsDetailItemBean.TYPE_HTML:
                initHtml(helper,item);
                break;
            case GoodsDetail.GoodsDetailItemBean.TYPE_TITLE:
                initTitle(helper,item);
                break;
            case GoodsDetail.GoodsDetailItemBean.TYPE_ISSUE:
                initIssue(helper,item);
                break;
            case GoodsDetail.GoodsDetailItemBean.TYPE_GOODS_LIST:
                initGoodsList(helper,item);
                break;
        }

    }
//处理商品列表
    private void initGoodsList(BaseViewHolder helper, GoodsDetail.GoodsDetailItemBean item) {
        RelateGoods.DataBean.GoodsListBean bean = (RelateGoods.DataBean.GoodsListBean) item.data;
        helper.setText(R.id.tv_title,bean.getName());
        helper.setText(R.id.tv_price,rmb+bean.getRetail_price());
        Glide.with(mContext).load(bean.getList_pic_url()).into((ImageView) helper.getView(R.id.iv));
    }
//处理问题
    private void initIssue(BaseViewHolder helper, GoodsDetail.GoodsDetailItemBean item) {
        GoodsDetail.DataBeanX.IssueBean bean = (GoodsDetail.DataBeanX.IssueBean) item.data;
        helper.setText(R.id.tv_question,bean.getQuestion());
        helper.setText(R.id.tv_answer,bean.getAnswer());
    }

    //处理标题
    private void initTitle(BaseViewHolder helper, GoodsDetail.GoodsDetailItemBean item) {
        String title = (String) item.data;
        helper.setText(R.id.tv_title,title);
    }

    //处理html
    private void initHtml(BaseViewHolder helper, GoodsDetail.GoodsDetailItemBean item) {
        String html = (String) item.data;
        WebView view = helper.getView(R.id.webview);
        //html:css(装饰),html页面(布局),js代码(java代码,业务逻辑)
        //css的样式
        String css_str = mContext.getResources().getString(R.string.css_goods);
        StringBuilder sb = new StringBuilder();
        //html格式:html标签,head,style,body
        //对html的片段进行拼接,拼接成完整的html,并且添加了样式
        sb.append("<html><head>");
        sb.append("<style>"+css_str+"</style></head><body>");
        sb.append(html+"</body></html>");
        view.loadData(sb.toString(),"text/html","utf-8");
    }

    //处理顶部banner+标题+价格
    private void initBanner(BaseViewHolder helper, GoodsDetail.GoodsDetailItemBean item) {
        GoodsDetail.DataBeanX data = (GoodsDetail.DataBeanX) item.data;
        helper.setText(R.id.tv_title,data.getInfo().getName());
        helper.setText(R.id.tv_price,rmb+data.getInfo().getRetail_price());
        List<GoodsDetail.DataBeanX.GalleryBean> gallery = data.getGallery();
        Banner banner = helper.getView(R.id.banner);
        banner.setImages(gallery)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        GoodsDetail.DataBeanX.GalleryBean bean = (GoodsDetail.DataBeanX.GalleryBean) path;
                        Glide.with(mContext).load(bean.getImg_url()).into(imageView);
                    }
                })
                .start();
    }
}
