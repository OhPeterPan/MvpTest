package com.wak.retrofit.callback;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

public interface IResultCallback extends ICallback {
    void onSuccess(ResponseBody o);
}
