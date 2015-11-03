package com.easybenefit.commons.rest.impl.delegate;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by whoislcj on 2015/11/3 - 18:22.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class PostRequest extends BaseRequest {

    public PostRequest(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }

  /*  private final MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream;charset=utf-8");
    private final MediaType MEDIA_TYPE_STRING = MediaType.parse("text/plain;charset=utf-8");

    public PostRequest(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }

    public void post(String url, Map<String, String> params, final ResultCallback callback) {
        post(url, params, callback, null);
    }

    public void post(String url, Map<String, String> params, final ResultCallback callback, Object tag) {
        DefaultHttpClientRf.Param[] paramsArr = map2Params(params);
        post(url, paramsArr, callback, tag);
    }

    public void post(String url, DefaultHttpClient.Param[] params, final ResultCallback callback) {
        post(url, params, callback, null);
    }

    *//**
     * 异步的post请求
     *//*
    public void post(String url, DefaultHttpClient.Param[] params, final ResultCallback callback, Object tag) {
        Request request = build(url, params, tag);
        deliveryRequest(callback, request);
    }

    *//**
     * 同步的Post请求:直接将bodyFile以写入请求体
     *//*
    public Response post(String url, File bodyFile) throws IOException {

        return post(url, bodyFile, null);
    }


    *//**
     * 直接将bodyStr以写入请求体
     *//*
    public void post(String url, String bodyStr, final ResultCallback callback) {

        post(url, bodyStr, callback, null);
    }

    public void post(String url, String bodyStr, final ResultCallback callback, Object tag) {

        postAsynWithMediaType(url, bodyStr, MediaType.parse("text/plain;charset=utf-8"), callback, tag);
    }

    *//**
     * 直接将bodyBytes以写入请求体
     *//*
    public void post(String url, byte[] bodyBytes, final ResultCallback callback) {

        post(url, bodyBytes, callback, null);
    }

    public void post(String url, byte[] bodyBytes, final ResultCallback callback, Object tag) {

        postAsynWithMediaType(url, bodyBytes, MediaType.parse("application/octet-stream;charset=utf-8"), callback, tag);
    }

    *//**
     * 直接将bodyFile以写入请求体
     *//*
    public void post(String url, File bodyFile, final ResultCallback callback) {

        post(url, bodyFile, callback, null);
    }

    public void post(String url, File bodyFile, final ResultCallback callback, Object tag) {

        postAsynWithMediaType(url, bodyFile, MediaType.parse("application/octet-stream;charset=utf-8"), callback, tag);
    }

    *//**
     * 直接将bodyStr以写入请求体
     *//*
    public void postAsynWithMediaType(String url, String bodyStr, MediaType type, final ResultCallback callback, Object tag) {

        RequestBody body = RequestBody.create(type, bodyStr);

        Request request = buildPostRequest(url, body, tag);

        deliveryRequest(callback, request);
    }

    *//**
     * 直接将bodyBytes以写入请求体
     *//*
    public void postAsynWithMediaType(String url, byte[] bodyBytes, MediaType type, final ResultCallback callback, Object tag) {

        RequestBody body = RequestBody.create(type, bodyBytes);

        Request request = buildPostRequest(url, body, tag);

        deliveryRequest(callback, request);
    }

    *//**
     * 直接将bodyFile以写入请求体
     *//*
    public void postAsynWithMediaType(String url, File bodyFile, MediaType type, final ResultCallback callback, Object tag) {

        RequestBody body = RequestBody.create(type, bodyFile);

        Request request = buildPostRequest(url, body, tag);

        deliveryRequest(callback, request);
    }


    *//**
     * post构造Request的方法
     *
     * @param url
     * @param body
     * @return
     *//*
    private Request buildPostRequest(String url, RequestBody body, Object tag) {

        Request.Builder builder = new Request.Builder().url(url).post(body);
        
        if (tag != null) {

            builder.tag(tag);
        }

        return builder.build();
    }*/
}
