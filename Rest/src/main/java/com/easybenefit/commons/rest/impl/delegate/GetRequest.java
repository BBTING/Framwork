package com.easybenefit.commons.rest.impl.delegate;

import com.easybenefit.commons.rest.ServiceCallback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

/**
 * Created by whoislcj on 2015/11/3 - 17:54.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class GetRequest extends BaseRequest {

    public GetRequest(OkHttpClient okHttpClient) {

        super(okHttpClient);

    }

    /**
     * 构建Request
     * @param url
     * @param tag
     * @return
     */
    private Request buildGetRequest(String url, Object tag) {

        Request.Builder builder = new Request.Builder().url(url);

        if (tag != null) {
            builder.tag(tag);
        }

        return builder.build();
    }

    /**
     * 请求
     * @param url
     * @param callback
     */
    public void get(String url, final ServiceCallback callback) {
        get(url, callback, null);
    }

    /**
     * 带tag请求
     * @param url
     * @param callback
     * @param tag
     */
    public void get(String url, final ServiceCallback callback, Object tag) {
        final Request request = buildGetRequest(url, tag);
        get(request, callback);
    }

    /**
     * 分发请求
     * @param request
     * @param callback
     */
    public void get(Request request, ServiceCallback callback) {
        deliveryRequest(callback, request);
    }
}
