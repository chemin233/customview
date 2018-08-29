package com.example.a00327927;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author chemin
 * @date 2018/8/29 15:30.
 * description：
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
