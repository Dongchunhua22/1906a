package com.example.dianshang.net;

import com.example.dianshang.bean.AddCart;
import com.example.dianshang.bean.GoodsDetail;
import com.example.dianshang.bean.GoodsList;
import com.example.dianshang.bean.LoginBean;
import com.example.dianshang.bean.RegisterBean;
import com.example.dianshang.bean.RelateGoods;
import com.example.dianshang.bean.SortItemBean;
import com.example.dianshang.bean.SortTabBean;
import com.example.dianshang.bean.TopicBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    String sBaseUrl="https://cdwan.cn/api/";
    @GET("topic/list")
    Observable<TopicBean> getTopic2(@Query("page") int page,
                                    @Query("size") int size);
    //flowable也是rxjava的被观察者,使用起来和Observable一样的,但是它支持背压模式
    @GET("topic/list")
    Flowable<TopicBean> getTopic(@Query("page") int page,
                                 @Query("size") int size);

    @GET("catalog/index")
    Flowable<SortTabBean> getSortTab();

    @GET("catalog/current")
    Flowable<SortItemBean> getSortItem(@Query("id") int id);

    @GET("goods/list")
    Flowable<GoodsList> getGoodList(@Query("categoryId") int categoryId,
                                    @Query("page") int page,
                                    @Query("size") int size);
    @GET("goods/detail")
    Flowable<GoodsDetail> getGoodsDetail(@Query("id") int id);

    @GET("goods/related")
    Flowable<RelateGoods> getRelateGoods(@Query("id") int id);

    @POST("auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("nickname") String nickname,
                              @Field("password") String password);


    @POST("auth/register")
    @FormUrlEncoded
    Flowable<RegisterBean> register(@Field("nickname") String nickname,
                                    @Field("password") String password);

    @POST("cart/add")
    @FormUrlEncoded
    Flowable<AddCart> addCart(@Header("X-Nideshop-Token") String token,
                              @Field("goodsId") int goodsId,
                              @Field("number") int number,
                              @Field("productId") int productId);

    /**
     * 获取购物车数据
     * @param token
     * @return
     */
    @GET("cart/index")
    Flowable<AddCart> getCartData(@Header("X-Nideshop-Token") String token);
    /**
     * 加入购物车
     * @param goodsId
     * @param number
     * @param productId
     * @return
     */
    @POST("cart/add")
    @FormUrlEncoded
    Flowable<AddCart> addCart2(@Field("goodsId") int goodsId,
                               @Field("number") int number,
                               @Field("productId") int productId);

    /**
     * 获取购物车数据
     * @return
     */
    @GET("cart/index")
    Flowable<AddCart> getCartData2();


    @POST("cart/update")
    @FormUrlEncoded
    Flowable<AddCart> updateGoodsNumber(@Field("productId") int productId,
                                        @Field("goodsId") int goodsId,
                                        @Field("number") int number,
                                        @Field("id") Long id);
    @POST("cart/delete")
    @FormUrlEncoded
    Flowable<AddCart> deleteGoods(@Field("productIds") String productIds);

}
