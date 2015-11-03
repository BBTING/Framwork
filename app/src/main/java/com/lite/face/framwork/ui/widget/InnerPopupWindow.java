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
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lite.face.framwork.R;
import com.lite.face.framwork.bean.normal.InnerType;
import com.lite.face.framwork.util.CommonAdapter;
import com.lite.face.framwork.util.ViewHolder;

import java.util.List;

public class InnerPopupWindow extends PopupWindow implements View.OnClickListener {


    private View mMenuView;
    private GridView mListView;
    private Button mSubmit;
    private Button mCancel;
    private TextView mTitle;

    private List<InnerType> mStrings;

    public void setStrings(List<InnerType> strings) {
        mStrings = strings;
    }

    private ListViewAdapter mCommonAdapter;

    public void initListView(List<InnerType> lists, int width) {
        mWidth = width;
        if (mCommonAdapter == null) {
            mCommonAdapter = new ListViewAdapter(mContext, lists, width);
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

    private InnerCallback mInnerCallback;

    public void setInnerCallback(InnerCallback innerCallback) {
        mInnerCallback = innerCallback;
    }

    public InnerPopupWindow(Activity context) {
        super(context);
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.bank_popup_window_layout, null);
        mListView = (GridView) mMenuView.findViewById(R.id.selecte_lv);
        mTitle = (TextView) mMenuView.findViewById(R.id.selecte_tv);
        mSubmit = (Button) mMenuView.findViewById(R.id.submit_btn);
        mCancel = (Button) mMenuView.findViewById(R.id.cancel_btn);
        mSubmit.setOnClickListener(this);
        mCancel.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if (mInnerCallback != null) {
            if (v.getId() == R.id.cancel_btn) {
                mInnerCallback.callback(false);
            } else {
                mInnerCallback.callback(true);
            }
        }
        dismiss();
    }

    /**
     * GridView适配器
     */
    private class ListViewAdapter extends CommonAdapter<InnerType> {
        private int mGridViewWidth = 100;

        private List<InnerType> mInnerTypes;

        public void setItemSelected(int index) {
            mInnerTypes.get(index).mSelected = !mInnerTypes.get(index).mSelected;
            //取消其它选项
            if (index != mInnerTypes.size() - 1) {
                mInnerTypes.get(mInnerTypes.size() - 1).mSelected = false;
            } else {
                for (int j = 0; j < mInnerTypes.size() - 1; j++) {
                    mInnerTypes.get(j).mSelected = false;
                }
            }
            notifyDataSetChanged();
        }

        public ListViewAdapter(Context context, List<InnerType> datas, int width) {
            this(context, datas, width, false);
        }

        public ListViewAdapter(Context context, List<InnerType> datas, int width, boolean isMulti) {
            super(context, R.layout.item_gridview_layout, datas);
            mInnerTypes = datas;
            mGridViewWidth = width;
        }

        @Override
        protected void convert(ViewHolder viewHolder, InnerType s) {
            viewHolder.setText(R.id.item_tv, s.mTitle);
            if (s.mSelected) {
                viewHolder.getConvertView().setSelected(true);
                viewHolder.setViewVisiable(R.id.selecte_iv, View.VISIBLE);
            } else {
                viewHolder.getConvertView().setSelected(false);
                viewHolder.setViewVisiable(R.id.selecte_iv, View.GONE);
            }
            viewHolder.getConvertView().setLayoutParams(new GridView.LayoutParams(mGridViewWidth - 60, 108));
        }

    }

    public interface InnerCallback {
        void callback(boolean confirm);
    }
}
