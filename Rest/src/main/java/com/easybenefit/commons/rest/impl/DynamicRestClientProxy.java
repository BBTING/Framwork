package com.easybenefit.commons.rest.impl;

import android.os.AsyncTask;
import android.util.Log;

import com.alibaba.fastjson.JSONException;
import com.easybenefit.commons.rest.MethodType;
import com.easybenefit.commons.rest.RestClient;
import com.easybenefit.commons.rest.RestClientContext;
import com.easybenefit.commons.rest.RestResponse;
import com.easybenefit.commons.rest.ServiceCallback;
import com.easybenefit.commons.rest.annotations.Body;
import com.easybenefit.commons.rest.annotations.Delete;
import com.easybenefit.commons.rest.annotations.Get;
import com.easybenefit.commons.rest.annotations.Interceptor;
import com.easybenefit.commons.rest.annotations.Interceptors;
import com.easybenefit.commons.rest.annotations.Param;
import com.easybenefit.commons.rest.annotations.Post;
import com.easybenefit.commons.rest.annotations.Put;
import com.easybenefit.commons.rest.interceptor.RequestInterceptor;
import com.easybenefit.commons.rest.util.ReqCallbackUtils;
import com.easybenefit.commons.rest.util.ReqHelper;
import com.easybenefit.commons.rest.util.TypeInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gary_Cheung on 15/10/29.
 */
public class DynamicRestClientProxy implements InvocationHandler {


    private RestClient restClient;

    public DynamicRestClientProxy() {

        this(new DefaultRestClientHttpImpl());
    }

    public DynamicRestClientProxy(RestClient restClient) {

        this.restClient = restClient;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String request_url = null;
        MethodType methodType = null;

        if (method.isAnnotationPresent(Get.class)) {

            Get annotation_Get = method.getAnnotation(Get.class);
            request_url = annotation_Get.value();
            methodType = MethodType.GET;
        } else if (method.isAnnotationPresent(Put.class)) {

            Put annotation_Put = method.getAnnotation(Put.class);
            request_url = annotation_Put.value();
            methodType = MethodType.PUT;
        } else if (method.isAnnotationPresent(Post.class)) {

            Post annotation_Post = method.getAnnotation(Post.class);
            request_url = annotation_Post.value();
            methodType = MethodType.POST;
        } else if (method.isAnnotationPresent(Delete.class)) {

            Delete annotation_Delete = method.getAnnotation(Delete.class);
            request_url = annotation_Delete.value();
            methodType = MethodType.DELETE;
        }

        if (request_url != null) {

            return this.callRemoteMethod(request_url, methodType, proxy, method, args);
        }

        return method.invoke(proxy, args);
    }

    private Object callRemoteMethod(final String request_url, final MethodType methodType, Object proxy, Method method, Object[] args) {

        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        final Map<String, Object> parameters = new HashMap<String, Object>();
        Object bodyParameters = null;

        if (parameterAnnotations != null) {

            int index = 0;
            top:
            for (Annotation[] annotations : parameterAnnotations) {

                for (Annotation annotation : annotations) {

                    if (annotation instanceof Param) {

                        Param param = (Param) annotation;

                        Object argValue = args[index];

                        if (argValue == null) {

                            argValue = param.defaultValue();
                        }

                        parameters.put(param.name(), argValue);
                        break;
                    } else if (annotation instanceof Body) {

                        bodyParameters = args[index];
                        break top;
                    }

                }

                index++;
            }
        }


        // 构造 RequestInterceptor
        final List<RequestInterceptor> interceptorList = buildRequestInterceptors(method);

        // 异步执行远程的业务服务调用
        this.tryToExecuteRemoteCall(request_url, methodType, args[args.length - 1], parameters, bodyParameters, interceptorList);

        return null;
    }

    private void doExecuteRealCall(final String request_url, final MethodType methodType, Object arg, Map<String, Object> parameters, Object bodyParameters, final List<RequestInterceptor> interceptorList) {

        final ServiceCallback callback = (ServiceCallback) arg;

        for (RequestInterceptor interceptor : interceptorList) {

            interceptor.onPreExecute(request_url, null);
        }


        // 执行拦截器中的onPostExecute
        for (RequestInterceptor interceptor : interceptorList) {

            interceptor.onPostExecute();
        }

    }


