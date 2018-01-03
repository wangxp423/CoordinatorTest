package com.commonlib.retrofit_rx.api;

import com.commonlib.retrofit_rx.listener.HttpOnNextListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * @类描述：请求数据统一封装基类
 * @创建人：Wangxiaopan
 * @创建时间：2017/6/5 09:51
 * @修改人：
 * @修改时间：2017/6/5 09:51
 * @修改备注：
 */
public abstract class BaseApi {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*");
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/json; charset=utf-8");
    /*是否能取消加载框*/
    private boolean cancel = false;
    /*是否显示加载框*/
    private boolean showProgress = true;
    /*是否需要缓存处理*/
    private boolean cache = false;
    /*基础url*/
    private String baseUrl = "https://www.izaodao.com/Api/";
    /*方法-如果需要缓存必须设置这个参数；不需要不用設置*/
    private String method = "";
    /*超时时间-默认10秒*/
    private int connectionTime = 10;
    /*有网情况下的本地缓存时间默认60秒*/
    private int cookieNetWorkTime = 60;
    /*无网络的情况下本地缓存时间默认30天*/
    private int cookieNoNetWorkTime = 24 * 60 * 60 * 30;
    /* retry次数*/
    private int retryCount = 1;
    /*retry延迟*/
    private long retryDelay = 100;
    /*retry叠加延迟*/
    private long retryIncreaseDelay = 100;

    /**
     * 设置参数
     *
     * @param retrofit
     * @return
     */
    public abstract Flowable getObservable(Retrofit retrofit);


    public int getCookieNoNetWorkTime() {
        return cookieNoNetWorkTime;
    }

    public void setCookieNoNetWorkTime(int cookieNoNetWorkTime) {
        this.cookieNoNetWorkTime = cookieNoNetWorkTime;
    }

    public int getCookieNetWorkTime() {
        return cookieNetWorkTime;
    }

    public void setCookieNetWorkTime(int cookieNetWorkTime) {
        this.cookieNetWorkTime = cookieNetWorkTime;
    }


    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return baseUrl + getMethod();
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public String getMethod() {
        return method;
    }

    /**
     * 如果是可变参数
     *
     * @param method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public long getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(long retryDelay) {
        this.retryDelay = retryDelay;
    }

    public long getRetryIncreaseDelay() {
        return retryIncreaseDelay;
    }

    public void setRetryIncreaseDelay(long retryIncreaseDelay) {
        this.retryIncreaseDelay = retryIncreaseDelay;
    }

    private static final HashMap<String, Disposable> mRequestQue = new HashMap<String, Disposable>(10);

    public void addQue(int tag, HttpOnNextListener callback, Disposable subscriber) {
        Disposable queSubscriber = null;
        if (null == callback) return;
        final String key = callback.hashCode() + "_" + tag;
        queSubscriber = mRequestQue.get(key);
        if (null == queSubscriber) {
            mRequestQue.put(key, subscriber);
        }
    }

    public void removeQue(int tag, HttpOnNextListener callback) {
        Disposable queSubscriber = null;
        if (null == callback) return;
        final String key = callback.hashCode() + "_" + tag;
        queSubscriber = mRequestQue.get(key);
        if (null != queSubscriber) {
            mRequestQue.remove(key);
            queSubscriber.dispose();
        }
    }

    public void cancelHttpDeal(int tag, HttpOnNextListener callback) {
        if (null == callback) return;
        final String key = callback.hashCode() + "_" + tag;
        Disposable subscriber = mRequestQue.get(key);
        if (null != subscriber) {
            mRequestQue.remove(key);
            subscriber.dispose();
            if (subscriber instanceof ResourceSubscriber) {
                ResourceSubscriber resourceSubscriber = (ResourceSubscriber) subscriber;
                resourceSubscriber.onComplete();
            }
        }
    }

    public HashMap<String, RequestBody> getPartMap(HashMap<String, String> params) {
        HashMap<String, RequestBody> partMap = new HashMap<>();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> iterable_element : params.entrySet()) {
                RequestBody body = RequestBody.create(MEDIA_TYPE_MARKDOWN, iterable_element.getValue());
                partMap.put(iterable_element.getKey(), body);
            }
        }
        return partMap;
    }

    public List<MultipartBody.Part> getPartList(HashMap<String, File> filesMap) {
        ArrayList<MultipartBody.Part> partList = new ArrayList<>();
        if (filesMap != null && filesMap.size() > 0) {
            for (Map.Entry<String, File> iterable_element : filesMap.entrySet()) {
                RequestBody requestBody = RequestBody.create(MEDIA_TYPE_PNG, iterable_element.getValue());
                MultipartBody.Part part = MultipartBody.Part.createFormData(iterable_element.getKey(), iterable_element.getValue().getName(), requestBody);
                partList.add(part);
            }
        }
        return partList;
    }
}
