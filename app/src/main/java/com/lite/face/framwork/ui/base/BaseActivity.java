package com.lite.face.framwork.ui.base;

import android.support.v4.app.FragmentActivity;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by whoislcj on 2015/10/28 - 10:05.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public abstract class BaseActivity extends FragmentActivity implements Observer {

    protected final String TAG = getClass().getSimpleName() + "_";


    protected void initSteps() {

        parserIntentExtraData();

        initTopbarViews();

        initOthers();
    }

    protected void initTopbarViews() {

    }

    protected void parserIntentExtraData() {

    }

    protected void initOthers() {

    }


    @Override
    final public void update(Observable observable, Object data) {
        //// TODO: 2015/10/28 can do some check

    }

}
