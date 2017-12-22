package com.xp.coordinator.coordinatortest.mvp.util;

import java.lang.reflect.ParameterizedType;

/**
 * @类描述：反射工具类
 * @创建人：Wangxiaopan
 * @创建时间：2017/12/20 0020 17:24
 * @修改人：
 * @修改时间：2017/12/20 0020 17:24
 * @修改备注：
 */

public class CreateUtil {
    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass().getGenericSuperclass())).getActualTypeArguments()[i]).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
