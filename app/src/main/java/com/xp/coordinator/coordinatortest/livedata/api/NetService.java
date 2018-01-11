package com.xp.coordinator.coordinatortest.livedata.api;

import com.xp.coordinator.coordinatortest.livedata.bean.InfoBean;
import com.xp.coordinator.coordinatortest.livedata.bean.NewsBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/26 0026 15:47
 * @修改人：
 * @修改时间：2017/12/26 0026 15:47
 * @修改备注：
 */

public interface NetService {
    @GET("/startup/?key=7fed97d2186ea83c78d3e4fd0b58ab56&num=5")
    Call<InfoBean> getInfoBean();

    @GET("/startup/?key=7fed97d2186ea83c78d3e4fd0b58ab56&num=5")
    Observable<InfoBean> getInfo();

    @GET("/startup/?key=7fed97d2186ea83c78d3e4fd0b58ab56&num=5")
    Observable<List<NewsBean>> getNewsList();

    @GET("/startup/?key=7fed97d2186ea83c78d3e4fd0b58ab56&num=5")
    Flowable<InfoBean> getInfo1();

    @GET("/startup/?key=7fed97d2186ea83c78d3e4fd0b58ab56&num=5")
    Observable<InfoBean> getInfo2();
}
