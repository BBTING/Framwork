package com.lite.face.framwork.ui.base;

import android.app.Application;

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

}
