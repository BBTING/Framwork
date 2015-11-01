package com.lite.face.framwork.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.lite.face.framwork.R;
import com.lite.face.framwork.bean.ExtraBean;
import com.lite.face.framwork.ui.base.BaseActivity;
import com.lite.face.framwork.ui.base.CommonAdapter;
import com.lite.face.framwork.ui.base.ViewHolder;
import com.lite.face.framwork.ui.fragment.PreferenceUtil;
import com.lite.face.framwork.ui.widget.BankPopupWindow;
import com.lite.face.framwork.ui.widget.FixedGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivityRf extends BaseActivity {

    @Bind(R.id.type_lv)
    ListView mPrimaryLv;
    @Bind(R.id.type_tv0)
    TextView mSecondaryTv0;
    @Bind(R.id.type_gv0)
    FixedGridView mSecondeGv0;
    @Bind(R.id.type_tv1)
    TextView mSecondaryTv1;
    @Bind(R.id.type_gv1)
    FixedGridView mSecondaryGv1;

    //一级标题
    private final String[] PRIMARYTITLES = new String[]{"有急性发作(多选)", "峰值仪(单选)", "呼吸问题(多选)", "夜间症状(单选)", "睡眠(单选)", "大便(单选)", "哮喘用药(单选)",
            "额外哮喘用药(单选)", "吸烟或二手烟(单选)", "辛辣刺激食品(单选)", "海鲜(单选)", "易过敏食品(单选)", "宠物(单选)", "特殊药(单选)", "散步慢走(单选)", "呼吸操(单选)", "自我感觉(单选)"};
    //二级标题
    //行动受限
    private final String[] SECONDARYMOVELIMITS = new String[]{"无", "步行或上楼气短（轻度）", "稍事活动气短，讲话常有中继（中度）",
            "休息时感气短，端坐呼吸，只能发单字表达（重度）", "不能讲话，嗜睡或意识模糊（危重）"};
    //情绪异常
    private final String[] SECONDARYABNORMALMOOD = new String[]{"无", "有焦虑(轻度)", "时有焦虑(中度)", "常有焦虑和烦躁(重度)", "嗜睡或意识模糊(危重)"};
    //峰值仪
    private final String[] SECONDARYPEAKMETER = new String[]{"未测量", "PEF值大于预计值 或个人最侍值的80%", "PEF值在预计值或个人最佳值的60%-80%之间", "PEF值小于预计值或个人最佳值的60%"};
    //呼吸问题
    private final String[] SECONDARYBREATHING = new String[]{"无", "哮喘", "咳嗽", "胸闷", "气急"};
    //夜间症状
    private final String[] SECONDARYSYMPTOM = new String[]{"无", "憋醒"};
    //睡眠
    private final String[] SECONDARYSLEEP = new String[]{">8小时", "6~8小时", "4~6小时", "<4小时"};
    //大便
    private final String[] SECONDARYSHIT = new String[]{"正常", "便秘", "腹泻"};
    //该用药
    private final String[] SECONDARYTHEDRUGUSE = new String[]{"完成", "完成部分", "完全未完成"};
    //多用药
    private final String[] SECONDARYMULTIDRUGUSE = new String[]{"无", "有"};
    //吸烟或二手烟
    private final String[] SECONDARYSMOOKING = new String[]{"无", "0~10支", "10~20支", "20~30支", "30+支"};
    //辛辣刺激食品
    private final String[] SECONDARYSPICYFOOD = new String[]{"无", "有"};
    //海鲜
    private final String[] SECONDARYSEAFOOD = new String[]{"无", "有"};
    //易过敏食品
    private final String[] SECONDARYALLERGYFOOD = new String[]{"无", "有"};
    //宠物
    private final String[] SECONDARYPET = new String[]{"无", "有"};
    //特殊药
    private final String[] SECONDARYDRUGS = new String[]{"无", "有"};
    //散步慢走
    private final String[] SECONDARYWALK = new String[]{"0~5000", "5000~10000", "10000~15000"};
    //呼吸操
    private final String[] SECONDARYBREAKINGEXERCISES = new String[]{"1次", "2次", "3次+"};
    //自我感觉
    private final String[] SECONDARYSELFFEELING = new String[]{"很好", "一般", "不好"};

    //三级标题
    private String[] SUBSPICYFOOD = new String[]{"辣椒", "花椒", "其它"};
    private String[] SUBSEAFOOD = new String[]{"虾", "蟹", "鱼", "其它"};
    private String[] SUBALLERGYFOOD = new String[]{"鸡蛋", "豆类", "牛奶", "其它"};
    private String[] SUBPETS = new String[]{"猫", "狗", "其它"};
    private String[] SUBDRUGS = new String[]{"抗生素", "阿司匹林", "其它"};

    private Map<String, String[]> mSubHashMap;

    //当前所选标题
    private String mCurrentPrimaryTitle = "";

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initTopbarViews() {
        super.initTopbarViews();
        mWidth = getDisplayMetrics().widthPixels / 3 - 10;
        initPrimaryTitlesLv();
        mPrimaryLv.performItemClick(mPrimaryLv.getChildAt(0), 0, -1);
    }

    @Override
    protected void initOthers() {
        super.initOthers();
        initSubHashMap();
    }

    private void initSubHashMap() {
        mSubHashMap = new HashMap<>();
        mSubHashMap.put(PRIMARYTITLES[9], SUBSPICYFOOD);
        mSubHashMap.put(PRIMARYTITLES[10], SUBSEAFOOD);
        mSubHashMap.put(PRIMARYTITLES[11], SUBALLERGYFOOD);
        mSubHashMap.put(PRIMARYTITLES[12], SUBPETS);
        mSubHashMap.put(PRIMARYTITLES[13], SUBDRUGS);
    }

    private void initPrimaryTitlesLv() {
        final CommonAdapter<String> commonAdapter = new CommonAdapter<String>(this, R.layout.item_type_layout, Arrays.asList(PRIMARYTITLES)) {
            @Override
            protected void convert(ViewHolder viewHolder, String s) {
                viewHolder.setText(R.id.type_tv, s);
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
                mCurrentPrimaryTitle = commonAdapter.getItem(position);
                resetSecondaryGridView(position);
            }
        });
    }


    @Override
    protected void doUpdate(ExtraBean extraBean) {

    }


    private void resetSecondaryGridView(int index) {
        mSecondaryTv0.setText(PRIMARYTITLES[index]);
        mSecondaryTv1.setText("");
        switch (index) {
            case 0:
                mSecondaryTv0.setText("行动受限(单选)");
                mSecondaryTv1.setText("情绪异常(单选)");
                resetGridView(Arrays.asList(SECONDARYMOVELIMITS), false, Arrays.asList(SECONDARYABNORMALMOOD), false);
                break;
            case 1:
                resetGridView(Arrays.asList(SECONDARYABNORMALMOOD), false);
                break;
            case 2:
                resetGridView(Arrays.asList(SECONDARYPEAKMETER), false);
                break;
            case 3:
                mSecondaryTv0.setText("呼吸问题(多选)");
                mSecondaryTv1.setText("夜间症状(单选)");
                resetGridView(Arrays.asList(SECONDARYBREATHING), true, Arrays.asList(SECONDARYSYMPTOM), false);

                break;
            case 4:
                resetGridView(Arrays.asList(SECONDARYSLEEP), true);
                break;
            case 5:
                resetGridView(Arrays.asList(SECONDARYSHIT), false);
                break;
            case 6:
                resetGridView(Arrays.asList(SECONDARYTHEDRUGUSE), false);
                break;
            case 7:
                resetGridView(Arrays.asList(SECONDARYMULTIDRUGUSE), false);
                break;
            case 8:
                resetGridView(Arrays.asList(SECONDARYSMOOKING), false);
                break;
            case 9:
                resetGridView(Arrays.asList(SECONDARYSPICYFOOD), false);
                break;
            case 10:
                resetGridView(Arrays.asList(SECONDARYSEAFOOD), false);
                break;
            case 11:
                resetGridView(Arrays.asList(SECONDARYALLERGYFOOD), false);
                break;
            case 12:
                resetGridView(Arrays.asList(SECONDARYPET), false);
                break;
            case 13:
                resetGridView(Arrays.asList(SECONDARYDRUGS), false);
                break;
            case 14:
                resetGridView(Arrays.asList(SECONDARYWALK), false);
                break;
            case 15:
                resetGridView(Arrays.asList(SECONDARYBREAKINGEXERCISES), false);
                break;
            case 16:
                resetGridView(Arrays.asList(SECONDARYSELFFEELING), false);
                break;
            default:
                break;
        }
    }

    private void resetGridView(List<String> list, boolean isMulti) {
        resetGridView(mSecondeGv0, list, isMulti);
        resetGridView(mSecondaryGv1, null, isMulti);
    }

    private void resetGridView(List<String> list, boolean isMulti, List<String> list2, boolean isMulti2) {
        resetGridView(mSecondeGv0, list, isMulti);
        resetGridView(mSecondaryGv1, list2, isMulti2);
    }

    private void resetGridView(GridView gridView, List<String> list, boolean isMulti) {
        final GridViewAdapter adapter = new GridViewAdapter(this, list, mWidth, isMulti);
        adapter.setKey(mCurrentPrimaryTitle);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.setItemSelected(position);
                List<Integer> list = adapter.getIntegers();
                if (list != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Integer integer : list) {
                        stringBuilder.append(String.valueOf(integer));
                        stringBuilder.append(",");
                    }
                    String value = stringBuilder.toString();
                    int len = value.length();
                    if (value.substring(len - 1, len).equals(",")) {
                        value = value.substring(0, len - 1);
                    }
                    //转成String存储
                    savePrimaryString(mCurrentPrimaryTitle, value);
                    Log.i("sssss", getPrimaryString(mCurrentPrimaryTitle));
                }
                if (adapter.getItem(position).equals("有")) {
                    BankPopupWindow bankPopupWindow = new BankPopupWindow(MainActivityRf.this);
                    bankPopupWindow.setCallback(mCallback);
                    bankPopupWindow.setKey(mCurrentPrimaryTitle);
                    String[] value = mSubHashMap.get(mCurrentPrimaryTitle);
                    if (value != null) {
                        bankPopupWindow.initListView(Arrays.asList(value), getDisplayMetrics().widthPixels - 20);
                        bankPopupWindow.showAtLocation(mSecondaryTv0, Gravity.BOTTOM, 0, 0);
                    }
                }
            }
        });
    }

    private BankPopupWindow.Callback mCallback = new BankPopupWindow.Callback() {
        @Override
        public void callback(String str, List<Integer> list) {
            if (list != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Integer integer : list) {
                    stringBuilder.append(String.valueOf(integer));
                    stringBuilder.append(",");
                }
                String value = stringBuilder.toString();
                int len = value.length();
                if (value.substring(len - 1, len).equals(",")) {
                    value = value.substring(0, len - 1);
                }
                //转成String存储
                saveSecondaryString(mCurrentPrimaryTitle, value);
                Log.i("ssss", getSecondaryString(mCurrentPrimaryTitle));
            }
        }
    };

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
    private class GridViewAdapter extends CommonAdapter<String> {
        private boolean mMultiSelect = false;
        private List<Integer> mIntegers = new ArrayList<>();

        private int mGridViewWidth = 100;

        public void setMultiSelect(boolean multi) {
            mMultiSelect = multi;
        }

        private String mKey;

        public void setKey(String key) {
            mKey = key;
        }

        public List<Integer> getIntegers() {
            return mIntegers;
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
            if (mIntegers.contains(position)) {
                viewHolder.getConvertView().setSelected(true);
                viewHolder.setViewVisiable(R.id.selecte_iv, View.VISIBLE);
            } else {
                viewHolder.getConvertView().setSelected(false);
                viewHolder.setViewVisiable(R.id.selecte_iv, View.GONE);
            }
            viewHolder.getConvertView().setLayoutParams(new GridView.LayoutParams(mGridViewWidth, (int) (mGridViewWidth * 1.25)));
        }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private void savePrimaryString(String key, String value) {
        key = getImei() + "_primary_" + key;
        PreferenceUtil.setPrefString(this, key, value);
    }

    private String getPrimaryString(String key) {
        key = getImei() + "_primary_" + key;
        return PreferenceUtil.getPrefString(this, key, null);
    }
    private void saveSecondaryString(String key, String value) {
        key = getImei() + "_secondary_" + key;
        PreferenceUtil.setPrefString(this, key, value);
    }

    private String getSecondaryString(String key) {
        key = getImei() + "_secondary_" + key;
        return PreferenceUtil.getPrefString(this, key, null);
    }


    public String getImei() {
        TelephonyManager tel = (TelephonyManager) getApplication().getSystemService(Context.TELEPHONY_SERVICE);
        return tel.getDeviceId();
    }
}
