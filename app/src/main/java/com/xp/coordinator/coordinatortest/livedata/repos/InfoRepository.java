package com.xp.coordinator.coordinatortest.livedata.repos;

import android.arch.lifecycle.LiveData;

import com.xp.coordinator.coordinatortest.AppApplication;
import com.xp.coordinator.coordinatortest.livedata.api.HttpApi;
import com.xp.coordinator.coordinatortest.livedata.bean.InfoBean;
import com.xp.coordinator.coordinatortest.livedata.bean.NewsBean;
import com.xp.coordinator.coordinatortest.livedata.database.InfoDao;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/26 0026 15:57
 * @修改人：
 * @修改时间：2017/12/26 0026 15:57
 * @修改备注：
 */

public class InfoRepository {
    public LiveData<List<NewsBean>> getInfoBean() {
        HttpApi.getInfoBean().getInfo().subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<InfoBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(InfoBean bean) {
                final InfoDao dao = AppApplication.getInstance().getDataBase().infoDao();
                dao.insertAll(bean.getNewslist());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return AppApplication.getInstance().getDataBase().infoDao().getAll();
    }

    public LiveData<List<NewsBean>> updateInfoBean() {
        updateData();
        return AppApplication.getInstance().getDataBase().infoDao().getAll();
    }

    private void updateData() {
        //Retrofit 中的Call 返回是在主线程中
        HttpApi.getInfoBean().getInfoBean().enqueue(new Callback<InfoBean>() {
            @Override
            public void onResponse(Call<InfoBean> call, final Response<InfoBean> response) {
                final InfoDao dao = AppApplication.getInstance().getDataBase().infoDao();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (null != dao) {
                            List<NewsBean> list = dao.getAl();
                        }
                        dao.insertAll(response.body().getNewslist());
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<InfoBean> call, Throwable t) {

            }
        });
    }
}
