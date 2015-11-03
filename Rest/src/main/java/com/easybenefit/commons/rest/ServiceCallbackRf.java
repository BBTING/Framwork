package com.easybenefit.commons.rest;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Gary_Cheung on 15/10/30.
 *
 * Rest API调用的异步回调接口
 */
public abstract class ServiceCallbackRf<T> {

    public Type mType;

    public ServiceCallbackRf() {

        mType = getSuperclassTypeParameter(getClass());
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {

        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {

            throw new RuntimeException("Missing type parameter.");
        }

        ParameterizedType parameterized = (ParameterizedType) superclass;

        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    /**
     * 当业务调用成功后的回调
     * @param result 业务结果对象
     */
    abstract void onSuccess(T result);

    /**
     * 当业务调用异常时的回调（如：业务异常、网络异常）
     * @param statusCode
     * @param errorMessage
     */
    abstract void onFailed(String statusCode, String errorMessage);

    /**
     * void类型的数据返回
     */
    final class Void {
    }
}
