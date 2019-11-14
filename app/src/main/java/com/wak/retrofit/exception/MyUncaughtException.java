package com.wak.retrofit.exception;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

import androidx.annotation.NonNull;

import com.wak.retrofit.MainActivity;

public class MyUncaughtException implements Thread.UncaughtExceptionHandler {

    private static Thread.UncaughtExceptionHandler mDefaultException;

    private static MyUncaughtException INSTANCE;
    private Application app;

    private MyUncaughtException() {

    }

    public static MyUncaughtException getInstance() {

        if (INSTANCE == null) {
            synchronized (MyUncaughtException.class) {
                if (INSTANCE == null)
                    INSTANCE = new MyUncaughtException();
            }
        }
        return INSTANCE;
    }


    public void init(Application app) {
        this.app = app;
        mDefaultException = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable e) {

        if (check(e) && mDefaultException != null) {
            mDefaultException.uncaughtException(thread, e);
        } else {
            AlarmManager manager = (AlarmManager) app.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(app, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent
                    .getActivity(app.getApplicationContext(), 0, intent,
                            PendingIntent.FLAG_ONE_SHOT);

            manager.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, pendingIntent);

            Process.killProcess(Process.myPid());
            //status为0时为正常退出程序  为1的时候表示非正常退出
            System.exit(1);
        }
    }

    private boolean check(Throwable e) {
        if (e == null) return true;
        return false;
    }
}
