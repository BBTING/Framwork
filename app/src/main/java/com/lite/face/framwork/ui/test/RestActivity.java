package com.lite.face.framwork.ui.test;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.easybenefit.commons.rest.RestClientContext;
import com.easybenefit.commons.rest.ServiceCallback;
import com.lite.face.framwork.R;
import com.lite.face.framwork.api.AppversionClient;
import com.lite.face.framwork.api.BannerClient;
import com.lite.face.framwork.api.CaptchaClient;
import com.lite.face.framwork.api.MessageClient;
import com.lite.face.framwork.bean.normal.VersionBean;
import com.lite.face.framwork.rest.HttpRequestClient;
import com.lite.face.framwork.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by whoislcj on 2015/11/3 - 14:24.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class RestActivity extends BaseActivity {

    @Bind(R.id.net_result_tv)
    TextView mNetResultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rest);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    private int mCurrentIndex = 0;

    @SuppressWarnings("unused")
    @OnClick(R.id.submit_btn)
    protected void onClickSubmitBtn(View view) {
        switch (mCurrentIndex++) {
            case 0:
                doGetBannerListRequest();
                break;
            case 1:
                doLatestVersionRequest();
                break;
            case 2:
                doCaptchaRequest();
                break;
            case 3:
                doMessageListRequest();
                break;
            default:
                break;
        }
    }

    /**
     * Banner列表查询测试
     */
    private void doGetBannerListRequest() {

        BannerClient bannerClient = RestClientContext.create(BannerClient.class, HttpRequestClient.getInstance());

        bannerClient.getBannerList(new ServiceCallback<String>() {

            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFailed(String statusCode, String errorMessage) {

            }
        });
    }

    /**
     * 版本信息查询测试
     */
    private void doLatestVersionRequest() {

        AppversionClient appversionClient = RestClientContext.create(AppversionClient.class, HttpRequestClient.getInstance());

        appversionClient.getLatestVersion("1.4", new ServiceCallback<VersionBean>() {

            @Override
            public void onSuccess(VersionBean result) {

            }

            @Override
            public void onFailed(String statusCode, String errorMessage) {

            }
        });
    }

    /**
     * 短信验证码测试
     */
    private void doCaptchaRequest() {

        CaptchaClient captchaClient = RestClientContext.create(CaptchaClient.class, HttpRequestClient.getInstance());

        captchaClient.getCaptcha("13758162327", "4", "abc", new ServiceCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFailed(String statusCode, String errorMessage) {

            }
        });
    }

    /**
     * 消息中心查询
     */
    private void doMessageListRequest() {

        MessageClient messageClient = RestClientContext.create(MessageClient.class, HttpRequestClient.getInstance());

        messageClient.getMessageList(new ServiceCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFailed(String statusCode, String errorMessage) {

            }
        });
    }


}
