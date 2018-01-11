package com.xp.coordinator.coordinatortest.livedata.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.iwangfan.foundationlibary.utils.LogUtils;
import com.xp.coordinator.coordinatortest.AppApplication;
import com.xp.coordinator.coordinatortest.livedata.bean.InfoBean;
import com.xp.coordinator.coordinatortest.livedata.bean.NewsBean;
import com.xp.coordinator.coordinatortest.livedata.database.InfoDao;
import com.xp.coordinator.coordinatortest.livedata.repos.InfoRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


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

    public ProfileViewModel() {
    }

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

    //第一种直接用observable livedata
    public MutableLiveData<List<NewsBean>> getNews() {
        final MutableLiveData<List<NewsBean>> listNews = new MutableLiveData<>();
        Observable<InfoBean> cache = Observable.create(new ObservableOnSubscribe<InfoBean>() {
            @Override
            public void subscribe(ObservableEmitter<InfoBean> e) throws Exception {
                InfoDao infoDao = AppApplication.getInstance().getDataBase().infoDao();
                List<NewsBean> data = infoDao.getAl();
                //在操作concat中只有调用onComplete之后才会执行下一个Observable
                if (null != data && data.size() > 0) {
                    LogUtils.d("Test", "读取缓存数据");
                    InfoBean bean = new InfoBean();
                    bean.setNewslist(data);
                    e.onNext(bean);
                } else {
                    LogUtils.d("Test", "缓存数据为NULL");
                    e.onComplete();
                }
            }
        });
        Observable<InfoBean> network = AppApplication.getInstance().sharedHttpApi().getInfoBean()
                .doOnNext(new Consumer<InfoBean>() {
                    @Override
                    public void accept(InfoBean bean) throws Exception {
                        LogUtils.d("Test", "从网络读取数据成功,然后缓存");
                        InfoDao infoDao = AppApplication.getInstance().getDataBase().infoDao();
                        infoDao.insertAll(bean.getNewslist());
                    }
                });
        Observable.concat(cache, network)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InfoBean>() {
                    @Override
                    public void accept(InfoBean bean) throws Exception {
                        LogUtils.d("Test", "获取成功 = " + bean.getNewslist().size());
                        List<NewsBean> list = bean.getNewslist();
                        listNews.setValue(list);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d("Test", "获取异常 = " + throwable.getMessage());
                    }
                });
        return listNews;
    }

    public LiveData<List<NewsBean>> getNews2() {
        //直接返回LiveData是有问题的，直接返回的前提是你要确保缓存有数据，不然通过cacheLiveData 不能判断是否有数据
        //getValue为null  但是 其实里面是有数据的
        LiveData<List<NewsBean>> cacheLiveData = AppApplication.getInstance().getDataBase().infoDao().getAll();
        if (null != cacheLiveData) {
            List<NewsBean> data = cacheLiveData.getValue();
            boolean is = cacheLiveData.hasObservers();
            boolean isActivity = cacheLiveData.hasActiveObservers();
            LogUtils.d("Test", "读取缓存数据");
            return cacheLiveData;
        }
        final MutableLiveData<List<NewsBean>> netLiveData = new MutableLiveData<>();
        AppApplication.getInstance().sharedHttpApi().getInfoBean()
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<InfoBean>() {
                    @Override
                    public void accept(InfoBean bean) throws Exception {
                        LogUtils.d("Test", "从网络读取数据成功,然后缓存");
                        InfoDao infoDao = AppApplication.getInstance().getDataBase().infoDao();
                        infoDao.insertAll(bean.getNewslist());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<InfoBean>() {
                    @Override
                    public void accept(InfoBean bean) throws Exception {
                        LogUtils.d("Test", "从网络读取数据成功,然后返回");
                        netLiveData.setValue(bean.getNewslist());
                    }
                });
        return netLiveData;
    }
}
