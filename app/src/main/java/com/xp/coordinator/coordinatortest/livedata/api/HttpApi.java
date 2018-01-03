package com.xp.coordinator.coordinatortest.livedata.api;

import com.xp.coordinator.coordinatortest.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @类描述：接口类 这个方法跟最外面的HttpApi一样，外面的事通用的。这个就暂且保留吧。
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/26 0026 16:12
 * @修改人：
 * @修改时间：2017/12/26 0026 16:12
 * @修改备注：
 */

public class HttpApi {
    public static Retrofit getRetrofit(String url) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static NetService getInfoBean() {
        return getRetrofit("http://api.tianapi.com").create(NetService.class);
    }

}
