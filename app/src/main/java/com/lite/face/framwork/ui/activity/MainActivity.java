package com.lite.face.framwork.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lite.face.framwork.R;
import com.lite.face.framwork.bean.ExtraBean;
import com.lite.face.framwork.bean.request.RequestBean;
import com.lite.face.framwork.bean.response.VersionBean;
import com.lite.face.framwork.request.RequestPolicy;
import com.lite.face.framwork.ui.base.AppConstants;
import com.lite.face.framwork.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initSteps();
    }

    @Override
    protected void onResume() {
        super.onResume();
        doMessagesRequest();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.hello_tv)
    @SuppressWarnings("unused")
    protected void onClickHelloTv(View view) {
        Intent intent = new Intent(this, RxAndroidActivity.class);
        startActivityForResult(intent, 100);
    }


    private void doMessagesRequest() {
        ExtraBean extraBean = new ExtraBean(AppConstants.REQUERYMESSAGECODE, RequestPolicy.FROM_DATABASE | RequestPolicy.FROM_SERVER, VersionBean.class);
        extraBean.mRequestObj = new RequestBean("http://172.16.100.29:8080/yb-api/message_center/list", RequestBean.GET);
        onLoadData(extraBean);
    }

    @Override
    protected void doUpdate(ExtraBean extraBean) {
        if (extraBean != null && extraBean.mRequestCode != null) {
            Object obj = extraBean.mResponseObj;
            switch (extraBean.mRequestCode) {
                case AppConstants.REQUERYMESSAGECODE:
                    if (obj != null && obj instanceof VersionBean) {
                        doMessageResponseHandler((VersionBean) obj);
                    }
                    break;
                case 2:
                    break;
            }
        }
    }

    private void doMessageResponseHandler(VersionBean versionBean) {
        Log.i(TAG, versionBean.toString());
    }

}
