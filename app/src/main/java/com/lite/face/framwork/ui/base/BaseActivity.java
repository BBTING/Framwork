package com.lite.face.framwork.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import com.lite.face.framwork.bean.ExtraBean;
import com.lite.face.framwork.request.RequestPluginPools;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by whoislcj on 2015/10/28 - 10:05.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public abstract class BaseActivity extends FragmentActivity implements Observer {

    protected final String TAG = getClass().getSimpleName();

    private RequestPluginPools mRequestPluginPools = AppContext.getAppContext().getRequestPoolsInstance();


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRequestPluginPools.deleteObserver(this);
    }

    protected void initSteps() {

        parserIntentExtraData();

        initTopbarViews();

        initOthers();
    }

    protected void initTopbarViews(){

    }

    protected void parserIntentExtraData() {

    }

    protected void initOthers() {
        mRequestPluginPools.addObserver(this);
    }

    protected void onLoadData(ExtraBean extraBean) {
        mRequestPluginPools.doRequest(extraBean);
    }

    @Override
    final public void update(Observable observable, Object data) {
        //// TODO: 2015/10/28 can do some check
        if (data != null && data instanceof ExtraBean) {
            doUpdate((ExtraBean)data);
        }
    }

    protected abstract void doUpdate(ExtraBean extraBean);
}
