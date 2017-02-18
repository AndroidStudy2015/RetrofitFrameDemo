package com.doc.retrofitframedemo.retrofit;

import com.doc.retrofitframedemo.bean.MyHistory;
import com.doc.retrofitframedemo.retrofit.base.BaseCallModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by a on 2017/2/18.
 */

/**
 * key	string	是	应用APPKEY(应用详细页查询)
 * v	string	是	版本，当前：1.0
 * month	int	是	月份，如：10
 * day	int	是	日，如：1
 *
 * @return
 */

public interface APIService {

    /**
     * get请求，一个一个传参数
     */
    @GET("japi/toh")
    Call<BaseCallModel<MyHistory>> getHistory(
            @Query("key") String key,
            @Query("v") String v,
            @Query("month") int month,
            @Query("day") int day
    );

    /**
     * get请求，参数传map
     */
    @GET("japi/toh")
    Call<BaseCallModel<MyHistory>> getMapHistory(@QueryMap Map<String, String> map);

    /**
     * post请求，一个一个传参数
     */

    @FormUrlEncoded
    @POST("japi/toh")
    Call<BaseCallModel<MyHistory>> postHistory(
            @Field("key") String key,
            @Field("v") String v,
            @Field("month") int month,
            @Field("day") int day
    );

    /**
     * post请求，参数传map
     */
    @FormUrlEncoded
    @POST("japi/toh")
    Call<BaseCallModel<MyHistory>> postMapHistory(@FieldMap Map<String, String> map);


}
