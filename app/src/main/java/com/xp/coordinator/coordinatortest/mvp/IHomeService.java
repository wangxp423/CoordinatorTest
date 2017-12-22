package com.xp.coordinator.coordinatortest.mvp;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @类描述：首页接口类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/21 0021 11:53
 * @修改人：
 * @修改时间：2017/12/21 0021 11:53
 * @修改备注：
 */

public interface IHomeService {
    String baseUrl = "http://gank.io/api/data/Android/";

    @GET("{pageSize}/{pageNum}")
    Flowable<String> getAndroidType(@Path("pageSize") int pageSize, @Path("pageNum") int pageNum);
}
