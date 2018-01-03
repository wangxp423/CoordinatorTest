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

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
    protected void initViews() {
        ButterKnife.bind(this);
        btnTest1.setOnClickListener(this);
        btnTest2.setOnClickListener(this);
        btnTest3.setOnClickListener(this);
        btnTest4.setOnClickListener(this);
        btnTest5.setOnClickListener(this);

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

    //过滤
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

}
