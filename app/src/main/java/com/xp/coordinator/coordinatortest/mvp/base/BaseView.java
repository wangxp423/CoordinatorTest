package com.xp.coordinator.coordinatortest.mvp.base;

import com.commonlib.entity.BaseResultEntity;

/**
 * @类描述：View基类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/19 0019 10:43
 * @修改人：
 * @修改时间：2017/12/19 0019 10:43
 * @修改备注：
 */

public interface BaseView {

    /**
     * 显示loading
     */
    void showLoading();

    /**
     * 隐藏loading
     */
    void hideLoading();

    /**
     * @param entity
     */
    void showError(BaseResultEntity entity);

}
