package com.commonlib.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.iwangfan.foundationlibary.utils.LogUtils;

import java.lang.reflect.Type;

/**
 * 使用例子
 * 要获得的对象
 * ResultData<User> rd = new ResultData<User>();
 * 获得对象类型
 * Type type = new TypeToken<ResultData<User>>() {}.getType();
 * 将json字符串转换成对象
 * rd = JsonUtil.fromJson(json, type);
 * 获得User对象
 * User user=rd.getData();
 */
public class JsonUtil {

    private static Gson gson = new Gson();

    private static Gson gsonWithFilter = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .create();

    /**
     * 将对象转换为json串,过滤Exposed
     *
     * @param src 待转换对象
     * @return
     */
    public static String toJsonWithFilter(Object src) {
        if (src == null)
            return "";
        return gsonWithFilter.toJson(src);
    }

    /**
     * 将对象转换为json串,过滤Exposed
     *
     * @param src       待转换对象
     * @param typeOfSrc 对象的type类型
     * @return
     */
    public static String toJsonWithFilter(Object src, Type typeOfSrc) {
        if (src == null)
            return "";
        return gsonWithFilter.toJson(src, typeOfSrc);
    }

    /**
     * 将对象转换为json串
     *
     * @param src 待转换对象
     * @return
     */
    public static String toJson(Object src) {
        if (src == null)
            return "";
        return gson.toJson(src);
    }

    /**
     * 将对象转换为json串
     *
     * @param src       待转换对象
     * @param typeOfSrc 对象的type类型
     * @return
     */
    public static String toJson(Object src, Type typeOfSrc) {
        if (src == null)
            return "";
        return gson.toJson(src, typeOfSrc);
    }

    /**
     * 将json串转换为对象
     *
     * @param json     待转换json串
     * @param classOfT 对象的class类型
     * @param <T>      T
     * @return
     * @throws JsonSyntaxException
     */
    public synchronized static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            LogUtils.e(e);
        }
        return null;
    }

    /**
     * 将json穿转换为对象
     *
     * @param json    待转换json串
     * @param typeOfT 对象的type类型
     * @param <T>     T
     * @return
     * @throws JsonSyntaxException
     */
    public synchronized static <T> T fromJson(String json, Type typeOfT) {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            LogUtils.e(e);
        }
        return null;
    }
}
