package com.example.dianshang.ui;

import com.example.dianshang.BaseView;
import com.example.dianshang.bean.AddCart;
import com.example.dianshang.bean.GoodsDetail;
import com.example.dianshang.bean.RelateGoods;

public interface GoodsDetailView extends BaseView {
    void setGoodsDetai(GoodsDetail goodsDetail);

    void setRelateGoods(RelateGoods relateGoods);

    void setCartData(AddCart addCart);

    void setAddCart(AddCart addCart);
}
