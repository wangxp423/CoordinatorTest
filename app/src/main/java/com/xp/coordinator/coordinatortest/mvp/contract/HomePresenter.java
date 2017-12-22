package com.xp.coordinator.coordinatortest.mvp.contract;

import com.commonlib.retrofit_rx.exception.ApiException;
import com.commonlib.retrofit_rx.listener.HttpOnNextListener;
import com.commonlib.util.JsonUtil;
import com.xp.coordinator.coordinatortest.mvp.entity.HomeAndroidEntity;

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
    public void requestData(int pageNum, int pageSize) {
        mModel.requestData(pageNum, pageSize, new HttpOnNextListener() {
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
}
