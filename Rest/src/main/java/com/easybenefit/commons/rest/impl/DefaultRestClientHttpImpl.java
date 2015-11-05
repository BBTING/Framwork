package com.easybenefit.commons.rest.impl;

import com.easybenefit.commons.rest.RestClient;
import com.easybenefit.commons.rest.RestResponse;

import java.util.Map;

/**
 * Created by whoislcj on 2015/10/28 - 14:05.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class DefaultRestClientHttpImpl implements RestClient{

    @Override
    public RestResponse doGet(String url, Map<String, Object> parameters, Map<String, String> header) {
        return null;
    }

    @Override
    public RestResponse doGet(String url, Object bodyParameter, Map<String, String> header) {
        return null;
    }

    @Override
    public RestResponse doPost(String url, Map<String, Object> parameters, Map<String, String> header) {
        return null;
    }

    @Override
    public RestResponse doPost(String url, Object bodyParameter, Map<String, String> header) {
        return null;
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
}

