package com.easybenefit.commons.rest.util;

/**
 * Created by whoislcj on 2015/11/4 - 11:08.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Description:类功能描述
 * @Copyright:Copyright (c) 2014
 * @Company: 杭州医本健康科技有限公司
 * @File: TypeInfo.java
 * @Author: 李超军
 * @Create ：2015年6月13日
 * @Version: 1.0.0
 * @Others:
 */

public class TypeInfo {

    private Class<?> componentType;
    private Class<?> rawType;

    private TypeInfo(Class<?> rawType, Class<?> componentType) {

        this.componentType = componentType;
        this.rawType = rawType;
    }

    public static TypeInfo createArrayType(Class<?> componentType) {
        return new TypeInfo(Array.class, componentType);
    }

    public static TypeInfo createNormalType(Class<?> componentType) {
        return new TypeInfo(null, componentType);
    }

    public static TypeInfo createParameterizedType(Class<?> rawType, Class<?> componentType) {
        return new TypeInfo(rawType, componentType);
    }

    public TypeInfo(Type type) {

        if (type instanceof ParameterizedType) {

            this.rawType = (Class<?>) ((ParameterizedType) type).getRawType();
            this.componentType = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
        } else if (type instanceof GenericArrayType) {

            this.rawType = Array.class;
            this.componentType = (Class<?>) ((GenericArrayType) type).getGenericComponentType();
        } else {

            this.componentType = (Class<?>) type;
        }

    }

    public Class<?> getComponentType() {
        return componentType;
    }

    public void setComponentType(Class<?> componentType) {
        this.componentType = componentType;
    }

    public Class<?> getRawType() {
        return rawType;
    }

    public void setRawType(Class<?> rawType) {
        this.rawType = rawType;
    }

    @Override
    public String toString() {
        return String.format("rowType:%s, componentType:%s", this.rawType, this.componentType);
    }

}




