package com.xp.coordinator.coordinatortest.mvp.contract;

import com.commonlib.retrofit_rx.listener.HttpOnNextListener;
import com.xp.coordinator.coordinatortest.AppApplication;
import com.xp.coordinator.coordinatortest.mvp.entity.HomeAndroidEntity;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * @类描述：首页Model类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/20 0020 17:43
 * @修改人：
 * @修改时间：2017/12/20 0020 17:43
 * @修改备注：
 */

public class HomeModel implements HomeContract.IHomeModel {
    public final int TAG_REQUEST_HOME_DATA = 1;

    @Override
    public void requestData(int pageSize, int pageNum, HttpOnNextListener listener) {
        AppApplication.getInstance().sharedHttpApi().getHomeData(pageSize, pageNum, TAG_REQUEST_HOME_DATA, listener);
    }

    @Override
    public Observable<HomeAndroidEntity> requestDataObserver(int pageSize, int pageNum) {
        return AppApplication.getInstance().sharedHttpApi().getHomeDataObserver(pageSize, pageNum);
    }

    @Override
    public Flowable<HomeAndroidEntity> requestDataFlowable(int pageSize, int pageNum) {
        return AppApplication.getInstance().sharedHttpApi().getHomeDataFlowable(pageSize, pageNum);
    }
}
