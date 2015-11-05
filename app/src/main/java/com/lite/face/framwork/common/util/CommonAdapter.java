package com.lite.face.framwork.common.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by acer （handgunbreak@gmail.com）
 * Data: 2015-06-10
 * Time: 22:02
 * Description:
 */

public abstract class CommonAdapter<T> extends BaseAdapter {

    private int mInt = 0;
    protected List<T> mDatas;
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected int mLayoutResource;
    protected int mSelectedPostion = -1;

    public CommonAdapter(Context context, int layoutResource) {
        mContext = context;
        mLayoutResource = layoutResource;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public CommonAdapter(Context context, int layoutResource, List<T> datas) {
        mDatas = datas;
        mContext = context;
        mLayoutResource = layoutResource;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDatas(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return mDatas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent, mLayoutResource, position);
        mCurrentPosition = position;
        if (position == mSelectedPostion) {
            invokSlectedView(viewHolder, true);
        } else {
            invokSlectedView(viewHolder, false);
        }
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    protected void invokSlectedView(ViewHolder viewHolder, boolean selected) {

    }

    private int mCurrentPosition;

    public int getCurrentPosition() {
        return mCurrentPosition;
    }

    protected abstract void convert(ViewHolder viewHolder, T t);

    public int getSelectedPostion() {
        return mSelectedPostion;
    }

    public void setSelectedPostion(int selectedPostion) {
        mSelectedPostion = selectedPostion;
        notifyDataSetChanged();
    }
}
