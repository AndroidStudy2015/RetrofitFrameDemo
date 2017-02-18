package com.doc.retrofitframedemo.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by a on 2017/2/14.
 */

public class MyApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
         context = getApplicationContext();
    }
}
