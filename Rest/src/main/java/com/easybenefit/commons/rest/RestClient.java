package com.easybenefit.commons.rest;

import java.util.Map;

/**
 * Created by Gary_Cheung on 15/10/29.
 */
public interface RestClient {

    int NO_NETWORK = -7001;

    int ERROR_PARSER = -7002;

    /**
     * 以QueryString方式调用远程服务
     *
     * @param url        资源的地址
     * @param parameters 参数字典
     * @return
     */
    /*RestResponse doGet(String url, Map<String, Object> parameters);*/

    /**
     * 以Body方式调用远程服务
     *
     * @param url           资源的地址
     * @param body body的参数体
     * @return
     */
/*    RestResponse doGet(String url, Object bodyParameter);

    RestResponse doPost(String url, Map<String, Object> parameters);

    RestResponse doPost(String url, Object bodyParameter);

    RestResponse doPut(String url, Map<String, Object> parameters);

    RestResponse doPut(String url, Object bodyParameter);

    RestResponse doDelete(String url, Map<String, Object> parameters);

    RestResponse doDelete(String url, Object bodyParameter);*/


    RestResponse doGet(String url, Map<String, Object> parameters, Map<String, String> header);

    RestResponse doGet(String url, Object bodyParameter, Map<String, String> header);

    RestResponse doPost(String url, Map<String, Object> parameters, Map<String, String> header);

    RestResponse doPost(String url, Object bodyParameter, Map<String, String> header);

    RestResponse doPut(String url, Map<String, Object> parameters, Map<String, String> header);

    RestResponse doPut(String url, Object bodyParameter, Map<String, String> header);

    RestResponse doDelete(String url, Map<String, Object> parameters, Map<String, String> header);

    RestResponse doDelete(String url, Object bodyParameter, Map<String, String> header);

}
