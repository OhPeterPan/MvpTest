package com.wak.retrofit.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
//服务器会随机的对下发的资源做GZip操作,此时就没有相应的content-type,加上请求头"Accept-Encoding:identity"，强迫服务器不走压缩，此时就可以获取文件大小了
public interface DownLoadApk {

    @Streaming
    @GET()
    @Headers("Accept-Encoding:identity")
    Observable<ResponseBody> getApk(@Url String url);
}
