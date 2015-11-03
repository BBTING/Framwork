package com.lite.face.framwork.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lite.face.framwork.R;
import com.lite.face.framwork.bean.ExtraBean;
import com.lite.face.framwork.bean.request.RequestBean;
import com.lite.face.framwork.bean.normal.AsthmaFactory;
import com.lite.face.framwork.bean.normal.VersionBean;
import com.lite.face.framwork.request.RequestPolicy;
import com.lite.face.framwork.ui.base.AppConstants;
import com.lite.face.framwork.ui.base.BaseActivity;
import com.lite.face.framwork.ui.base.CommonAdapter;
import com.lite.face.framwork.util.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class MainActivity extends BaseActivity {

    @Bind(R.id.header_center_tv)
    TextView mHeaderCenterTv;
    @Bind(R.id.move_limit_gv)
    GridView mMoveLimitGv;
    @Bind(R.id.peak_meter_gv)
    GridView mPeakMeterGv;
    @Bind(R.id.abnormal_mood_gv)
    GridView mAbnormalMoodGv;

    //行动受限
    private final String[] MOVELIMITS = new String[]{"无", "步行活动气短，讲话常有中继（中度）", "步行或上楼气短（轻度）",
            "稍事活动气短，讲话常有中断（中度）", "休息时感气短，端坐呼吸，只能发单字表达（重度）", "不能讲话，嗜睡或意识模糊（危重）"};
    //情绪异常
    private final String[] ABNORMALMOOD = new String[]{"无", "有焦虑(轻度)", "时有焦虑(中度)", "常有焦虑和烦躁(重度)", "嗜睡或意识模糊(危重)"};//Abnormal mood
    //峰值仪
    private final String[] PEAKMETER = new String[]{"PEF值大于预计值 或个人最侍值的80%", "PEF值在预计值或个人最佳值的60%-80%之间", "PEF值小于预计值或个人最佳值的60%"};
    //呼吸问题
    private final String[] BREATHING = new String[]{"无", "哮喘", "咳嗽", "胸闷", "气急"};
    //夜间症状
    private final String[] SYMPTOM = new String[]{"无", "憋醒"};
    //睡眠
    private final String[] SLEEP = new String[]{">8小时", "6~8小时", "4~6小时", "<4小时"};
    //大便
    private final String[] SHIT = new String[]{"正常", "便秘", "腹泻"};
    //该用药
    private final String[] THEDRUGUSE = new String[]{"完成", "完成部分", "完全未完成"};
    //多用药
    private final String[] MULTIDRUGUSE = new String[]{"无", "有(原历，药名，药量)"};
    //吸烟或二手烟
    private final String[] SMOOKING = new String[]{"无", "0~10支", "10~20支", "20~30支", "30+支"};
    //辛辣刺激食品
    private final String[] SPICYFOOD = new String[]{"无", "有(多选:辣椒, 花椒...)"};
    //海鲜
    private final String[] SEAFOOD = new String[]{"无", "有(多选:虾，蟹，鱼...)"};
    //易过敏食品
    private final String[] ALLERGYFOOD = new String[]{"无", "有(多选:鸡蛋，豆类，牛奶..."};
    //宠物
    private final String[] PET = new String[]{"无", "有(多选：猫，狗)"};
    //特殊药
    private final String[] DRUGS = new String[]{"无", "有(多选：抗生素，阿司匹林...)"};
    //散步慢走
    private final String[] WALK = new String[]{"0~5000", "5000~10000", "10000~15000"};
    //呼吸操
    private final String[] BREAKINGEXERCISES = new String[]{"1次", "2次", "3次+"};
    //自我感觉
    private final String[] SELFFEELING = new String[]{"很好", "一般", "不好"};

    @Bind(R.id.breathing_gv)
    GridView mBreathingGv;
    @Bind(R.id.symptom_gv)
    GridView mSymptomGv;
    @Bind(R.id.sleeping_gv)
    GridView mSleepingGv;
    @Bind(R.id.shit_gv)
    GridView mShitGv;
    @Bind(R.id.the_drug_use_gv)
    GridView mTheDrugUseGv;
    @Bind(R.id.multi_drug_use_gv)
    GridView mMultiDrugUseGv;
    @Bind(R.id.smoking_gv)
    GridView mSmokingGv;
    @Bind(R.id.spicy_food_gv)
    GridView mSpicyFoodGv;
    @Bind(R.id.sea_food_gv)
    GridView mSeaFoodGv;
    @Bind(R.id.allergy_food_gv)
    GridView mAllergyFoodGv;
    @Bind(R.id.pet_gv)
    GridView mPetGv;
    @Bind(R.id.drugs_gv)
    GridView mDrugsGv;
    @Bind(R.id.walk_gv)
    GridView mWalkGv;
    @Bind(R.id.breaking_exercises_gv)
    GridView mBreakingExercisesGv;
    @Bind(R.id.self_feeling_gv)
    GridView mSelfFeelingGv;
    @Bind(R.id.symptom_ll)
    LinearLayout mSymptomLl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initSteps();
        AsthmaFactory asthmaFactory = new AsthmaFactory();
        asthmaFactory.initAsthmaData();

        Intent intent = new Intent(this, MainActivityRf.class);
        startActivityForResult(intent, 0);
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

    @Override
    protected void initTopbarViews() {
        super.initTopbarViews();
        mWidth = getDisplayMetrics().widthPixels / 3 - 8;
    }

    @Override
    protected void initOthers() {
        super.initOthers();
        initMoveLimitGridView();
        initAbmornalMoodGridView();
        initPeakMeterGridView();
        initBreathingGridView();
        initSymptomGridView();
        initShitGridView();
        initSleepGridView();
        initTheDrugUseGridView();
        initMultiDrugUseGridView();
        initSmookingGridView();
        initSpicyFoodGridView();
        initSeaFoodGridView();
        initAllergyFoodGridView();
        initPetGridView();
        initDrugsGridView();
        initWalkGridView();
        initBreathingExercisesGridView();
        initSelfFeelingGridView();
    }

    /*@OnClick(R.id.hello_tv)*/
    @SuppressWarnings("unused")
    protected void onClickHelloTv(View view) {
        Intent intent = new Intent(this, RxAndroidActivity.class);
        startActivityForResult(intent, 100);
    }

    @SuppressWarnings("unused")
    @OnCheckedChanged(R.id.gofa)
    protected void onChecedChanged(CompoundButton button, boolean checked) {
        if (button.isChecked()) {
            mSymptomLl.setVisibility(View.VISIBLE);
        } else {
            mSymptomLl.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化运动受限GridView
     */
    private int mWidth;

    private void initMoveLimitGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(MOVELIMITS), mWidth, false);
        mMoveLimitGv.setAdapter(adapter);
        mMoveLimitGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    private void initAbmornalMoodGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(ABNORMALMOOD), mWidth, false);
        mAbnormalMoodGv.setAdapter(adapter);
        mAbnormalMoodGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    private void initPeakMeterGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(PEAKMETER), mWidth, false);
        mPeakMeterGv.setAdapter(adapter);
        mPeakMeterGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 呼吸问题
     */
    private void initBreathingGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(BREATHING), mWidth, true);
        mBreathingGv.setAdapter(adapter);
        mBreathingGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 夜间症状
     */
    private void initSymptomGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(SYMPTOM), mWidth, false);
        mSymptomGv.setAdapter(adapter);
        mSymptomGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 睡眠
     */
    private void initSleepGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(SLEEP), mWidth, false);
        mSleepingGv.setAdapter(adapter);
        mSleepingGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 睡眠
     */
    private void initShitGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(SHIT), mWidth, false);
        mShitGv.setAdapter(adapter);
        mShitGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 该用药
     */
    private void initTheDrugUseGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(THEDRUGUSE), mWidth, false);
        mTheDrugUseGv.setAdapter(adapter);
        mTheDrugUseGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 多用药
     */
    private void initMultiDrugUseGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(MULTIDRUGUSE), mWidth, false);
        mMultiDrugUseGv.setAdapter(adapter);
        mMultiDrugUseGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 吸烟或二手烟
     */
    private void initSmookingGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(SMOOKING), mWidth, false);
        mSmokingGv.setAdapter(adapter);
        mSmokingGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 辛辣刺激食品
     */
    private void initSpicyFoodGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(SPICYFOOD), mWidth, false);
        mSpicyFoodGv.setAdapter(adapter);
        mSpicyFoodGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 海鲜食品
     */
    private void initSeaFoodGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(SEAFOOD), mWidth, false);
        mSeaFoodGv.setAdapter(adapter);
        mSeaFoodGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 易过敏食品
     */
    private void initAllergyFoodGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(ALLERGYFOOD), mWidth, false);
        mAllergyFoodGv.setAdapter(adapter);
        mAllergyFoodGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 宠物
     */
    private void initPetGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(PET), mWidth, false);
        mPetGv.setAdapter(adapter);
        mPetGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 特殊药
     */
    private void initDrugsGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(DRUGS), mWidth, false);
        mDrugsGv.setAdapter(adapter);
        mDrugsGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 散步慢走
     */
    private void initWalkGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(WALK), mWidth, false);
        mWalkGv.setAdapter(adapter);
        mWalkGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 呼吸操
     */
    private void initBreathingExercisesGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(BREAKINGEXERCISES), mWidth, false);
        mBreakingExercisesGv.setAdapter(adapter);
        mBreakingExercisesGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
    }

    /**
     * 散步慢走
     */
    private void initSelfFeelingGridView() {
        final GridViewAdapter adapter = new GridViewAdapter(this, Arrays.asList(SELFFEELING), mWidth, false);
        mSelfFeelingGv.setAdapter(adapter);
        mSelfFeelingGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
            }
        });
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

    /**
     * GridView适配器
     */
    private class GridViewAdapter extends CommonAdapter<String> {
        private boolean mMultiSelect = false;
        private List<Integer> mIntegers = new ArrayList<>();

        private int mGridViewWidth = 100;

        public void setMultiSelect(boolean multi) {
            mMultiSelect = multi;
        }

        public void setItemSelected(Integer item) {
            if (!mIntegers.contains(item)) {
                if (!mMultiSelect) {
                    mIntegers.clear();
                }
                mIntegers.add(item);
            } else {
                mIntegers.remove(item);
            }
            notifyDataSetChanged();
        }

        public GridViewAdapter(Context context, List<String> datas, int width) {
            this(context, datas, width, false);
        }

        public GridViewAdapter(Context context, List<String> datas, int width, boolean isMulti) {
            super(context, R.layout.item_gridview_layout, datas);
            mGridViewWidth = width;
            mMultiSelect = isMulti;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent, mLayoutResource, position);
            convert(viewHolder, getItem(position), position);
            return viewHolder.getConvertView();
        }

        @Override
        protected void convert(ViewHolder viewHolder, String s) {
            //do nothing
        }

        protected void convert(ViewHolder viewHolder, String s, int position) {
            viewHolder.setText(R.id.item_tv, s);
            if (mIntegers.contains((Integer) position)) {
                viewHolder.getConvertView().setSelected(true);
                viewHolder.setViewVisiable(R.id.selecte_iv, View.VISIBLE);
            } else {
                viewHolder.getConvertView().setSelected(false);
                viewHolder.setViewVisiable(R.id.selecte_iv, View.GONE);
            }
            viewHolder.getConvertView().setLayoutParams(new GridView.LayoutParams(mGridViewWidth, mGridViewWidth));
        }
    }


    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

}
