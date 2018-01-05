package com.xp.coordinator.coordinatortest.rxjava2;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.commonlib.base.BaseActivity;
import com.iwangfan.foundationlibary.utils.LogUtils;
import com.xp.coordinator.coordinatortest.AppApplication;
import com.xp.coordinator.coordinatortest.R;
import com.xp.coordinator.coordinatortest.livedata.bean.InfoBean;
import com.xp.coordinator.coordinatortest.livedata.bean.NewsBean;
import com.xp.coordinator.coordinatortest.livedata.database.InfoDao;
import com.xp.coordinator.coordinatortest.mvp.entity.HomeAndroidEntity;
import com.xp.coordinator.coordinatortest.rxjava2.entity.AllCity;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * @类描述：应用常量类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/28 0028 16:46
 * @修改人：
 * @修改时间：2017/12/28 0028 16:46
 * @修改备注：
 */

public class TestRxjava2Activity extends BaseActivity implements OnClickListener {
    @BindView(R.id.btn_test1)
    Button btnTest1;
    @BindView(R.id.btn_test2)
    Button btnTest2;
    @BindView(R.id.btn_test3)
    Button btnTest3;
    @BindView(R.id.btn_test4)
    Button btnTest4;
    @BindView(R.id.btn_test5)
    Button btnTest5;
    @BindView(R.id.btn_test6)
    Button btnTest6;
    @BindView(R.id.btn_test7)
    Button btnTest7;
    @BindView(R.id.btn_test8)
    Button btnTest8;
    @BindView(R.id.btn_test9)
    Button btnTest9;
    @BindView(R.id.btn_test10)
    Button btnTest10;
    @BindView(R.id.btn_test11)
    Button btnTest11;

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    protected void getExtras(Intent intent) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_test_rxjava2;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mSubscription) mSubscription.cancel();
        if (null != disposable) disposable.dispose();
        if (null != disposable11) disposable11.dispose();
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        btnTest1.setOnClickListener(this);
        btnTest2.setOnClickListener(this);
        btnTest3.setOnClickListener(this);
        btnTest4.setOnClickListener(this);
        btnTest5.setOnClickListener(this);
        btnTest6.setOnClickListener(this);
        btnTest7.setOnClickListener(this);
        btnTest8.setOnClickListener(this);
        btnTest9.setOnClickListener(this);
        btnTest10.setOnClickListener(this);
        btnTest11.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test1:
                btnTestClickEvent1();
                break;
            case R.id.btn_test2:
                btnTestClickEvent2();
                break;
            case R.id.btn_test3:
                btnTestClickEvent3();
                break;
            case R.id.btn_test4:
                btnTestClickEvent4();
                break;
            case R.id.btn_test5:
                btnTestClickEvent5();
                break;
            case R.id.btn_test6:
                btnTestClickEvent6();
                break;
            case R.id.btn_test7:
                btnTestClickEvent7_1();
                break;
            case R.id.btn_test8:
                btnTestClickEvent8();
                break;
            case R.id.btn_test9:
                btnTestClickEvent9();
                break;
            case R.id.btn_test10:
                btnTestClickEvent10();
                break;
            case R.id.btn_test11:
                btnTestClickEvent11();
                break;
            default:
                break;
        }
    }

    //Observer
    private void btnTestClickEvent1() {
        //也可以连起来写
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                LogUtils.d("Test", "emit 1");
                e.onNext(1);
                LogUtils.d("Test", "emit 2");
                e.onNext(2);
                LogUtils.d("Test", "emit 3");
                e.onNext(3);
                LogUtils.d("Test", "emit complete");
                e.onComplete();
                LogUtils.d("Test", "emit 4");
                e.onNext(4);
            }
        });
        Observer<Integer> observer = new Observer<Integer>() {
            private Disposable mDisposable;
            private int i;

            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("Test", "onSubscribe");
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("Test", "onNext -> " + integer);
                i++;
                if (i == 2) {
                    LogUtils.d("Test", "Disposable");
                    mDisposable.dispose();
                    LogUtils.d("Test", "isDisposable = " + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Test", "onError");
            }

            @Override
            public void onComplete() {
                LogUtils.d("Test", "onComplete");
            }
        };
        observable.subscribe(observer);
    }

    //Consumer
    private void btnTestClickEvent2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                LogUtils.d("Test", "emit 1");
                e.onNext(1);
                LogUtils.d("Test", "emit 2");
                e.onNext(2);
                LogUtils.d("Test", "emit 3");
                e.onNext(3);
                LogUtils.d("Test", "emit complete");
                e.onComplete();
                LogUtils.d("Test", "emit 4");
                e.onNext(4);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.d("Test", "onNext: " + integer);
            }
        });
    }

    //主线程 新县城
    private void btnTestClickEvent3() {
        //也可以连起来写
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                LogUtils.d("Test", "Observable thread is : " + Thread.currentThread().getName());
                LogUtils.d("Test", "emit 1");
                e.onNext(1);
            }
        });
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("Test", "onSub·       scribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("Test", "Observer thread is : " + Thread.currentThread().getName());
                LogUtils.d("Test", "onNext -> " + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Test", "onError");
            }

            @Override
            public void onComplete() {
                LogUtils.d("Test", "onComplete");
            }
        };
        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d("Test", "after Observeon(main) thread is : " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d("Test", "after Observeon(io) thread is : " + Thread.currentThread().getName());
                    }
                }).subscribe(observer);
    }

    //实践 请求接口 回调数据
    private void btnTestClickEvent4() {
        AppApplication.getInstance().sharedHttpApi().getInfoBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InfoBean bean) {
                        LogUtils.d("Test", "bean = " + bean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //过滤 map-》转换
    private void btnTestClickEvent5() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "111");
        map.put("2", "222");
        map.put("3", "333");
        map.put("4", "444");
        Observable.just(map).filter(new Predicate<Object>() {
            @Override
            public boolean test(Object o) throws Exception {
                return ((HashMap) o).containsKey("1");
            }
        }).map(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) throws Exception {
                return ((HashMap) o).get("1");
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        LogUtils.d("Test", "value = " + o.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //模拟真实场景
    private void btnTestClickEvent6() {
        AllCity allCity = new AllCity();
        allCity.setResult(allCity.getCitys());
        Flowable.just(allCity)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<AllCity, Publisher<AllCity.City>>() {
                    @Override
                    public Publisher<AllCity.City> apply(AllCity allCity) throws Exception {
                        ArrayList<AllCity.City> citys = allCity.getResult();
                        return Flowable.fromIterable(citys);
                    }
                })
                .filter(new Predicate<AllCity.City>() {
                    @Override
                    public boolean test(AllCity.City city) throws Exception {
                        String id = city.getId();
                        if (Integer.parseInt(id) < 5) {
                            return true;
                        }
                        return false;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AllCity.City>() {
                    @Override
                    public void accept(AllCity.City city) throws Exception {
                        LogUtils.d("Test", "city = " + city.toString());
                    }
                });

    }

    //模拟背压
    private void btnTestClickEvent7() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                int index = 0;
                while (true) {
                    e.onNext(index++);
                    LogUtils.d("Test", "index = " + index);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Thread.sleep(2000);
                        LogUtils.d("Test", "index = " + integer);
                    }
                });

    }

    private Subscription mSubscription;

    //Flowable 支持背压
    private void btnTestClickEvent7_1() {
        CompositeDisposable disposable = new CompositeDisposable();
        Flowable.create(new FlowableOnSubscribe<Integer>() {

            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                int index = 0;
                while (index < 150) {
                    e.onNext(index++);
                    LogUtils.d("Test", "index = " + index);
//                    if (index == 20) mSubscription.cancel();
                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        LogUtils.d("Test", "index = " + integer);
                        if (integer == 20) mSubscription.cancel();
                    }

                    @Override
                    public void onError(Throwable t) {
                        LogUtils.d("Test", "onError = " + t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //模拟取消
    Disposable disposable;

    private void btnTestClickEvent7_2() { //disposable 的时候 上面会一直执行 下面会中断

        disposable = Flowable.create(new FlowableOnSubscribe<Integer>() {

            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                int index = 0;
                while (index < 150) {
                    e.onNext(index++);
                    LogUtils.d("Test", "index = " + index);
                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d("Test", "index = " + integer);
                        if (integer == 20) disposable.dispose();
                    }
                });
    }

    //测试concat 交错发射多个Observable.  先缓存在网络
    private void btnTestClickEvent8() {
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
        }).subscribeOn(Schedulers.io());
        Observable<InfoBean> network = AppApplication.getInstance().sharedHttpApi().getInfoBean()
                .subscribeOn(Schedulers.io())
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
                    public void accept(InfoBean beans) throws Exception {
                        LogUtils.d("Test", "获取成功 = " + beans.getNewslist().size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.d("Test", "获取失败 = " + throwable.getMessage());
                    }
                });
    }

    //测试 flatMap 多个网络请求依次依赖
    private void btnTestClickEvent9() {
        AppApplication.getInstance().sharedHttpApi().getInfoBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<InfoBean>() {
                    @Override
                    public void accept(InfoBean bean) throws Exception {
                        //先获取新闻 然后做一些事情
                        LogUtils.d("Test", "accept = " + bean.getNewslist().size());
                    }
                })
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<InfoBean, ObservableSource<HomeAndroidEntity>>() {
                    @Override
                    public ObservableSource<HomeAndroidEntity> apply(InfoBean bean) throws Exception {
                        //这里做一些判断如果有新闻 返回 不然的话不返回
                        if (null != bean) {
                            LogUtils.d("Test", "apply = " + bean.getNewslist().size());
                            return AppApplication.getInstance().sharedHttpApi().getHomeTypes();
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeAndroidEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeAndroidEntity homeAndroidEntity) {
                        LogUtils.d("Test", "onNext = " + homeAndroidEntity.getResults().size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d("Test", "onError = " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //测试 zip 多个数据合并为一个
    private void btnTestClickEvent10() {
        Observable<InfoBean> beanObservable = AppApplication.getInstance().sharedHttpApi().getInfoBean();
        Observable<HomeAndroidEntity> homeAndroidEntityObservable = AppApplication.getInstance().sharedHttpApi().getHomeTypes();
        Observable.zip(beanObservable, homeAndroidEntityObservable, new BiFunction<InfoBean, HomeAndroidEntity, String>() {
            @Override
            public String apply(InfoBean bean, HomeAndroidEntity homeAndroidEntity) throws Exception {
                StringBuilder builder = new StringBuilder();
                builder.append(bean.getNewslist().get(0).getDescription())
                        .append("\n")
                        .append(homeAndroidEntity.getResults().get(0).getDesc());
                return builder.toString();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.d("Test", "accept = " + s);
                    }
                })
        ;
    }

    //测试 interval 心跳间隔任务 轮训
    private Disposable disposable11;

    private void btnTestClickEvent11() {
        disposable11 = Flowable.interval(1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtils.d("Test", "doOnNext.accept = " + aLong);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtils.d("Test", "subscribe.accept = " + aLong);
                        if (10 == aLong) disposable11.dispose();
                    }
                });
    }

}
