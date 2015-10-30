package com.lite.face.framwork.ui.widget;

/**
 * Created by acer （handgunbreak@gmail.com�?
 * Data: 2015-07-22
 * Time: 17:54
 * Description:
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lite.face.framwork.R;
import com.lite.face.framwork.ui.base.CommonAdapter;
import com.lite.face.framwork.ui.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class BankPopupWindow extends PopupWindow {


    private View mMenuView;
    private GridView mListView;
    private Button mSubmit;
    private TextView mTitle;
    private OnClickListener mOnClickListener;

    private List<String> mStrings;

    public void setStrings(List<String> strings) {
        mStrings = strings;

    }

    private GridViewAdapter mCommonAdapter;
    private boolean mDefaultMul = false;

    public void initListView(List<String> lists, int width) {
        mWidth = width;
        if (mCommonAdapter == null) {
            mCommonAdapter = new GridViewAdapter(mContext, lists, width);
            mCommonAdapter.setMultiSelect(true);
            mListView.setAdapter(mCommonAdapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mCommonAdapter.setItemSelected(position);
                }
            });
        } else {
            mCommonAdapter.setDatas(lists);
            mCommonAdapter.notifyDataSetChanged();
        }
    }

    private int mWidth = 0;
    private Context mContext;

    public BankPopupWindow(Activity context, OnClickListener onClickListener) {
        super(context);
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.bank_popup_window_layout, null);
        mListView = (GridView) mMenuView.findViewById(R.id.selecte_lv);
        mTitle = (TextView) mMenuView.findViewById(R.id.selecte_tv);
        mSubmit = (Button) mMenuView.findViewById(R.id.submit_btn);
        mOnClickListener = onClickListener;

        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点�?
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.anim_fad_out);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xd0000000);
        //设置SelectPicPopupWindow弹出窗体的背�?
        this.setBackgroundDrawable(dw);

        mMenuView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
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
            viewHolder.getConvertView().setLayoutParams(new GridView.LayoutParams(mGridViewWidth, 108));
        }
    }


}
