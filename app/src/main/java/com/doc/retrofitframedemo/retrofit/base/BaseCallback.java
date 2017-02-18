package com.doc.retrofitframedemo.retrofit.base;

import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by a on 2017/2/14.
 */
public abstract class BaseCallback<T> implements Callback<BaseCallModel<T>> {
    @Override
    public void onResponse(Call<BaseCallModel<T>> call, Response<BaseCallModel<T>> response) {
        //error_code说明：https://www.juhe.cn/code
        int error_code = response.body().error_code;
        String reason = response.body().reason;
        Log.e("tag", error_code + "" + reason);
        if (response.raw().code() == 200) {
            switch (error_code) {
                case 0:
                    onSucess(call, response);
                    break;

                default:
                    onFail(reason);
                    break;
            }

        } else {
            onFail(reason);
        }
    }


    @Override
    public void onFailure(Call<BaseCallModel<T>> call, Throwable t) {
        if (t instanceof SocketTimeoutException) {
            //
            Log.e("tag", "网络超时");
        } else if (t instanceof ConnectException) {
            //
            Log.e("tag", "网络连接异常");

        } else if (t instanceof RuntimeException) {
            //
            Log.e("tag", "运行时异常");

        }
        onFail(t.getMessage());
    }


    public abstract void onSucess(Call<BaseCallModel<T>> call, Response<BaseCallModel<T>> response);

    protected abstract void onFail(String message);
}
