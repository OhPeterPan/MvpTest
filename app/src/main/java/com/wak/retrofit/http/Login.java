package com.wak.retrofit.http;

import com.wak.retrofit.bean.ComResponse;
import com.wak.retrofit.bean.ErrorModel;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Tag;

public interface Login {
    @FormUrlEncoded
    @POST("index.php?do=shoper_login&of=login")
    Observable<ResponseBody> phoneLogin(@Field("user_name") String userName,
                                        @Field("password") String pwd,
                                        @Field("client") String client
    );

    @FormUrlEncoded
    @POST("index.php?do=shoper_login&of=fastlogin")
    Observable<ComResponse<ErrorModel>> vLogin(@Field("user_name") String userName,
                                               @Field("captcha") String pwd,
                                               @Field("client") String client
    );
}
