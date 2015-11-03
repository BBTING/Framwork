package com.easybenefit.commons.rest.interceptor;

import java.util.Map;

/**
 * Created by Gary_Cheung on 15/10/31.
 */
public interface RequestInterceptor {

    boolean onPreExecute(String url, final Map<String, String> heads);

    void onPostExecute();
}
