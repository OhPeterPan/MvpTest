package com.wak.retrofit.app;

import android.app.Application;

import com.wak.retrofit.exception.MyUncaughtException;
import com.wak.widget_lib.utils.XUI;


public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
       // MyUncaughtException.getInstance().init(this);
    }
}
