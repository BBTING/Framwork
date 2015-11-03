package com.lite.face.framwork.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.easybenefit.commons.rest.RestClient;
import com.easybenefit.commons.rest.RestClientContext;
import com.easybenefit.commons.rest.ServiceCallback;
import com.lite.face.framwork.R;
import com.lite.face.framwork.bean.ExtraBean;
import com.lite.face.framwork.ui.base.BaseActivity;

/**
 * Created by whoislcj on 2015/11/3 - 14:24.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class RestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rf);

        doRest();
    }

    public void doRest() {

        RestClient restClient = RestClientContext.create(RestClient.class);

        restClient.doGet("http://www.baidu.com", "android");
    }

    private ServiceCallback<String> mServiceCallback = new ServiceCallback<String>() {
        @Override
        public void onFailed(String statusCode, String errorMessage) {
            Log.i("0", statusCode + "");
        }

        @Override
        public void onSuccess(String result) {
            Log.i("0", result);
        }
    };

    @Override
    protected void doUpdate(ExtraBean extraBean) {

    }
}
