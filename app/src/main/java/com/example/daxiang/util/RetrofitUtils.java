package com.example.daxiang.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.daxiang.api.NetApi;
import com.example.daxiang.api.URLConstants;
import com.example.daxiang.app.MyApplication;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitUtils implements  INetWork {
    public static  volatile  RetrofitUtils retrofitUtils;
    private final Retrofit retrofit;
    private final OkHttpClient okHttpClient;
    private final NetApi netApi;
    private RetrofitUtils() {

        File cacheFile = new File(MyApplication.myApplication.getCacheDir(),"cache");
        Cache cache = new Cache(cacheFile,1024*1024*10);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(addNetHeaderInterceptor())
//                .addInterceptor(addCacheInterceptor())
//                .cache(cache)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000,TimeUnit.MILLISECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(URLConstants.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        netApi = retrofit.create(NetApi.class);

    }

    public static RetrofitUtils getInstance() {
        if(retrofitUtils == null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils == null){
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }





//    网络请求


    //应用拦截器、网络拦截器
    private Interceptor addNetHeaderInterceptor(){
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                Request.Builder requestbuilder = request.newBuilder();
//                        .header("Cache-control", "no-cache")
//                        .header("sigan","*********");
                Request build = requestbuilder.build();
                return chain.proceed(build);
            }
        };
        return headerInterceptor;
    }


    private  Interceptor addCacheInterceptor(){

        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if(isNetWorkValid()){
                    request =  request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if(isNetWorkValid()){
//                    有网络的情况下，咱们就不使用缓存
                    response.newBuilder()
                            .header("Cache-Control","public,max-age="+0)
//                            清楚头信息，如果服务器不知粗--看网络请求返回结果
                            .removeHeader("")
                            .build();
                }else {
                    int max_stale = 60*60;
                    response.newBuilder()
                            .header("Cache-Control","public,only-if-cached,max-stale="+max_stale)
                            .removeHeader("")
                            .build();
                }
                return response;
            }
        };

        return cacheInterceptor;
    }



    private  boolean isNetWorkValid(){

        ConnectivityManager manager = (ConnectivityManager)MyApplication.myApplication.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if(null == manager){
            return false;
        }
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(null == info || !info.isAvailable()){
            return false;
        }
        return true;
    }

    @Override
    public <T> void get(String url, final INetCallBack<T> netCallBack) {

        netApi.get(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                       // String body = null;
                        try {
                          String  body = responseBody.string();
                            Log.e("TAG","网络请求GET方法打印："+body);

                            Type[] genericInterfaces = netCallBack.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();

                            Type type =  actualTypeArguments[0];

                            Gson gson = new Gson();
                            T t = gson.fromJson(body, type);
                            netCallBack.onSuccess(t);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

    @Override
    public <T> void get(String url, HashMap<String, String> s, final INetCallBack<T> netCallBack) {

        netApi.get(url,s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {


                        try {
                          String   body = responseBody.string();
                            //LogUtils.e("TAG","网络请求GET方法打印："+body);

                            Type[] genericInterfaces = netCallBack.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();

                            Type type =  actualTypeArguments[0];

                            Gson gson = new Gson();
                            T t = gson.fromJson(body, type);
                            netCallBack.onSuccess(t);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public <T> void post(String url, INetCallBack<T> netCallBack) {

    }

    @Override
    public <T> void post(String url, HashMap<String, String> s, final INetCallBack<T> netCallBack) {

        netApi.post(url,s)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        // String body = null;
                        try {
                            String  body = responseBody.string();
                            Log.e("TAG","网络请求GET方法打印："+body);

                            Type[] genericInterfaces = netCallBack.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();

                            Type type =  actualTypeArguments[0];

                            Gson gson = new Gson();
                            T t = gson.fromJson(body, type);
                            netCallBack.onSuccess(t);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
