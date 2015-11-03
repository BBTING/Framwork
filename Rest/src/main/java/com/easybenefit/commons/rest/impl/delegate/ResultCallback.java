package com.easybenefit.commons.rest.impl.delegate;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by whoislcj on 2015/11/3 - 18:06.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */

public abstract class ResultCallback<T> {

    Type mType;

    public ResultCallback() {

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

    public abstract void onFailed(String statusCode, String errorMessage);

    public abstract void onSuccess(T response);
}
