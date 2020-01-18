package com.wak.retrofit.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wak.retrofit.exception.MyUncaughtException;
import com.wak.widget_lib.utils.XUI;


public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XUI.init(this);
        // MyUncaughtException.getInstance().init(this);
        registerActivityLifecycleCallbacks(callbacks);
    }

    ActivityLifecycleCallbacks callbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }
    };
}
