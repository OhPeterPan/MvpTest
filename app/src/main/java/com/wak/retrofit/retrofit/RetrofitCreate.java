package com.wak.retrofit.retrofit;

import com.wak.retrofit.http.DownLoadApk;
import com.wak.retrofit.http.Login;
import com.wak.retrofit.http.UploadFile;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Invocation;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Tag;

public class RetrofitCreate {
    private static Retrofit retrofit;

    private static Retrofit generateRetrofit() {

        if (retrofit == null) {
            synchronized (RetrofitCreate.class) {
                if (retrofit == null) {

                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .readTimeout(60, TimeUnit.SECONDS)
                            .writeTimeout(60, TimeUnit.SECONDS)
                            .build();

                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://www.chadaodian.cn/cddAppv3/")
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okHttpClient)
                            .build();
                }
            }
        }

        return retrofit;
    }

    public static OkHttpClient getOkHttpClient() {
        return (OkHttpClient) generateRetrofit().callFactory();
    }

    public static Dispatcher getDispatcher() {
        return getOkHttpClient().dispatcher();
    }

    public static Login loginCreate() {
        return generateRetrofit().create(Login.class);
    }

    public static DownLoadApk downloadApkCreate() {
        //retrofit = generateRetrofit().newBuilder().baseUrl("http://www.chadaodian.com/").build();
        return generateRetrofit().create(DownLoadApk.class);
    }

    public static UploadFile uploadApkCreate() {
        //retrofit = generateRetrofit().newBuilder().baseUrl("http://www.chadaodian.com/").build();
        return generateRetrofit().create(UploadFile.class);
    }
}
