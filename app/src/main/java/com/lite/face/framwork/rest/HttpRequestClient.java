package com.lite.face.framwork.rest;

import android.util.Log;

import com.easybenefit.commons.rest.RestClient;
import com.easybenefit.commons.rest.RestResponse;
import com.lite.face.framwork.common.util.JsonUtils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.util.List;
import java.util.Map;

/**
 * Created by whoislcj on 2015/11/3 - 22:19.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class HttpRequestClient implements RestClient {

    private final String TAG = HttpRequestClient.class.getSimpleName();

    OkHttpClient mOkHttpClient;

    private static HttpRequestClient mHttpRequestClient;

    public static HttpRequestClient getInstance() {

        if (mHttpRequestClient == null) {

            synchronized (HttpRequestClient.class) {

                if (mHttpRequestClient == null) {

                    mHttpRequestClient = new HttpRequestClient();
                }
            }
        }

        return mHttpRequestClient;
    }

    private HttpRequestClient() {

        mOkHttpClient = OkHttpPlatform.getInstance();
    }

    /**
     * map转String
     *
     * @param map
     * @return
     */
    private String map2String(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value != null) {
                stringBuilder.append(key);
                stringBuilder.append("=");
                stringBuilder.append(value);
                stringBuilder.append("&");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        return stringBuilder.toString();
    }

    /**
     * Get请求
     *
     * @param url
     * @param tag
     * @return
     */
    public RestResponse doGet(String url, Map<String, String> header, String tag) {

        Log.i(TAG, url);

        Call call = mOkHttpClient.newCall(buildGetRequest(url, header, tag));

        try {

            Response response = call.execute();

            return RestResponse.createSuccessResponse(response.body().string(), parserHeader(response));
        } catch (Exception exception) {

            return RestResponse.createFailedResponse(String.valueOf(RestClient.NO_NETWORK), exception.toString());
        }
    }

    /**
     * @param url        资源的地址
     * @param parameters
     * @param header
     * @return
     */
    @Override
    public RestResponse doGet(String url, Map<String, Object> parameters, Map<String, String> header) {

        return this.doGet(url + ((parameters == null || parameters.size() == 0) ? "" : "?" + map2String(parameters)), header, TAG);
    }


    @Override
    public RestResponse doGet(String url, Object body, Map<String, String> header) {

        //????
        String bodyStr = "";
        if (body != null) {
            bodyStr = "/" + JsonUtils.objectToJson(body);
        }

        return this.doGet(url + bodyStr, header, TAG);
    }


    /**
     * Post请求
     * @param url
     * @param bodyStr
     * @param header
     * @param tag
     * @return
     */
    public RestResponse doPost(String url, String bodyStr, Map<String, String> header, String tag) {

        Log.i(TAG, url);

        Request request = buildJsonPostRequest(url, bodyStr, header, tag);

        Call call = mOkHttpClient.newCall(request);

        try {

            Response response = call.execute();

            return RestResponse.createSuccessResponse(response.body().string(), parserHeader(response));
        } catch (Exception exception) {

            return RestResponse.createFailedResponse(String.valueOf(RestClient.NO_NETWORK), exception.toString());
        }
    }

    @Override
    public RestResponse doPost(String url, Map<String, Object> parameters, Map<String, String> header) {

        String bodyStr = null;

        if (parameters != null) {

            bodyStr = JsonUtils.mapToJson(parameters);
        }

        return this.doPost(url, bodyStr, header, null);
    }

    @Override
    public RestResponse doPost(String url, Object bodyParameter, Map<String, String> header) {

        String bodyStr = null;

        if (bodyParameter != null) {

            bodyStr = JsonUtils.objectToJson(bodyParameter);
        }

        return this.doPost(url, bodyStr, header, null);
    }

    @Override
    public RestResponse doPut(String url, Map<String, Object> parameters, Map<String, String> header) {
        return null;
    }

    @Override
    public RestResponse doPut(String url, Object bodyParameter, Map<String, String> header) {
        return null;
    }

    @Override
    public RestResponse doDelete(String url, Map<String, Object> parameters, Map<String, String> header) {
        return null;
    }

    @Override
    public RestResponse doDelete(String url, Object bodyParameter, Map<String, String> header) {
        return null;
    }

    /**
     * 解析头部
     *
     * @param response
     * @return
     */
    private Map<String, List<String>> parserHeader(Response response) {
        if (response == null || response.headers() == null) {
            return null;
        }
        return response.headers().toMultimap();

    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 构建Json请求Request
     *
     * @param url
     * @param param
     * @param tag
     * @return
     */
    private Request buildJsonPostRequest(String url, String body, Map<String, String> header, Object tag) {

        RequestBody requestBody = RequestBody.create(JSON, body);

        Request.Builder requestBuilder = new Request.Builder();

        if (header != null) {

            for (String key : header.keySet()) {

                requestBuilder.addHeader(key, header.get(key).toString());
            }
        }
        requestBuilder.url(url).post(requestBody);

        addHeaderBuilder(requestBuilder);

        if (tag != null) {

            requestBuilder.tag(tag);
        }
        return requestBuilder.build();
    }


    /**
     * 构建表单Post请求Request
     *
     * @param url
     * @param param
     * @param tag
     * @return
     */
    @SuppressWarnings("unused")
    private Request buildFormPostRequest(String url, Map<String, Object> param, Object tag) {

        FormEncodingBuilder builder = new FormEncodingBuilder();

        if (param != null) {

            for (String key : param.keySet()) {

                builder.add(key, param.get(key).toString());
            }
        }
        RequestBody requestBody = builder.build();

        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url).post(requestBody);

        if (tag != null) {

            requestBuilder.tag(tag);
        }
        return requestBuilder.build();
    }

    /**
     * @param url 构建Get请求Request
     * @param tag
     * @return
     */
    private Request buildGetRequest(String url, Map<String, String> header, String tag) {

        Request.Builder builder = new Request.Builder().url(url);

        if (header != null) {
            for (String key : header.keySet()) {
                builder.addHeader(key, header.get(key));
            }
        }

        addHeaderBuilder(builder);

        if (tag != null) {

            builder.tag(tag);
        }
        return builder.build();
    }

    /**
     * 添加Header meta
     *
     * @param requestBuilder
     */
    private void addHeaderBuilder(Request.Builder requestBuilder) {

        requestBuilder.addHeader("Connection", "keep-alive");
    }
}
