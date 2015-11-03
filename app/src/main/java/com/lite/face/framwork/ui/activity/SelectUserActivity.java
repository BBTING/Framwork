package com.lite.face.framwork.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lite.face.framwork.R;
import com.lite.face.framwork.bean.ExtraBean;
import com.lite.face.framwork.util.ACache;
import com.lite.face.framwork.ui.base.BaseActivity;
import com.lite.face.framwork.ui.base.CommonAdapter;
import com.lite.face.framwork.util.ViewHolder;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by whoislcj on 2015/11/2 - 17:26.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class SelectUserActivity extends BaseActivity {

    @Bind(R.id.header_center_tv)
    TextView mHeaderCenterTv;
    @Bind(R.id.header_right_tv)
    TextView mHeaderRightTv;
    @Bind(R.id.header_right_iv)
    ImageView mHeaderRightIv;
    @Bind(R.id.user_lv)
    ListView mUserLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        ButterKnife.bind(this);
        initSteps();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initTopbarViews() {
        super.initTopbarViews();
        mHeaderRightIv.setVisibility(View.GONE);
        mHeaderRightTv.setVisibility(View.GONE);
        mHeaderCenterTv.setText("已调查用户");
    }

    @Override
    protected void initOthers() {
        super.initOthers();
        initListView();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.header_left_iv)
    protected void onClickHeaderLeftIv(View view) {
        finish();
    }

    /**
     * ListView初始化
     */
    private void initListView() {
        List<String> users = getStoredUserId();
        final CommonAdapter<String> commonAdapter = new CommonAdapter<String>(this, R.layout.item_selected_layout, users) {
            @Override
            protected void convert(ViewHolder viewHolder, String s) {
                if (!TextUtils.isEmpty(s)) {
                    String[] prefix = s.split("_");
                    if (prefix.length == 2) {
                        viewHolder.setText(R.id.item_tv, "用户ID: " + prefix[1]);
                    }
                }
            }
        };
        mUserLv.setAdapter(commonAdapter);
        mUserLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                String string = commonAdapter.getItem(position);
                if (!TextUtils.isEmpty(string)) {
                    String[] prefix = string.split("_");
                    if (prefix.length == 2) {
                        intent.putExtra("REFRESH", prefix[1]);
                    }
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    /**
     * 获取用户已存储的所有用户ID
     * @return
     */
    private List<String> getStoredUserId() {
        String prefix = getDeviceOpenID();
        String object = ACache.get(getApplicationContext()).getAsString(prefix);
        if (object != null) {
            String[] users = object.split(";;");
            return Arrays.asList(users);
        }
        return null;
    }

    @Override
    protected void doUpdate(ExtraBean extraBean) {

    }

    public String getDeviceOpenID() {
        TelephonyManager telephonyManager = (TelephonyManager) getApplication().getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}
