package com.xp.coordinator.coordinatortest;

import com.commonlib.retrofit_rx.api.HttpManagerApi;
import com.commonlib.retrofit_rx.listener.HttpOnNextListener;
import com.xp.coordinator.coordinatortest.livedata.api.NetService;
import com.xp.coordinator.coordinatortest.livedata.bean.InfoBean;
import com.xp.coordinator.coordinatortest.mvp.IHomeService;
import com.xp.coordinator.coordinatortest.mvp.entity.HomeAndroidEntity;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * @类描述：网络请求 工具类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/21 0021 14:37
 * @修改人：
 * @修改时间：2017/12/21 0021 14:37
 * @修改备注：
 */

public class HttpApi extends HttpManagerApi {
    public void getHomeData(int pageSize, int pageNum, int tag, HttpOnNextListener listener) {
        setBaseUrl(IHomeService.baseUrl);
        IHomeService service = getRetrofit().create(IHomeService.class);
        doHttpDealDelay(tag, listener, service.getAndroidType(pageSize, pageNum), 3);
    }

    public Observable<HomeAndroidEntity> getHomeDataObserver(int pageSize, int pageNum) {
        setBaseUrl(IHomeService.baseUrl);
        IHomeService service = getRetrofit().create(IHomeService.class);
        return service.getHomeTypes(pageSize, pageNum);
    }

    public Flowable<HomeAndroidEntity> getHomeDataFlowable(int pageSize, int pageNum) {
        setBaseUrl(IHomeService.baseUrl);
        IHomeService service = getRetrofit().create(IHomeService.class);
        return getFlowable(service.getHomeTypesData(pageSize, pageNum));
    }

    public Observable<InfoBean> getInfoBean() {
        setBaseUrl("http://api.tianapi.com");
        NetService service = getRetrofit().create(NetService.class);
        return service.getInfo();
    }

    public Observable<InfoBean> getListNews() {
        setBaseUrl("http://api.tianapi.com");
        NetService service = getRetrofit().create(NetService.class);
        return service.getInfo2();
    }

    public Observable<HomeAndroidEntity> getHomeTypes() {
        setBaseUrl(IHomeService.baseUrl);
        IHomeService service = getRetrofit().create(IHomeService.class);
        return service.getHomeTypes(1, 5);
    }
}
