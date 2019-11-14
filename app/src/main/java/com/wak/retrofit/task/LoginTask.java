package com.wak.retrofit.task;

import android.os.Environment;

import com.wak.retrofit.encode.AESUtils;
import com.wak.retrofit.presenter.MainPresenter;
import com.wak.retrofit.retrofit.RetrofitCreate;

import java.io.File;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class LoginTask implements ILoginTask {

    @Override
    public void execute(Observer observer) {

        RetrofitCreate.loginCreate()
                .phoneLogin(AESUtils.getInstance().encrypt("13071133862"), "123456", "android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    @Override
    public void vExecute(Observer observer) {
        RetrofitCreate.loginCreate()
                .vLogin(AESUtils.getInstance().encrypt("13071133862"), "123456", "android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void downloadApkTask(Observer observer) {
        RetrofitCreate.downloadApkCreate()
                .getApk("http://www.chadaodian.com/apk/364.apk")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void uploadFile(RequestBody body, Observer observer) {
        RetrofitCreate.uploadApkCreate()
                .uploadAvatar(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
