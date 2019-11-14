package com.wak.retrofit.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver implements Observer {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Object o) {
        onSuccess(o);
    }

    protected abstract void onSuccess(Object o);

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    protected abstract void onFail(Throwable e);

    @Override
    public void onComplete() {

    }
}