    @SuppressWarnings("unchecked")
    private void tryToExecuteRemoteCall(final String request_url, final MethodType methodType, Object arg, Map<String, Object> parameters, Object bodyParameters, final List<RequestInterceptor> interceptorList) {

        final ServiceCallback callback = (ServiceCallback) arg;

        AsyncTask<Object, Integer, RestResponse> asyncTask = new AsyncTask<Object, Integer, RestResponse>() {

            @Override
            protected RestResponse doInBackground(Object... params) {

                Map<String, String> heads = new HashMap<String, String>();

                // 执行拦截器中的onPreExecute
                for (RequestInterceptor interceptor : interceptorList) {

                    if (!interceptor.onPreExecute(request_url, heads)) {

                        return RestResponse.AbortRestResponse.make();
                    }
                }

                return getRestResponse(request_url, methodType, (Map<String, Object>) params[0], params[1], heads);
            }

            protected void onPostExecute(RestResponse result) {

                if (result == null || callback == null) {

                    return;
                }

                if (result instanceof RestResponse.AbortRestResponse) {

                    return;
                }

                if (result.statusCode.equals("1")) {

                    TypeInfo typeInfo = ReqCallbackUtils.getCallbackGenericType(callback.getClass());
                    String data = result.responseBody;

                    try {

                        Object serviceResult = ReqHelper.parseHttpResult(typeInfo, data);

                        callback.onSuccess(serviceResult);
                    } catch (JSONException jsonException) {

                        callback.onFailed(String.valueOf(RestClient.ERROR_PARSER), jsonException.toString());
                    }

                } else {

                    callback.onFailed(result.statusCode, result.errorMessage);
                }

                // 执行拦截器中的onPostExecute
                for (RequestInterceptor interceptor : interceptorList) {

                    interceptor.onPostExecute();
                }
            }
        };

        //执行异步任务
        asyncTask.execute(parameters, bodyParameters);
    }

    private List<RequestInterceptor> buildRequestInterceptors(Method method) {

        List<RequestInterceptor> interceptorList = new ArrayList<RequestInterceptor>();

        try {

            List<Interceptor> interceptorAnnotationList = new ArrayList<Interceptor>();

            // 类级别的Interceptor
            Interceptors class_interceptors = method.getDeclaringClass().getAnnotation(Interceptors.class);
            if (class_interceptors != null) {

                for (Interceptor interceptor : class_interceptors.value()) {

                    interceptorAnnotationList.add(interceptor);
                }
            }

            // 方法级别的Interceptor
            Interceptor method_interceptor = method.getAnnotation(Interceptor.class);
            if (method_interceptor != null) {

                interceptorAnnotationList.add(method_interceptor);
            }


            // 全局的RequestInterceptor
            for (RequestInterceptor interceptor : RestClientContext.globalInterceptors) {

                interceptorList.add(interceptor);
            }

            for (Interceptor interceptor : interceptorAnnotationList) {

                interceptorList.add((RequestInterceptor) interceptor.value().newInstance());
            }
        } catch (Exception exception) {

            Log.e(RestClientContext.TAG, "Failed to buildRequestInterceptors ...", exception);
            // 忽略异常
        }

        return interceptorList;
    }

    private RestResponse getRestResponse(String request_url, MethodType methodType, Map<String, Object> parameters, Object bodyParameters, Map<String, String> header) {

        RestResponse result = null;
        switch (methodType) {

            case GET:

                if (bodyParameters == null) {

                    result = this.restClient.doGet(request_url, parameters, header);
                } else {

                    result = this.restClient.doGet(request_url, bodyParameters, header);
                }
                break;
            case PUT:

                if (bodyParameters == null) {

                    result = this.restClient.doPut(request_url, parameters, header);
                } else {

                    result = this.restClient.doPut(request_url, bodyParameters, header);
                }
                break;
            case POST:

                if (bodyParameters == null) {

                    result = this.restClient.doPost(request_url, parameters, header);
                } else {

                    result = this.restClient.doPost(request_url, bodyParameters, header);
                }
                break;
            case DELETE:

                if (bodyParameters == null) {

                    result = this.restClient.doDelete(request_url, parameters, header);
                } else {

                    result = this.restClient.doDelete(request_url, bodyParameters, header);
                }
                break;
        }

        return result;
    }
}
