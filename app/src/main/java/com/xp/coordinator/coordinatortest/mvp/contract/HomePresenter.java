package com.xp.coordinator.coordinatortest.mvp.contract;

import com.commonlib.retrofit_rx.exception.ApiException;
import com.commonlib.retrofit_rx.listener.HttpOnNextListener;
import com.commonlib.util.JsonUtil;
import com.xp.coordinator.coordinatortest.mvp.entity.HomeAndroidEntity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * @类描述：首页Presenter类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/20 0020 17:44
 * @修改人：
 * @修改时间：2017/12/20 0020 17:44
 * @修改备注：
 */

public class HomePresenter extends HomeContract.IHomePresenter {
    @Override
    public void requestData(int pageSize, int pageNum) {
        mModel.requestData(pageSize, pageNum, new HttpOnNextListener() {
            @Override
            public void onNext(int tag, String result) {
                HomeAndroidEntity entity = JsonUtil.fromJson(result, HomeAndroidEntity.class);
                if (null != entity && !entity.getError()) {
                    getView().setHomeData(entity.getResults());
                }
            }

            @Override
            public void onError(ApiException e) {

            }
        });
    }

    public void requestDataObserver(int pageSize, int pageNum) {
        mModel.requestDataObserver(pageSize, pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeAndroidEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeAndroidEntity homeAndroidEntity) {
                        if (null != homeAndroidEntity && !homeAndroidEntity.getError()) {
                            getView().setHomeData(homeAndroidEntity.getResults());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void requestDataFlowable(int pageSize, int pageNum) {
        mModel.requestDataFlowable(pageSize, pageNum)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<HomeAndroidEntity>() { //这个地方只能用ResourceSubscriber,用Subscriber没用
                    @Override
                    public void onNext(HomeAndroidEntity homeAndroidEntity) {
                        if (null != homeAndroidEntity && !homeAndroidEntity.getError()) {
                            getView().setHomeData(homeAndroidEntity.getResults());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
