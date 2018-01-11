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
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

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
    @BindView(R.id.btn_test12)
    Button btnTest12;
    @BindView(R.id.btn_test13)
    Button btnTest13;
    @BindView(R.id.btn_test14)
    Button btnTest14;
    @BindView(R.id.btn_test15)
    Button btnTest15;
    @BindView(R.id.btn_test16)
    Button btnTest16;
    @BindView(R.id.btn_test17)
    Button btnTest17;
    @BindView(R.id.btn_test18)
    Button btnTest18;

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
        btnTest12.setOnClickListener(this);
        btnTest13.setOnClickListener(this);
        btnTest14.setOnClickListener(this);
        btnTest15.setOnClickListener(this);
        btnTest16.setOnClickListener(this);
        btnTest17.setOnClickListener(this);
        btnTest18.setOnClickListener(this);

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
            case R.id.btn_test12:
                btnTestClickEvent12();
                break;
            case R.id.btn_test13:
                btnTestClickEvent13();
                break;
            case R.id.btn_test14:
                btnTestClickEvent14();
                break;
            case R.id.btn_test15:
                btnTestClickEvent15();
                break;
            case R.id.btn_test16:
                btnTestClickEvent16();
                break;
            case R.id.btn_test17:
                btnTestClickEvent17();
                break;
            case R.id.btn_test18:
                btnTestClickEvent18();
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

    //测试 flatMap 多个网络请求依次依赖 将一个observable 转换成 一个或多个observable
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
    //timer 延迟执行
    private Disposable disposable11;

    private void btnTestClickEvent11() {
        //如果两个同时执行， 先执行上面 后同步执行第二个，此时由于disposable11已经被重新赋值，第一个中的dispose()方法不能生效
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
        LogUtils.d("Test", "timer.accept = ");
        disposable11 = Flowable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtils.d("Test", "timer.accept = " + aLong);
                    }
                });
    }

    //测试 concatMap 将一个observable 转换成 一个或多个observable 但是他能控制 顺序1-2-3
    private void btnTestClickEvent12() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.d("Test", "accept = " + s);
                    }
                });
    }

    //测试 skip  跳过 多少个数目以后开始  take 接受多少个
    private void btnTestClickEvent13() {
        Observable.just(1, 2, 3, 4, 5)
                .skip(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d("Test", "Observable.accept = " + integer);
                    }
                });
        Flowable.fromArray(1, 2, 3, 4, 5)
                .take(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d("Test", "Flowable.accept = " + integer);
                    }
                });
    }

    //TestRxjava2-single-distinct-buffer
    private void btnTestClickEvent14() {
        //single 只能接受一个参数
        Single.just(new Random().nextInt())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        LogUtils.d("Test", "Single.integer = " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        Observable.just(1, 1, 1, 2, 2, 3, 3, 4)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d("Test", "distinct.integer = " + integer);
                    }
                });
        List<Integer> testIntList = new ArrayList<>();
        testIntList.add(1);
        testIntList.add(1);
        testIntList.add(1);
        testIntList.add(2);
        testIntList.add(2);
        testIntList.add(3);
        testIntList.add(3);
        testIntList.add(4);
        Observable.fromIterable(testIntList)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d("Test", "distinct.fromIterable.integer = " + integer);
                    }
                });
        //将数据 分割成 长度为count
        // 每组数据起头跨步为skip即 index(0)起步count一组  index(0+skip)起步count一组  index(0+skip+skip)起步count一组
        //长度不能超过 数据长度
        Observable.just(1, 2, 3, 4, 5, 6)
                .buffer(3, 4)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        LogUtils.d("Test", "buffer.integers = " + integers.size());
                        for (Integer i : integers) {
                            LogUtils.d("Test", "buffer.integer = " + i);
                        }
                    }
                });
        Observable.fromIterable(testIntList)
                .buffer(3, 4)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        LogUtils.d("Test", "buffer.fromIterable.integers = " + integers.size());
                        for (Integer i : integers) {
                            LogUtils.d("Test", "buffer.fromIterable = " + i);
                        }
                    }
                });
    }

    //debounce-defer-last
    private void btnTestClickEvent15() {
        //debounce 过滤掉发射速度过快的问题
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                Thread.sleep(400);
                e.onNext(2);
                Thread.sleep(505);
                e.onNext(3);
                Thread.sleep(100);
                e.onNext(4);
                Thread.sleep(605);
                e.onNext(5);
                Thread.sleep(510);
                e.onNext(6);
                Thread.sleep(300);
            }
        })
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d("Test", "debounce.integer = " + integer);
                    }
                });

        //每次订阅都会创建一个Observable 跟这个Observable.just(1,2,3)执行一样
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1, 2, 3);
            }
        });
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("Test", "defer.onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("Test", "defer.onNext = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Test", "defer.onError");
            }

            @Override
            public void onComplete() {
                LogUtils.d("Test", "defer.onComplete");
            }
        });
        //取出最后一个 last(参数) 参数为如果没有last的默认值
        Observable.just(1, 2, 3)
                .last(4)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d("Test", "last.integer = " + integer);
                    }
                });
    }

    //merge-reduce-scan
    private void btnTestClickEvent16() {
        //merge 将多个observable 合并起来 支持多个可变参数和迭代
        List<Integer> testIntegers = new ArrayList<>();
        testIntegers.add(4);
        testIntegers.add(5);
        testIntegers.add(6);
        Observable.merge(Observable.just(1, 2, 3), Observable.fromIterable(testIntegers))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.d("Test", "merge.integer = " + integer);
                    }
                });
        //一次用一个方法处理一个值
        Observable.just(1, 2, 3)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        LogUtils.d("Test", "reduce.integer = " + integer + " integer2 = " + integer2);
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.d("Test", "reduce.accept.integer = " + integer);
            }
        });
        //和reduce一样 不过scan将过程中每一个结果输出
        Observable.just(1, 2, 3)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        LogUtils.d("Test", "scan.integer = " + integer + " integer2 = " + integer2);
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.d("Test", "scan.accept.integer = " + integer);
            }
        });
    }

    //PublishSubject-AsyncSubject-BehaviorSubject
    private void btnTestClickEvent17() {
        //publishSubject onNext会通知每个观察者
        PublishSubject<Integer> publishSubject = PublishSubject.create();
        publishSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("Test", "First.observer.onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("Test", "First.observer.onNext = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Test", "First.observer.onError");
            }

            @Override
            public void onComplete() {
                LogUtils.d("Test", "First.observer.onComplete");
            }
        });
        publishSubject.onNext(1);
        publishSubject.onNext(2);
        publishSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("Test", "Second.observer.onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("Test", "Second.observer.onNext = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Test", "Second.observer.onError");
            }

            @Override
            public void onComplete() {
                LogUtils.d("Test", "Second.observer.onComplete");
            }
        });
        publishSubject.onNext(3);
        publishSubject.onNext(4);
        publishSubject.onComplete();

        //asyncSubject 在调用onComplete之前 除了subscrbe其他操作 都会被缓存，在调用onComplete之后 只有最后一个onNext会生效
        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
        asyncSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("Test", "asyncSubject.First.observer.onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("Test", "asyncSubject.First.observer.onNext = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Test", "asyncSubject.First.observer.onError");
            }

            @Override
            public void onComplete() {
                LogUtils.d("Test", "asyncSubject.First.observer.onComplete");
            }
        });
        asyncSubject.onNext(1);
        asyncSubject.onNext(2);
        asyncSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("Test", "asyncSubject.Second.observer.onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("Test", "asyncSubject.Second.observer.onNext = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Test", "asyncSubject.Second.observer.onError");
            }

            @Override
            public void onComplete() {
                LogUtils.d("Test", "asyncSubject.Second.observer.onComplete");
            }
        });
        asyncSubject.onNext(3);
        asyncSubject.onNext(4);
        asyncSubject.onComplete();

        //behaviorSubject 的最后一次onNext会被缓存，然后在sub
        BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("Test", "behaviorSubject.First.observer.onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("Test", "behaviorSubject.First.observer.onNext = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Test", "behaviorSubject.First.observer.onError");
            }

            @Override
            public void onComplete() {
                LogUtils.d("Test", "behaviorSubject.First.observer.onComplete");
            }
        });
        behaviorSubject.onNext(1);
        behaviorSubject.onNext(2);
        behaviorSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("Test", "behaviorSubject.Second.observer.onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("Test", "behaviorSubject.Second.observer.onNext = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Test", "behaviorSubject.Second.observer.onError");
            }

            @Override
            public void onComplete() {
                LogUtils.d("Test", "behaviorSubject.Second.observer.onComplete");
            }
        });
        behaviorSubject.onNext(3);
        behaviorSubject.onNext(4);
        behaviorSubject.onComplete();
    }

    //window-Completable
    private void btnTestClickEvent18() {
        //window 按照时间划分窗口 将数据发送给不同的observable
        Observable.interval(1, TimeUnit.SECONDS)
                .take(15) //最多接受15个
                .window(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(Observable<Long> longObservable) throws Exception {
                        LogUtils.d("Test", "longObservable.accept");
                        longObservable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        LogUtils.d("Test", "accept " + aLong);
                                    }
                                });
                    }
                });
        //completable 只关心结果，也就是completable没有onNext，要么成功要么出错，不关心过程，在subscribe后某个时间点返回结果
        Completable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d("Test", "Completable.onSubscribe");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d("Test", "Completable.onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d("Test", "Completable.onError");
                    }
                });


    }

}
