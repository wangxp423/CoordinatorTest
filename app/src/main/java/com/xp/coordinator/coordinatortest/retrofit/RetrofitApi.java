package com.xp.coordinator.coordinatortest.retrofit;

import com.commonlib.retrofit_rx.api.HttpManagerApi;
import com.commonlib.retrofit_rx.exception.ApiException;
import com.commonlib.retrofit_rx.listener.HttpOnNextListener;
import com.iwangfan.foundationlibary.utils.LogUtils;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @类描述：retrofit 请求管理类
 * @创建人：Wangxiaopan
 * @创建时间：2017/6/12 0012 9:56
 * @修改人：
 * @修改时间：2017/6/12 0012 9:56
 * @修改备注：
 */
//这里的方法 可以直接用doHttpDeal 也可以重写返回 Flowable 或者 Observable
public class RetrofitApi extends HttpManagerApi implements HttpOnNextListener {
    private final int TAG_REQUEST_ALL_VEDIO = 1;
    private final int TAG_REQUEST_LASTEST_NEWS = 2;
    private final int TAG_REQUEST_UPLOAD_IMG = 3;

    public void getAllVedios() {
        setCache(true);
        setBaseUrl(RetrofitTestService.baseUrl);
        setMethod("AppFiftyToneGraph/videoLink");
        RetrofitTestService service = getRetrofit().create(RetrofitTestService.class);
        doHttpDeal(TAG_REQUEST_ALL_VEDIO, this, service.getAllVedioBy(true));
    }

    public void getLastestNews() {
        setCache(true);
        setBaseUrl(RetrofitTestService.baseZUrl);
        setMethod("/api/4/news/latest");
        RetrofitTestService service = getRetrofit().create(RetrofitTestService.class);
        doHttpDeal(TAG_REQUEST_LASTEST_NEWS, this, service.getLatestNews());
    }


    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/json; charset=utf-8");

    public void uploadImage(File file) {
        setCache(false);
        setBaseUrl(RetrofitTestService.baseDUrl);
        RetrofitTestService service = getRetrofit().create(RetrofitTestService.class);
        RequestBody access_token = RequestBody.create(MediaType.parse("text/plain"), "65e1866dbbddfc987268ab1b2e30b8b8");
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
//        RequestBody access_token = RequestBody.create(MEDIA_TYPE_MARKDOWN, HttpApi.ACCESS_TOKEN);
//        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_PNG, file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("fileName", file.getName(), requestBody);
        doHttpDeal(TAG_REQUEST_UPLOAD_IMG, this, service.uploadImage(access_token, part));
    }

    public void uploadImages(File file) {
        setCache(false);
        setBaseUrl(RetrofitTestService.baseDUrl);
        RetrofitTestService service = getRetrofit().create(RetrofitTestService.class);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("access_token", "65e1866dbbddfc987268ab1b2e30b8b8");

        HashMap<String, File> filesMap = new HashMap<>();
        filesMap.put("fileName", file);

        doHttpDeal(TAG_REQUEST_UPLOAD_IMG, this, service.uploadImages(getPartMap(params), getPartList(filesMap)));
    }

    @Override
    public void onNext(int tag, String result) {
        LogUtils.d("Test", "onNext.tag = " + tag + " result = " + result);
    }

    @Override
    public void onError(ApiException e) {
        LogUtils.d("Test", "onError.message = " + e.getDisplayMessage() + " e = " + e);
    }
}
