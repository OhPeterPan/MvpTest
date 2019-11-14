package com.wak.retrofit.presenter;

import android.util.Log;

import com.wak.retrofit.task.ITask;
import com.wak.retrofit.view.IView;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<T extends IView, U extends ITask> implements Observer {
    T view;
    U task;
    private int tag;//根据不同的tag判断是哪个请求
    private final CompositeDisposable disposables;
    private LinkedHashMap<String, Disposable> hashMap = new LinkedHashMap<>();

    public BasePresenter(T view, U task) {
        this.view = view;
        this.task = task;
        disposables = new CompositeDisposable();
    }

    public U getTask() {
        return task;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    @Override
    public void onSubscribe(Disposable d) {
        addDisposable(d);
    }

    //这个方法与onError互斥，只能执行一个
    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        //UnknownHostException  SocketException  可以判断是否是io异常来判断网络连接是否失败
        System.out.println(e.getMessage());
    }

    public void cancel() {
        if (!disposables.isDisposed()) {
            disposables.clear();//此方法不仅可以取消订阅还可以取消联网  你上哪说理去
        }
    }

    public void onDestroy() {
        cancel();
    }
}
