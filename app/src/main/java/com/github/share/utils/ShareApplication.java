package com.github.share.utils;

import android.app.Application;

/**
 * Created by zlove on 2018/2/1.
 */

public class ShareApplication extends Application {

    private static ShareApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static ShareApplication getInstance() {
        return instance;
    }
}
