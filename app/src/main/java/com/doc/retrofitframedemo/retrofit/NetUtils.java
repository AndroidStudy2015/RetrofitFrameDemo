package com.doc.retrofitframedemo.retrofit;

import android.util.Log;

import com.doc.retrofitframedemo.app.MyApp;
import com.doc.retrofitframedemo.bean.NetUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtils {

    /**
     * ★这里最后面必须能带“/”
     */
    private static final String baseUrl = " http://api.juheapi.com/";
    public static final String key = "767bdb23d5f292fb5df289722239fd62";

    /*
//    拦截器的使用：这里就是在请求执行前，做一些统一的操作，也可以在这里串改请求（添加请求头之类的）
            Interceptor mTokenInterceptor = new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
//                1.得到原始的Request
                    Request originalRequest = chain.request();
//                2. 如果你没有token,就执行原来的Request
                    if (Your.sToken == null) {
                        return chain.proceed(originalRequest);
                    }
//                3. 否则就基于原请求originalRequest建立一个新的Request
//                    并且去添加个请求头，去执行这个newRequest
                    Request newRequest = originalRequest.newBuilder()
                            .header("Authorization", Your.sToken)
                            .build();
                    return chain.proceed(newRequest);
                }
            };
    */
    private static final Interceptor interceptor = new Interceptor() {

        // https://drakeet.me/retrofit-2-0-okhttp-3-0-config   如何配置okhttp
        @Override
        public Response intercept(Chain chain) throws IOException {
            //1. 得到原始的请求
            Request originalRequest = chain.request();
            //2. 这里在请求执行前都统一判断一下当前的网络情况
            if (!NetUtil.hasConnectedNetwork(MyApp.context)) {
                Log.e("tag", "没网络");
            } else {
                Log.e("tag", "有网络");
            }
            return chain.proceed(originalRequest);
        }
    };


    static OkHttpClient client = new OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addNetworkInterceptor(interceptor)//拦截器
            .readTimeout(10000, TimeUnit.MILLISECONDS)
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .writeTimeout(1000, TimeUnit.MILLISECONDS)
            .build();


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    public static APIService service =
            retrofit.create(APIService.class);
}