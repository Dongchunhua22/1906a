package com.example.dianshang.bean;

public class UpdateGoodsEvent {
    public int productId;
    public int goodsId;
    public int number;
    public Long id;

    public UpdateGoodsEvent(int productId, int goodsId, int number, Long id) {
        this.productId = productId;
        this.goodsId = goodsId;
        this.number = number;
        this.id = id;
    }
}
