package com.xp.coordinator.coordinatortest.mvp.base;

import java.lang.ref.WeakReference;

/**
 * @类描述：基类presenter
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/19 0019 11:20
 * @修改人：
 * @修改时间：2017/12/19 0019 11:20
 * @修改备注：
 */

public abstract class BasePresenter<V, M> {
    private V mView;
    public M mModel;
    public WeakReference<V> mViewRef;

    public void attachViewModel(V view, M model) {
        mViewRef = new WeakReference<V>(view);
        this.mModel = model;
    }

    public V getView() {
        if (isAttach()) {
            return mViewRef.get();
        } else {
            return null;
        }
    }

    public boolean isAttach() {
        return null != mViewRef && null != mViewRef.get();
    }

    public void onDettach() {
        if (null != mViewRef) {
            mViewRef.clear();
            mViewRef = null;
        }
        mView = null;
        mModel = null;
    }
}
