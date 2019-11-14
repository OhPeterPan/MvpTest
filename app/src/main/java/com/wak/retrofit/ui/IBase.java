package com.wak.retrofit.ui;

public interface IBase {
    int getLayoutId();

    void initPresenter();

    void showLoading();

    void onEnd();

    void onFail(Throwable throwable);
}
