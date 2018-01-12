package com.xp.coordinator.coordinatortest.mvp.contract;

import com.commonlib.retrofit_rx.listener.HttpOnNextListener;
import com.xp.coordinator.coordinatortest.mvp.base.BaseModel;
import com.xp.coordinator.coordinatortest.mvp.base.BasePresenter;
import com.xp.coordinator.coordinatortest.mvp.base.BaseView;
import com.xp.coordinator.coordinatortest.mvp.entity.HomeAndroidEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * @类描述：首页数据 关联类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/19 0019 18:07
 * @修改人：
 * @修改时间：2017/12/19 0019 18:07
 * @修改备注：
 */

public interface HomeContract {
    interface IHomeView extends BaseView {
        void setHomeData(List<HomeAndroidEntity.TypeAndroidEntity> datas);
    }

    interface IHomeModel extends BaseModel {
        void requestData(int pageSize, int pageNum, HttpOnNextListener listener);

        Observable<HomeAndroidEntity> requestDataObserver(int pageSize, int pageNum);

        Flowable<HomeAndroidEntity> requestDataFlowable(int pageSize, int pageNum);
    }

    abstract class IHomePresenter extends BasePresenter<IHomeView, IHomeModel> {
        public abstract void requestData(int pageNum, int pageSize);
    }
}
