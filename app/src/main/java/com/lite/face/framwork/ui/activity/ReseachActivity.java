package com.lite.face.framwork.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.easybenefit.commons.rest.RestClientContext;
import com.easybenefit.commons.rest.ServiceCallback;
import com.lite.face.framwork.R;
import com.lite.face.framwork.bean.ExtraBean;
import com.lite.face.framwork.bean.normal.AsthmaFactory;
import com.lite.face.framwork.bean.normal.AsthmaType;
import com.lite.face.framwork.bean.normal.InnerType;
import com.lite.face.framwork.bean.normal.PrimaryType;
import com.lite.face.framwork.bean.normal.SecondaryType;
import com.lite.face.framwork.bean.normal.SubType;
import com.lite.face.framwork.inteface.ClientService;
import com.lite.face.framwork.ui.base.BaseActivity;
import com.lite.face.framwork.ui.widget.FixedGridView;
import com.lite.face.framwork.ui.widget.InnerPopupWindow;
import com.lite.face.framwork.util.CommonAdapter;
import com.lite.face.framwork.util.ViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReseachActivity extends BaseActivity {


    @Bind(R.id.type_lv)
    ListView mPrimaryLv;
    @Bind(R.id.type_tv0)
    TextView mSecondaryTv0;
    @Bind(R.id.type_gv0)
    FixedGridView mSecondaryGv0;
    @Bind(R.id.type_tv1)
    TextView mSecondaryTv1;
    @Bind(R.id.type_gv1)
    FixedGridView mSecondaryGv1;
    @Bind(R.id.header_left_iv)
    ImageView mHeaderLeftIv;

    private long mExitTime;

    private static boolean DEBUG = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rf);
        ButterKnife.bind(this);
        initSteps();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (DEBUG) {
            doTest();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initTopbarViews() {
        super.initTopbarViews();
        mHeaderLeftIv.setVisibility(View.GONE);
        mWidth = getDisplayMetrics().widthPixels / 3 - 10;
        initPrimaryTitlesLv();
        mPrimaryLv.performItemClick(mPrimaryLv.getChildAt(0), 0, -1);
    }

    @Override
    protected void initOthers() {
        super.initOthers();

    }

    private void doTest() {

        ClientService userService = RestClientContext.create(ClientService.class);

        userService.doSomething("key", mServiceCallback);
    }

    private ServiceCallback<String> mServiceCallback = new ServiceCallback<String>() {
        @Override
        public void onFailed(String statusCode, String errorMessage) {
            Log.i(TAG, errorMessage + "");
        }

        @Override
        public void onSuccess(String response) {
            Log.i(TAG, response + "");
        }
    };

    private AsthmaType mAsthmaType;

    private void initPrimaryTitlesLv() {
        AsthmaFactory factory = new AsthmaFactory();
        factory.initAsthmaData();
        mAsthmaType = factory.asthmaType;
        final CommonAdapter<PrimaryType> commonAdapter = new CommonAdapter<PrimaryType>(this, R.layout.item_type_layout, mAsthmaType.mPrimaryTypes) {
            @Override
            protected void convert(ViewHolder viewHolder, PrimaryType primaryType) {
                viewHolder.setText(R.id.type_tv, primaryType.mTitle);
            }

            @Override
            protected void invokSlectedView(ViewHolder viewHolder, boolean selected) {
                viewHolder.getConvertView().setBackgroundColor(selected ? getResources().getColor(R.color.color_pressed) : getResources().getColor(R.color.color_unpressed));
                viewHolder.setViewVisiable(R.id.tag_vi, selected ? View.VISIBLE : View.INVISIBLE);
            }
        };
        mPrimaryLv.setAdapter(commonAdapter);
        mPrimaryLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                commonAdapter.setSelectedPostion(position);
                for (PrimaryType primaryType : mAsthmaType.mPrimaryTypes) {
                    primaryType.mSelected = false;
                }
                mAsthmaType.mPrimaryTypes.get(position).mSelected = true;
                resetSecondaryGridView(position);
            }
        });
    }


    @Override
    protected void doUpdate(ExtraBean extraBean) {

    }

    @SuppressWarnings("unused")
    @OnClick(R.id.header_right_tv)
    protected void onClickSubmitBtn(View view) {
        if (mAsthmaType != null && mAsthmaType.mPrimaryTypes != null) {
            for (PrimaryType primaryType : mAsthmaType.mPrimaryTypes) {
                List<SecondaryType> secondaryTypes = primaryType.mSecondTypes;
                for (SecondaryType secondaryType : secondaryTypes) {
                    List<SubType> subTypes = secondaryType.mSubTypes;
                    boolean selected = false;
                    for (SubType subType : subTypes) {
                        if (!selected) {
                            selected = subType.mSelected;
                        }
                    }
                    if (!selected) {
                        Toast.makeText(this, secondaryType.mTitle + "未选择", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("Key", mAsthmaType);
        startActivityForResult(intent, 100);
    }

    private void resetSecondaryGridView(int index) {
        PrimaryType primaryType = mAsthmaType.mPrimaryTypes.get(index);
        mSecondaryTv0.setText("");
        mSecondaryTv1.setText("");
        if (primaryType != null && primaryType.mSecondTypes != null) {
            if (primaryType.mSecondTypes.size() == 1) {
                mSecondaryTv0.setText(primaryType.mSecondTypes.get(0).mTitle);
            } else if (primaryType.mSecondTypes.size() == 2) {
                mSecondaryTv0.setText(primaryType.mSecondTypes.get(0).mTitle);
                mSecondaryTv1.setText(primaryType.mSecondTypes.get(1).mTitle);
            }
            resetGridViewRf(primaryType.mSecondTypes);
        }

    }


    private void resetGridViewRf(List<SecondaryType> list) {
        if (list != null) {
            SecondaryType secondaryType;
            if (list.size() == 1) {
                secondaryType = list.get(0);
                resetGridViewRf(mSecondaryGv0, secondaryType.mSubTypes, secondaryType.mMulti);
                resetGridViewRf(mSecondaryGv1, null, secondaryType.mMulti);
            }
            if (list.size() == 2) {
                secondaryType = list.get(1);
                resetGridViewRf(mSecondaryGv0, list.get(0).mSubTypes, secondaryType.mMulti);
                resetGridViewRf(mSecondaryGv1, list.get(1).mSubTypes, secondaryType.mMulti);
            }
        }
    }

    private void resetGridViewRf(GridView gridView, final List<SubType> list, final boolean isMulti) {
        final GridViewAdapter adapter = new GridViewAdapter(this, list, mWidth, isMulti);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.onClickItem(position);
            }
        });
    }

    /**
     * 初始化运动受限GridView
     */
    private int mWidth;

    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * GridView适配器
     */
    private class GridViewAdapter extends CommonAdapter<SubType> {

        private int mGridViewWidth = 100;

        private boolean mMultiSelect;

        List<SubType> mSubTypes;

        public void onClickItem(int index) {
            for (int i = 0; i < mSubTypes.size(); i++) {
                if (i != index) {
                    if (!mMultiSelect) {
                        mSubTypes.get(i).mSelected = false;
                    }
                } else {
                    if (mSubTypes.get(i).mTitle.equals("有") && mSubTypes.get(i).mInnerTypes != null) {
                        if (!mSubTypes.get(i).mSelected) {
                            mSubTypes.get(i).mSelected = true;
                        }
                        final SubType subType = mSubTypes.get(i);
                        InnerPopupWindow bankPopupWindow = new InnerPopupWindow(ReseachActivity.this);
                        bankPopupWindow.initListView(mSubTypes.get(i).mInnerTypes, getDisplayMetrics().widthPixels - 20);
                        bankPopupWindow.setInnerCallback(new InnerPopupWindow.InnerCallback() {
                            @Override
                            public void callback(boolean confirm) {
                                if (!confirm) {//取消
                                    subType.mSelected = false;
                                    List<InnerType> innerTypes = subType.mInnerTypes;
                                    if (innerTypes != null) {
                                        for (InnerType innerType : innerTypes) {
                                            innerType.mSelected = false;
                                        }
                                    }
                                } else {
                                    List<InnerType> innerTypes = subType.mInnerTypes;
                                    boolean selected = false;
                                    if (innerTypes != null) {
                                        for (InnerType innerType : innerTypes) {
                                            if (innerType.mSelected) {
                                                selected = true;
                                            }
                                        }
                                    }
                                    subType.mSelected = selected;
                                }
                                GridViewAdapter.this.notifyDataSetChanged();
                            }
                        });
                        bankPopupWindow.showAtLocation(mSecondaryTv0, Gravity.BOTTOM, 0, 0);
                    } else {
                        mSubTypes.get(i).mSelected = !mSubTypes.get(i).mSelected;
                        List<InnerType> innerTypes = mSubTypes.get(i).mInnerTypes;
                        if (innerTypes != null) {
                            for (InnerType innerType : innerTypes) {
                                innerType.mSelected = false;
                            }
                        }
                    }
                }
            }
            notifyDataSetChanged();
        }

        public GridViewAdapter(Context context, List<SubType> datas, int width, boolean isMulti) {
            super(context, R.layout.item_gridview_layout, datas);
            mGridViewWidth = width;
            mMultiSelect = isMulti;
            mSubTypes = datas;
        }

        @Override
        protected void convert(ViewHolder viewHolder, SubType s) {
            viewHolder.setText(R.id.item_tv, s.mTitle);
            if (s.mSelected) {
                viewHolder.getConvertView().setSelected(true);
                viewHolder.setViewVisiable(R.id.selecte_iv, View.VISIBLE);
            } else {
                viewHolder.getConvertView().setSelected(false);
                viewHolder.setViewVisiable(R.id.selecte_iv, View.GONE);
            }
            viewHolder.getConvertView().setLayoutParams(new GridView.LayoutParams(mGridViewWidth, (int) (mGridViewWidth * 1.25)));
        }
    }

    /**
     * 点击后退键2次退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2500) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        //拦截MENU按钮点击事件，让他无任何操作
        return keyCode == KeyEvent.KEYCODE_MENU || super.onKeyDown(keyCode, event);
    }

}
