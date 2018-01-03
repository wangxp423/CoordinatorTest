package com.xp.coordinator.coordinatortest.retrofit;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * @类描述：retrofit 测试连接
 * @创建人：Wangxiaopan
 * @创建时间：2017/6/12 0012 9:58
 * @修改人：
 * @修改时间：2017/6/12 0012 9:58
 * @修改备注：
 */

public interface RetrofitTestService {
    String baseUrl = "https://www.izaodao.com/Api/";
    String baseZUrl = "http://news-at.zhihu.com";
    String baseDUrl = "http://api.9797168.com";

    @GET("AppFiftyToneGraph/videoLink/{once_no}")
    Flowable<String> getAllVedioBy(@Query("once_no") boolean once_no);

    @GET("/api/4/news/latest")
    Flowable<String> getLatestNews();

    @Multipart
    @POST("/app-dms/driver/upfile")
    Flowable<String> uploadImage(@Part("access_token") RequestBody uid, @Part MultipartBody.Part file);

    @Multipart
    @POST("/app-dms/driver/upfile")
    Flowable<String> uploadImages(@PartMap() Map<String, RequestBody> params, @Part List<MultipartBody.Part> files);
}
