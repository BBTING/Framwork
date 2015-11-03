package com.easybenefit.commons.rest.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.easybenefit.commons.rest.RestClient;
import com.easybenefit.commons.rest.RestClientContext;
import com.easybenefit.commons.rest.RestResponse;
import com.easybenefit.commons.rest.ServiceCallback;
import com.easybenefit.commons.rest.impl.delegate.GetRequest;
import com.easybenefit.commons.rest.impl.delegate.ResultCallback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.util.Map;

/**
 * Created by Gary_Cheung on 15/10/29.
 */
public class RestClientHttpImpl implements RestClient {

/*    @Override
    public RestResponse doGet(String url, Object bodyParameter, ServiceCallback resultCallback) {

        new GetRequest(new OkHttpClient()).get(url, resultCallback);

        return null;
    }*/

    @Override
    public RestResponse doGet(String url, Map<String, Object> parameters) {

        Log.e(RestClientContext.TAG, "\n");
        Log.e(RestClientContext.TAG, "execute method : doGet -- " + url);
        Log.e(RestClientContext.TAG, "Parameters : " + JSON.toJSONString(parameters));

        return RestResponse.createSuccessResponse("[{'name':'Gary','hospital':'浙二医院呼吸科','address':'西湖大道'},{'name':'Tommy','hospital':'北京医院呼吸科','address':'东直门'}]");
    }

    @Override
    public RestResponse doGet(String url, Object bodyParameter) {

        Log.e(RestClientContext.TAG, "\n");
        Log.e(RestClientContext.TAG, "execute method : doGet -- " + url);
        Log.e(RestClientContext.TAG, "Parameters : " + JSON.toJSONString(bodyParameter));

        return RestResponse.createSuccessResponse("[{'name':'Gary','hospital':'浙二医院呼吸科','address':'西湖大道'},{'name':'Tommy','hospital':'北京医院呼吸科','address':'东直门'}]");
    }

    @Override
    public RestResponse doPost(String url, Map<String, Object> parameters) {

        Log.e(RestClientContext.TAG, "\n");
        Log.e(RestClientContext.TAG, "execute method : doPost -- " + url);
        Log.e(RestClientContext.TAG, "Parameters : " + JSON.toJSONString(parameters));

        return RestResponse.createFailedResponse("100", "User not found!");
    }

    @Override
    public RestResponse doPost(String url, Object bodyParameter) {

        Log.e(RestClientContext.TAG, "\n");
        Log.e(RestClientContext.TAG, "execute method : doPost -- " + url);
        Log.e(RestClientContext.TAG, "Parameters : " + JSON.toJSONString(bodyParameter));

        return RestResponse.createFailedResponse("100", "User not found!");
    }

    @Override
    public RestResponse doPut(String url, Map<String, Object> parameters) {

        Log.e(RestClientContext.TAG, "\n");
        Log.e(RestClientContext.TAG, "execute method : doPut -- " + url);
        Log.e(RestClientContext.TAG, "Parameters : " + JSON.toJSONString(parameters));

        return RestResponse.createFailedResponse("100", "User not found!");
    }

    @Override
    public RestResponse doPut(String url, Object bodyParameter) {

        Log.e(RestClientContext.TAG, "\n");
        Log.e(RestClientContext.TAG, "execute method : doPut -- " + url);
        Log.e(RestClientContext.TAG, "Parameters : " + JSON.toJSONString(bodyParameter));

        return RestResponse.createFailedResponse("100", "User not found!");
    }

    @Override
    public RestResponse doDelete(String url, Map<String, Object> parameters) {

        Log.e(RestClientContext.TAG, "\n");
        Log.e(RestClientContext.TAG, "execute method : doDelete -- " + url);
        Log.e(RestClientContext.TAG, "Parameters : " + JSON.toJSONString(parameters));

        return RestResponse.createSuccessResponse("true");
    }

    @Override
    public RestResponse doDelete(String url, Object bodyParameter) {

        Log.e(RestClientContext.TAG, "\n");
        Log.e(RestClientContext.TAG, "execute method : doDelete -- " + url);
        Log.e(RestClientContext.TAG, "Parameters : " + JSON.toJSONString(bodyParameter));

        return RestResponse.createSuccessResponse("true");
    }
}
