package com.xp.coordinator.coordinatortest.livedata.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.xp.coordinator.coordinatortest.livedata.bean.NewsBean;
import com.xp.coordinator.coordinatortest.livedata.repos.InfoRepository;

import java.util.List;


/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/26 0026 15:43
 * @修改人：
 * @修改时间：2017/12/26 0026 15:43
 * @修改备注：
 */

public class ProfileViewModel extends ViewModel {
    private InfoRepository infoRepository;

    public ProfileViewModel(InfoRepository repository) {
        this.infoRepository = repository;
    }

    public LiveData<List<NewsBean>> getInfos() {
        if (null == infoRepository) {
            Log.e("---->", "回调isNull");
        }
        return infoRepository.getInfoBean();

    }

    public LiveData<List<NewsBean>> updateInfoBeans() {
        return infoRepository.updateInfoBean();
    }
}
