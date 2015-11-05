package com.lite.face.framwork.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lite.face.framwork.R;
import com.lite.face.framwork.bean.normal.AsthmaType;
import com.lite.face.framwork.bean.normal.InnerType;
import com.lite.face.framwork.bean.normal.PrimaryType;
import com.lite.face.framwork.bean.normal.SecondaryType;
import com.lite.face.framwork.bean.normal.SubType;
import com.lite.face.framwork.ui.base.BaseActivity;
import com.lite.face.framwork.common.util.ACache;
import com.lite.face.framwork.common.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by whoislcj on 2015/11/2 - 9:14.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class InquiryDetailActivity extends BaseActivity {
    @Bind(R.id.header_center_tv)
    TextView mHeaderCenterTv;
    @Bind(R.id.header_right_tv)
    TextView mHeaderRightTv;
    @Bind(R.id.result_lv)
    ListView mResultLv;
    @Bind(R.id.user_tv)
    EditText mUserTv;
    @Bind(R.id.score_tv)
    TextView mScoreTv;
    @Bind(R.id.header_right_iv)
    ImageView mHeaderRightIv;


    private AsthmaType mAsthmaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_result);
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
        mHeaderRightTv.setText("保存");
        mHeaderCenterTv.setText("详情");
        mHeaderRightTv.setVisibility(View.GONE);
        mHeaderRightIv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void parserIntentExtraData() {
        super.parserIntentExtraData();
        Intent intent = getIntent();
        Object object = intent.getSerializableExtra("Key");
        if (object != null && object instanceof AsthmaType) {//哮喘变量
            mAsthmaType = (AsthmaType) object;
            mResultLv.setAdapter(new ResearchAdater(this, mAsthmaType));
            //计算得分
            mScoreTv.setText(MathUtil.transDouble2Str(calculate(mAsthmaType), 2));
        }
    }

    class ResearchAdater extends BaseAdapter {
        private Context mContext;
        private AsthmaType mAsthmaType;
        private List<PrimaryType> mPrimaryTypes;

        public void setContext(Context context) {
            mContext = context;
        }

        public ResearchAdater(Context context, AsthmaType asthmaType) {
            mContext = context;
            mAsthmaType = asthmaType;
            mPrimaryTypes = mAsthmaType.mPrimaryTypes;
        }

        @Override
        public int getCount() {
            return mAsthmaType == null ? 0 : (mPrimaryTypes == null ? 0 : mPrimaryTypes.size());
        }

        @Override
        public Object getItem(int position) {
            return mPrimaryTypes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_result_layout, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            PrimaryType primaryType = (PrimaryType) getItem(position);
            if (primaryType != null) {
                viewHolder.mTitleTv.setText(primaryType.mTitle);
                viewHolder.mDescriptionTv.setText(buildDescription(primaryType));
            }
            return convertView;
        }

        /**
         * This class contains all butterknife-injected Views & Layouts from layout file 'item_result_layout.xml'
         * for easy to all layout elements.
         *
         * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
         */
        class ViewHolder {
            @Bind(R.id.title_tv)
            TextView mTitleTv;
            @Bind(R.id.description_tv)
            TextView mDescriptionTv;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }


    @SuppressWarnings("unused")
    @OnClick(R.id.header_left_iv)
    protected void onClickHeaderLeftIv(View view) {
        finish();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.header_right_iv)
    protected void onClickHeaderRightIv(View view) {
        Intent intent = new Intent(this, InquiryDatasActivity.class);
        startActivityForResult(intent, 100);
    }

    /**
     * 点击保存数据
     *
     * @param view
     */
    @SuppressWarnings("unused")
    @OnClick(R.id.submit_btn)
    protected void onClickSubmitBtn(View view) {
        String user = mUserTv.getText().toString().trim();
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        //存储
        String suffix = mUserTv.getText().toString().trim();
        loadAsthmaType(suffix, mAsthmaType);
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    private AsthmaType reloadAsthmaType(String user) {
        String prefix = getDeviceOpenID() + "_";
        Object object = ACache.get(getApplicationContext()).getAsObject(prefix + user);
        if (object != null && object instanceof AsthmaType) {
            return (AsthmaType) object;
        }
        return null;
    }

    private void loadAsthmaType(String user, AsthmaType asthmaType) {
        if (asthmaType == null) {
            return;
        }
        String prefix = getDeviceOpenID() + "_";
        if (reloadAsthmaType(user) != null) {
            Toast.makeText(this, "用户" + user + " 调查数据已存在", Toast.LENGTH_SHORT).show();
            return;
        }
        ACache.get(getApplicationContext()).put(prefix + user, asthmaType);
        String prefixUser = ACache.get(getApplicationContext()).getAsString(getDeviceOpenID());
        if (prefixUser != null) {
            String[] prefixUsers = prefixUser.split(";;");
            prefixUser = prefixUser + ";;" + prefix + user;
            for (String _prefix : prefixUsers) {
                if (!TextUtils.isEmpty(_prefix) && _prefix.equals(prefixUser)) {//已存储
                    Toast.makeText(this, "用户 " + user + " 调查数据已存在", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            prefixUser = prefix + user;
        }
        ACache.get(getApplicationContext()).put(getDeviceOpenID(), prefixUser);
    }

    private double calculate(AsthmaType asthmaType) {
        if (asthmaType == null) {
            return 0;
        }
        List<PrimaryType> primaryTypes = asthmaType.mPrimaryTypes;
        //计算分数...
        List<SecondaryType> secondaryTypes0 = new ArrayList<>();
        List<SecondaryType> secondaryTypes1 = new ArrayList<>();
        List<SecondaryType> secondaryTypes2 = new ArrayList<>();
        List<SecondaryType> secondaryTypes3 = new ArrayList<>();
        List<SecondaryType> secondaryTypes4 = new ArrayList<>();
        List<SecondaryType> secondaryTypes11 = new ArrayList<>();
        List<SecondaryType> secondaryTypes21 = new ArrayList<>();
        for (PrimaryType primaryType : primaryTypes) {
            List<SecondaryType> secondaryTypes = primaryType.mSecondTypes;
            for (SecondaryType secondaryType : secondaryTypes) {
                switch (secondaryType.mPriority) {
                    case 0:
                        secondaryTypes0.add(secondaryType);//绿色
                        break;
                    case 1:
                        secondaryTypes1.add(secondaryType);//浅蓝+黄
                        break;
                    case 2:
                        secondaryTypes2.add(secondaryType);//桔
                        break;
                    case 3:
                        secondaryTypes3.add(secondaryType);//紫
                        break;
                    case 4:
                        secondaryTypes4.add(secondaryType);//红
                        break;
                    case 11:
                        secondaryTypes11.add(secondaryType);//浅蓝
                        break;
                    case 21:
                        secondaryTypes21.add(secondaryType);//深蓝
                        break;
                    default:
                        break;
                }
            }
        }
        int priority_0 = calculatePriority0(secondaryTypes0);//绿
        int priority_1 = calculatePriority1(secondaryTypes1);//浅蓝+黄
        int priority_2 = calculatePriority2(secondaryTypes2);//桔
        int priority_3 = calculatePriority3(secondaryTypes3);//紫
        int priority_4 = calculatePriority4(secondaryTypes4);//红
        int priority_11 = calculatePriority1(secondaryTypes11);//浅蓝
        int priority_21 = calculatePriority1(secondaryTypes21);//深蓝

        double sumDo = priority_1 + priority_11;
        if (sumDo == 0 && priority_0 != 0) {
            sumDo -= 5;
        }
        if (sumDo != 0) {
            sumDo += priority_21;
        }
        if (sumDo > 0) {
            sumDo = 0;
        }
        if (priority_3 < sumDo) {
            sumDo = priority_3;
        }
        double sumTmp;
        if (priority_2 != 0) {
            sumTmp = sumDo / 5;
            sumDo = priority_2 + sumTmp;
        }
        if (priority_4 != 0) {
            sumTmp = sumDo / 10;
            sumDo = priority_4 + sumTmp;
        }
        return 100 + sumDo;
    }

    /**
     * 计算分数
     *
     * @param secondaryTypes
     * @return
     */
    private int calculatePriority(List<SecondaryType> secondaryTypes) {
        int count = 0;
        if (secondaryTypes != null && secondaryTypes.size() > 0) {
            for (SecondaryType secondaryType : secondaryTypes) {
                if (secondaryType != null) {
                    List<SubType> subTypes = secondaryType.mSubTypes;
                    if (subTypes != null) {
                        for (SubType subType : subTypes) {
                            if (subType != null && subType.mSelected) {
                                count += subType.mFract;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * 计算刺激性因素
     *
     * @param secondaryTypes
     * @return
     */
    private int calculatePriority0(List<SecondaryType> secondaryTypes) {
        int count = calculatePriority(secondaryTypes);
        //满分减5
        if (count == 0) {
            count = -5;
        }
        return count;
    }

    /**
     * 计算1优先级分数
     *
     * @param secondaryTypes
     * @return
     */
    private int calculatePriority1(List<SecondaryType> secondaryTypes) {
        return calculatePriority(secondaryTypes);
    }

    /**
     * 计算2优先级分数
     *
     * @param secondaryTypes
     * @return
     */
    private int calculatePriority2(List<SecondaryType> secondaryTypes) {
        int count = calculatePriority(secondaryTypes);
        count = Math.max(count, -30);
        return count;
    }

    /**
     * 计算3优先级分数
     *
     * @param secondaryTypes
     * @return
     */
    private int calculatePriority3(List<SecondaryType> secondaryTypes) {
        return calculatePriority(secondaryTypes);
    }

    /**
     * 计算4优先级分数
     *
     * @param secondaryTypes
     * @return
     */
    private int calculatePriority4(List<SecondaryType> secondaryTypes) {
        int count = 0;
        if (secondaryTypes != null && secondaryTypes.size() > 0) {
            for (SecondaryType secondaryType : secondaryTypes) {
                if (secondaryType != null) {
                    List<SubType> subTypes = secondaryType.mSubTypes;
                    for (SubType subType : subTypes) {
                        if (subType.mSelected) {
                            if (count > subType.mFract) {
                                count = subType.mFract;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * 生成描述字段
     *
     * @param primaryType
     * @return
     */
    private String buildDescription(PrimaryType primaryType) {
        if (primaryType == null) {
            return null;
        }
        List<SecondaryType> secondaryTypes = primaryType.mSecondTypes;
        if (secondaryTypes == null || secondaryTypes.size() <= 0) {
            return null;
        }
        StringBuilder buffer = new StringBuilder();
        for (SecondaryType secondaryType : secondaryTypes) {
            buffer.append("[");//
            buffer.append(secondaryType.mTitle);
            buffer.append("] ");//[行动受限(单选)]
            List<SubType> subTypes = secondaryType.mSubTypes;
            if (subTypes != null && subTypes.size() >= 0) {
                buffer.append("[");//[有 无]
                for (SubType subType : subTypes) {
                    if (subType.mSelected && subType.mTitle != null) {
                        buffer.append(subType.mTitle);
                        buffer.append(" ");
                    }
                }
                buffer.append("] ");
                for (SubType subType : subTypes) {
                    if (subType.mSelected && subType.mInnerTypes != null) {
                        buffer.append("[");
                        for (InnerType innerType : subType.mInnerTypes) {
                            if (innerType != null && innerType.mSelected && innerType.mTitle != null) {
                                buffer.append(innerType.mTitle);
                                buffer.append(" ");
                            }
                        }
                        buffer.append("]");
                    }
                }
            }
            if (secondaryTypes.get(secondaryTypes.size() - 1) != secondaryType && secondaryTypes.size() > 1) {
                buffer.append("\r\n");
            }
        }
        return buffer.toString();
    }

    public String getDeviceOpenID() {
        TelephonyManager tel = (TelephonyManager) getApplication().getSystemService(Context.TELEPHONY_SERVICE);
        return tel.getDeviceId();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String string = data.getStringExtra("REFRESH");
            if (!TextUtils.isEmpty(string)) {
                mUserTv.setText(string);
                mUserTv.setSelection(string.length());
                AsthmaType object = reloadAsthmaType(string);
                if (object != null) {
                    mAsthmaType = object;
                    mResultLv.setAdapter(new ResearchAdater(this, mAsthmaType));
                    mScoreTv.setText(MathUtil.transDouble2Str(calculate(mAsthmaType), 2));
                }
            }
        }
    }
}
