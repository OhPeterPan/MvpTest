package com.wak.retrofit.view;

import okhttp3.ResponseBody;

public interface IMainView extends IView {
    void result(String json);

    void uploadApk(ResponseBody body);

    void loadPic(String json);
}
