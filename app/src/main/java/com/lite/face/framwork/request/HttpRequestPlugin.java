package com.lite.face.framwork.request;

import com.easybenefit.commons.rest.impl.DefaultHttpClient;
import com.lite.face.framwork.bean.ExtraBean;
import com.lite.face.framwork.bean.JsonUtil;
import com.lite.face.framwork.bean.request.RequestBean;
import com.lite.face.framwork.inteface.RequestCallback;
import com.lite.face.framwork.inteface.RequestPlugin;
import com.squareup.okhttp.Request;

import java.util.HashMap;

/**
 * Created by whoislcj on 2015/10/28 - 10:01.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
@SuppressWarnings("unused")
public class HttpRequestPlugin implements RequestPlugin {

    private ExtraBean mExtraBean;
    private RequestCallback mRequestCallback;

    private HashMap<String, ExtraBean> mExtraBeanHashMap = new HashMap<>();

    @Override
    public ExtraBean request(ExtraBean extraBean, RequestCallback requestCallback) {
        mRequestCallback = requestCallback;
        return this.request(extraBean);
    }

    @Override
    public ExtraBean request(final ExtraBean extraBean) {
        mExtraBeanHashMap.put(extraBean.mRequestObj.mUrl, extraBean);
        switch (extraBean.mRequestObj.mMethod.toUpperCase()) {
            case RequestBean.POST:
                doPostRequest(extraBean);
                break;
            case RequestBean.GET:
                doGetRequest(extraBean);
                break;
        }
        return null;
    }

    /**
     * Get请求
     * @param extraBean
     */
    private void doGetRequest(final ExtraBean extraBean) {
        DefaultHttpClient.get(extraBean.mRequestObj.mUrl, new DefaultHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                extraBean.mResponseObj = e;
                if (mRequestCallback != null) {
                    mRequestCallback.callback(extraBean);
                }
            }

            @Override
            public void onResponse(String response) {
                Class mRetClass = extraBean.mRetClass;
                Object obj = JsonUtil.parserJsonToBean(mRetClass, response);
                extraBean.mResponseObj = obj;
                if (mRequestCallback != null) {
                    mRequestCallback.callback(extraBean);
                }
            }
        });
    }

    /**
     * Post请求
     * @param extraBean
     */
    private void doPostRequest(final ExtraBean extraBean) {
        DefaultHttpClient.post(extraBean.mRequestObj.mUrl, extraBean.mRequestObj.mParams, new DefaultHttpClient.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                extraBean.mResponseObj = e;
                if (mRequestCallback != null) {
                    mRequestCallback.callback(extraBean);
                }
            }

            @Override
            public void onResponse(String response) {
                Class mRetClass = extraBean.mRetClass;
                Object obj = JsonUtil.parserJsonToBean(mRetClass, response);
                extraBean.mResponseObj = obj;
                if (mRequestCallback != null) {
                    mRequestCallback.callback(extraBean);
                }
            }
        });
    }

    @Override
    public boolean cancel(ExtraBean extraBean) {
        return false;
    }


}
