package com.xp.coordinator.coordinatortest.mvp.contract;

import com.commonlib.retrofit_rx.listener.HttpOnNextListener;
import com.xp.coordinator.coordinatortest.AppApplication;

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
    public void requestData(int pageNum, int pageSize, HttpOnNextListener listener) {
        AppApplication.getInstance().sharedHttpApi().getHomeData(pageSize, pageNum, TAG_REQUEST_HOME_DATA, listener);
    }
}
