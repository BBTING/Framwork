package com.easybenefit.commons.rest;

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


    public static RestResponse createSuccessResponse(String responseBody) {

        RestResponse response = new RestResponse();
        response.statusCode = "1";
        response.responseBody = responseBody;

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
