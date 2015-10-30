package com.lite.face.framwork.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
import com.lite.face.framwork.ui.widget.BankPopupWindow;
import com.lite.face.framwork.ui.widget.FixedGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivityRf extends BaseActivity {

/*    @Bind(R.id.header_center_tv)
    TextView mHeaderCenterTv;
    @Bind(R.id.move_limit_gv)
    GridView mMoveLimitGv;
    @Bind(R.id.peak_meter_gv)
    GridView mPeakMeterGv;
    @Bind(R.id.abnormal_mood_gv)
    GridView mAbnormalMoodGv;*/

    //行动受限
    private final String[] MOVELIMITS = new String[]{"无", "步行或上楼气短（轻度）","稍事活动气短，讲话常有中继（中度）",
            "休息时感气短，端坐呼吸，只能发单字表达（重度）", "不能讲话，嗜睡或意识模糊（危重）"};
    //情绪异常
    private final String[] ABNORMALMOOD = new String[]{"无", "有焦虑(轻度)", "时有焦虑(中度)", "常有焦虑和烦躁(重度)", "嗜睡或意识模糊(危重)"};
    //峰值仪
    private final String[] PEAKMETER = new String[]{"未测量", "PEF值大于预计值 或个人最侍值的80%", "PEF值在预计值或个人最佳值的60%-80%之间", "PEF值小于预计值或个人最佳值的60%"};
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
    private final String[] MULTIDRUGUSE = new String[]{"无", "有"};
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

    private final String[] TYPETITLES = new String[]{"有急性发作(多选)", "峰值仪(单选)", "呼吸问题(多选)", "夜间症状(单选)", "睡眠(单选)", "大便(单选)", "哮喘用药(单选)",
            "额外哮喘用药(单选)", "吸烟或二手烟(单选)", "辛辣刺激食品(单选)", "海鲜(单选)", "易过敏食品(单选)", "宠物(单选)", "特殊药(单选)", "散步慢走(单选)", "呼吸操(单选)", "自我感觉(单选)"};

    @Bind(R.id.type_lv)
    ListView mTypeLv;
    @Bind(R.id.type_tv0)
    TextView mTypeTv0;
    @Bind(R.id.type_gv0)
    FixedGridView mTypeGv0;
    @Bind(R.id.type_tv1)
    TextView mTypeTv1;
    @Bind(R.id.type_gv1)
    FixedGridView mTypeGv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rf);
        ButterKnife.bind(this);
        initSteps();
        initTypeListView();
        mTypeLv.performItemClick(mTypeLv.getChildAt(0), 0, -1);
    }

    private void initTypeListView() {
        final CommonAdapter<String> commonAdapter = new CommonAdapter<String>(this, R.layout.item_type_layout, Arrays.asList(TYPETITLES)) {

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
        mTypeLv.setAdapter(commonAdapter);
        mTypeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                commonAdapter.setSelectedPostion(position);
                initGridView(position);
            }
        });
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
    }

    @Override
    protected void doUpdate(ExtraBean extraBean) {

    }

    @Override
    protected void initOthers() {
        super.initOthers();
    }


    private void initGridView(int index) {
        mTypeTv0.setText(TYPETITLES[index]);
        mTypeTv1.setText("");
        switch (index) {
            case 0:
                mTypeTv0.setText("行动受限(单选)");
                mTypeTv1.setText("情绪异常(单选)");
                resetGridView(Arrays.asList(MOVELIMITS), false, Arrays.asList(ABNORMALMOOD), false);
                break;
            case 1:
                resetGridView(Arrays.asList(ABNORMALMOOD), false);
                break;
            case 2:
                resetGridView(Arrays.asList(PEAKMETER), false);
                break;
            case 3:
                mTypeTv0.setText("呼吸问题(多选)");
                mTypeTv1.setText("夜间症状(单选)");
                resetGridView(Arrays.asList(BREATHING), true, Arrays.asList(SYMPTOM), false);
                BankPopupWindow bankPopupWindow = new BankPopupWindow(this, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                bankPopupWindow.initListView(Arrays.asList(BREATHING), mWidth);
                bankPopupWindow.showAtLocation(mTypeTv0, Gravity.BOTTOM, 0, 0);
                break;
            case 4:
                resetGridView(Arrays.asList(SLEEP), true);
                break;
            case 5:
                resetGridView(Arrays.asList(SHIT), false);
                break;
            case 6:
                resetGridView(Arrays.asList(THEDRUGUSE), false);
                break;
            case 7:
                resetGridView(Arrays.asList(MULTIDRUGUSE), false);
                break;
            case 8:
                resetGridView(Arrays.asList(SMOOKING), false);
                break;
            case 9:
                resetGridView(Arrays.asList(SPICYFOOD), false);
                break;
            case 10:
                resetGridView(Arrays.asList(SEAFOOD), false);
                break;
            case 11:
                resetGridView(Arrays.asList(ALLERGYFOOD), false);
                break;
            case 12:
                resetGridView(Arrays.asList(PET), false);
                break;
            case 13:
                resetGridView(Arrays.asList(DRUGS), false);
                break;
            case 14:
                resetGridView(Arrays.asList(WALK), false);
                break;
            case 15:
                resetGridView(Arrays.asList(BREAKINGEXERCISES), false);
                break;
            case 16:
                resetGridView(Arrays.asList(SELFFEELING), false);
                break;
            default:
                break;

        }
    }

    private void resetGridView(List<String> list, boolean isMulti) {
        resetGridView(mTypeGv0, list, isMulti);
        resetGridView(mTypeGv1, null, isMulti);
    }

    private void resetGridView(List<String> list, boolean isMulti, List<String> list2, boolean isMulti2) {
        resetGridView(mTypeGv0, list, isMulti);
        resetGridView(mTypeGv1, list2, isMulti2);
    }

    private void resetGridView(GridView gridView, List<String> list, boolean isMulti) {
        final GridViewAdapter adapter = new GridViewAdapter(this, list, mWidth, isMulti);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setItemSelected(position);
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
            viewHolder.getConvertView().setLayoutParams(new GridView.LayoutParams(mGridViewWidth, (int) (mGridViewWidth * 1.25)));
        }
    }


}
