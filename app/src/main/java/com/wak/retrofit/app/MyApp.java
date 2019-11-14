package com.wak.retrofit.app;

import android.app.Application;

import com.wak.retrofit.ui.XUI;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
    }
}
