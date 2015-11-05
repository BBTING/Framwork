package com.lite.face.framwork.rest;

import com.squareup.okhttp.OkHttpClient;

/**
 * Created by whoislcj on 2015/11/3 - 22:23.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class OkHttpPlatform {

    public static OkHttpClient mOkHttpClient;

    @SuppressWarnings("unused")
    private static long CacheSize = 20 * 1024 * 1024;

    //缓存目录
    @SuppressWarnings("unused")
    private static String mCachePath = "/android/app/data/rest/okhttp/";

    private OkHttpPlatform() {

    }

    public static OkHttpClient getInstance() {

        if (mOkHttpClient == null) {

            synchronized (OkHttpPlatform.class) {

                if (mOkHttpClient == null) {

                    mOkHttpClient = new OkHttpClient();
                    //设置缓存
                   /* Cache cache = new Cache(new File(mCachePath), CacheSize);
                    mOkHttpClient.setCache(cache);*/
                }
            }
        }
        return mOkHttpClient;
    }
}
