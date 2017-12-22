package com.xp.coordinator.coordinatortest.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.commonlib.base.BaseActivity;
import com.xp.coordinator.coordinatortest.mvp.base.BaseModel;
import com.xp.coordinator.coordinatortest.mvp.base.BasePresenter;
import com.xp.coordinator.coordinatortest.mvp.util.CreateUtil;

/**
 * @类描述：Mvp基类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/19 0019 10:42
 * @修改人：
 * @修改时间：2017/12/19 0019 10:42
 * @修改备注：
 */

public abstract class MvpActivity<T extends BasePresenter, M extends BaseModel> extends BaseActivity {
    protected T mPresenter;
    protected M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = CreateUtil.getT(this, 0);
        mModel = CreateUtil.getT(this, 1);
        mPresenter.attachViewModel(this, mModel);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDettach();
    }
}
