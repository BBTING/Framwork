package com.easybenefit.commons.rest;

import com.easybenefit.commons.rest.impl.DynamicRestClientProxy;
import com.easybenefit.commons.rest.interceptor.RequestInterceptor;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gary_Cheung on 15/10/30.
 *
 * 创建RestClient实例的上下文管理对象（创建实例，以及全局的拦截器定义）
 */
public class RestClientContext {

    public static final String TAG = "ezb.rpc";

    // 全局的拦截器
    public final static List<RequestInterceptor> globalInterceptors = new ArrayList<RequestInterceptor>();

    public static void addGlobalInterceptor(RequestInterceptor interceptor) {

        if (interceptor != null) {

            globalInterceptors.add(interceptor);
        }
    }

    /**
     * 动态创建业务服务的代理对象
     * @param clientInterface 业务服务对象的类型（只支持接口）
     * @param <T> 返回指定类型的业务服务对象代理实例
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> clientInterface) {

        return (T) Proxy.newProxyInstance(clientInterface.getClassLoader(), new Class[]{ clientInterface }, new DynamicRestClientProxy());
    }
}
