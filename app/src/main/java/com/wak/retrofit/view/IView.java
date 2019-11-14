package com.wak.retrofit.view;

public interface IView {
    void showLoading();

    void onEnd();

    void onFail(Throwable throwable);
}
