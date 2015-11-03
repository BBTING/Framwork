package com.easybenefit.commons.rest.impl.delegate;

import android.os.Handler;
import android.os.Looper;

import com.easybenefit.commons.rest.ServiceCallback;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by whoislcj on 2015/11/3 - 18:08.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class BaseRequest {


    private Handler mHandler;

    protected OkHttpClient mOkHttpClient;

    public BaseRequest(OkHttpClient okHttpClient) {

        mOkHttpClient = okHttpClient;

        mHandler = new Handler(Looper.getMainLooper());
    }

    protected void deliveryRequest(ServiceCallback callback, Request request) {

        if (callback == null || mOkHttpClient == null) {

            return;
        }
        final ServiceCallback resCallBack = callback;

        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(final Request request, final IOException e) {

                postFailedResultCallback(request, e, resCallBack);
            }

            @Override
            public void onResponse(final Response response) {

                try {

                    final String string = response.body().string();

                    postSuccessResultCallback(string, resCallBack);
                } catch (IOException e) {

                    postFailedResultCallback(response.request(), e, resCallBack);
                } catch (com.google.gson.JsonParseException e) {//Json解析的错误

                    postFailedResultCallback(response.request(), e, resCallBack);
                }

            }
        });
    }

    private void postFailedResultCallback(final Request request, final Exception e, final ServiceCallback callback) {

        mHandler.post(new Runnable() {

            @Override
            public void run() {

            }
        });

    }

    private void postSuccessResultCallback(final String string, final ServiceCallback callback) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(string);
                }
            }
        });
    }

}
