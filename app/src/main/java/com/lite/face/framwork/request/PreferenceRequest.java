package com.lite.face.framwork.request;

import com.lite.face.framwork.bean.ExtraBean;
import com.lite.face.framwork.inteface.RequestCallback;
import com.lite.face.framwork.inteface.RequestPlugin;

/**
 * Created by whoislcj on 2015/10/28 - 10:01.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
@SuppressWarnings("unused")
public class PreferenceRequest implements RequestPlugin {

    private ExtraBean mExtraBean;
    private RequestCallback mRequestCallback;

    @Override
    public ExtraBean request(ExtraBean extraBean, RequestCallback requestCallback) {
        mRequestCallback = requestCallback;
        return this.request(extraBean);
    }

    @Override
    public ExtraBean request(ExtraBean extraBean) {
        return null;
    }

    @Override
    public boolean cancel(ExtraBean extraBean) {
        return false;
    }
}
