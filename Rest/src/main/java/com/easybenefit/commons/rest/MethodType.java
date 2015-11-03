package com.easybenefit.commons.rest;

/**
 * Created by Gary_Cheung on 15/10/30.
 */
public enum MethodType {
    /**
     * 对应于REST中的GET操作，通常用来获取HTTP的服务资源
     */
    GET,
    /**
     * 对应于REST中的PUT操作，通常用来更新/修改HTTP的资源
     */
    PUT,
    /**
     * 对应于REST中的GET操作，通常用来创建新的HTTP的服务资源
     */
    POST,
    /**
     * 对应于REST中的GET操作，通常用来删除对应的HTTP服务资源
     */
    DELETE
}
