package cn.shimmer.core.core;

import android.app.Application;

public class MyApplication extends Application {

    public Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
