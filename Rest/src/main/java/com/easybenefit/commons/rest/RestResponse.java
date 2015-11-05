package com.easybenefit.commons.rest;

import java.util.List;
import java.util.Map;

/**
 * Created by Gary_Cheung on 15/10/30.
 */
public class RestResponse {

    /**
     * 状态码
     */
    public String statusCode;

    /**
     * 异常消息，仅在statusCode !=1的时候出现
     */
    public String errorMessage;

    /**
     * 业务数据响应结果
     */
    public String responseBody;

    /**
     * 响应头部
     */
    public Map<String, List<String>> headers;


    public static RestResponse createSuccessResponse(String responseBody) {

        return createSuccessResponse(responseBody, null);
    }

    public static RestResponse createSuccessResponse(String responseBody, Map<String, List<String>> header) {

        RestResponse response = new RestResponse();
        response.statusCode = "1";
        response.responseBody = responseBody;
        response.headers = header;

        return response;
    }


    public static RestResponse createFailedResponse(String statusCode ,String errorMessage) {

        RestResponse response = new RestResponse();
        response.statusCode = statusCode;
        response.errorMessage = errorMessage;

        return response;
    }

    public static class AbortRestResponse extends RestResponse{

        private static volatile AbortRestResponse instance = new AbortRestResponse();

        public static AbortRestResponse make() {

            return instance;
        }
    }

}
