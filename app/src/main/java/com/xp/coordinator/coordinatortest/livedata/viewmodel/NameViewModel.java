package com.xp.coordinator.coordinatortest.livedata.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/25 0025 16:22
 * @修改人：
 * @修改时间：2017/12/25 0025 16:22
 * @修改备注：
 */

public class NameViewModel extends ViewModel {
    private MutableLiveData<String> mCurrentName;

    public MutableLiveData<String> getmCurrentName() {
        if (null == mCurrentName) {
            mCurrentName = new MutableLiveData<>();
        }
        return mCurrentName;
    }
}
