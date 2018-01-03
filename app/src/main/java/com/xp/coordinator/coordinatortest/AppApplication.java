package com.xp.coordinator.coordinatortest;

import android.support.multidex.MultiDexApplication;

import com.commonlib.retrofit_rx.RxRetrofitApp;
import com.commonlib.util.CrashUtil;
import com.xp.coordinator.coordinatortest.livedata.database.InfoDataBase;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/21 0021 14:47
 * @修改人：
 * @修改时间：2017/12/21 0021 14:47
 * @修改备注：
 */

public class AppApplication extends MultiDexApplication {
    private static AppApplication INSTANCE = null;

    public static synchronized AppApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        RxRetrofitApp.init(this);
        CrashUtil.getInstance().init(getApplicationContext());
    }

    public InfoDataBase getDataBase() {
        return InfoDataBase.getInstance(getApplicationContext());
    }

    static HttpApi Api;
    public HttpApi sharedHttpApi() {
        if (Api == null) {
            synchronized (this) {
                if (Api == null) {
                    Api = new HttpApi();
                }
            }
        }
        return Api;
    }
}
