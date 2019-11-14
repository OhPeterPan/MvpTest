package com.wak.retrofit.http;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UploadFile {

    @POST("index.php?do=shop_image&of=oss")
    Observable<ResponseBody> uploadAvatar(@Body RequestBody body);
}
