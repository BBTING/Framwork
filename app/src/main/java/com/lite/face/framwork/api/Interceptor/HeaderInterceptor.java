package com.lite.face.framwork.api.Interceptor;

import com.easybenefit.commons.rest.interceptor.RequestInterceptor;
import com.lite.face.framwork.common.util.ConfigManager;

import java.util.Map;

/**
 * Created by whoislcj on 2015/11/4 - 19:55.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description: 添加头部信息
 */
public class HeaderInterceptor implements RequestInterceptor {

    @Override
    public boolean onPreExecute(String url, Map<String, String> heads) {

        if (heads != null) {
            String currentTime = String.valueOf(System.currentTimeMillis());
            heads.put("Connection", "keep-alive");
            heads.put("clientKey", "p");
            heads.put("deviceKey", "Android");
            heads.put("timestamp", currentTime);
            heads.put("version", "1.4");
            heads.put("kidfv", ConfigManager.getDeviceId());
        }
        return true;
    }

    @Override
    public void onPostExecute() {

    }
}
