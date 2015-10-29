package com.lite.face.framwork.ui.base;

import android.app.Application;

import com.lite.face.framwork.request.CacheRequestPlugin;
import com.lite.face.framwork.request.FileRequestPlugin;
import com.lite.face.framwork.request.HttpRequestPlugin;
import com.lite.face.framwork.request.PreferenceRequest;
import com.lite.face.framwork.request.RequestPluginPools;
import com.lite.face.framwork.request.RequestPolicy;

/**
 * Created by whoislcj on 2015/10/28 - 11:32.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class AppContext extends Application {

    private static AppContext mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
    }

    public static AppContext getAppContext() {
        return mAppContext;
    }

    /**
     * 添加处理策略
     */
    private void initRequestAgentPools() {
        if (mRequestPools != null) {
            mRequestPools.addRequestPlugin(RequestPolicy.FROM_FILE, new FileRequestPlugin());
            mRequestPools.addRequestPlugin(RequestPolicy.FROM_PREFERENCE, new PreferenceRequest());
            mRequestPools.addRequestPlugin(RequestPolicy.FROM_SERVER, new HttpRequestPlugin());
            mRequestPools.addRequestPlugin(RequestPolicy.FROM_CACHE, new CacheRequestPlugin());
        }
    }

    private RequestPluginPools mRequestPools;

    public RequestPluginPools getRequestPoolsInstance() {
        if (mRequestPools == null) {
            synchronized (RequestPluginPools.class) {
                if (mRequestPools == null) {
                    mRequestPools = new RequestPluginPools();
                    initRequestAgentPools();
                }
            }
        }
        return mRequestPools;
    }

}
