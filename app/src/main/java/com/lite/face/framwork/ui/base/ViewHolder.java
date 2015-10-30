package com.lite.face.framwork.ui.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by acer （handgunbreak@gmail.com）
 * Data: 2015-06-10
 * Time: 21:35
 * Description:
 */

public class ViewHolder {
    private int mPosition = 0;
    private View mConvertView;
    private Context mContext;
    private SparseArray<View> mViewSparseArray;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        mContext = context;
        mPosition = position;
        mViewSparseArray = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holer = (ViewHolder) convertView.getTag();
            holer.mPosition = position;
            return holer;
        }
    }

    public View getConvertView() {
        return mConvertView;
    }

    public int getPosition() {
        return mPosition;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int id) {
        View view = mViewSparseArray.get(id);
        if (view == null) {
            view = mConvertView.findViewById(id);
            mViewSparseArray.put(id, view);
        }
        return (T) view;
    }

    /**
     * TextView
     *
     * @param id
     * @param text
     * @return
     */
    public ViewHolder setText(int id, String text) {

        TextView textView = getView(id);
        if (textView != null && !TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
        return this;
    }

    public ViewHolder setViewVisiable(int id, int visiable) {

        View view = getView(id);
        if (view != null) {
            view.setVisibility(visiable);
        }
        return this;
    }

    /**
     * TextView
     *
     * @param id
     * @return
     */
    public ViewHolder setTextColor(int id, int color) {

        TextView textView = getView(id);
        if (textView != null) {
            textView.setTextColor(color);
        }
        return this;
    }


    /**
     * ImageView
     *
     * @param id
     * @param resource
     * @return
     */
    public ViewHolder setImageResource(int id, int resource) {
        ImageView imageView = getView(id);
        if (imageView != null) {
            imageView.setImageResource(resource);
        }
        return this;
    }


    public ViewHolder setBackgroundRes(int id, int background) {
        View view = getView(id);
        if (view != null) {
            view.setBackgroundResource(background);
        }
        return this;
    }

    public ViewHolder setBackgroundColor(int id, int color) {
        View view = getView(id);
        if (view != null) {
            view.setBackgroundColor(color);
        }
        return this;
    }


    /**
     * ImageView
     *
     * @param id
     * @param bitmap
     * @return
     */
    public ViewHolder setImageBitmap(int id, Bitmap bitmap) {
        ImageView imageView = getView(id);
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
        return this;
    }

    /**
     * ImageView
     *
     * @param id
     * @param url
     * @return
     */
    public ViewHolder setImageUrl(int id, String url) {
        ImageView imageView = getView(id);
        if (imageView != null) {
            //ImageLoadManager.getInstance(mContext).loadAvatarImage(imageView, url);
        }
        return this;
    }

    protected void loadImageByImageLoader(ImageView imageView, String url) {

    }

    protected void loadImageByPicasso(ImageView targetImageView, String url) {

      /*  Picasso picasso = Picasso.with(mContext);
        RequestCreator requestCreator;
        if (url != null) {
            requestCreator = picasso.load(url);
        } *//*else if (mFile != null) {
            requestCreator = picasso.load(mFile);
        } else if (mRes != 0) {
            requestCreator = picasso.load(mRes);
        }*//* else {
            return;
        }

        if (requestCreator == null) {
            return;
        }

        if (getImageResForEmpty() != 0) {
            requestCreator.placeholder(getImageResForEmpty());
        }
        if (getImageResForError() != 0) {
            requestCreator.error(getImageResForError());
        }

        requestCreator.fit();
        requestCreator.into(targetImageView);*/
    }


   /* public void setResouceId(int resouceId) {
        mResouceId = resouceId;
    }

    public int getImageResForEmpty() {
        return mResouceId;
    }

    public int getImageResForError() {
        return mResouceId;
    }*/

    /**
     *
     */
    public ViewHolder setViewOnClickListener(int id, View.OnClickListener clickListener) {
        View view = getView(id);
        if (view != null) {
            view.setOnClickListener(clickListener);
        }
        return this;
    }

    public ViewHolder setViewOnClickListener(int[] ids, View.OnClickListener clickListener) {
        for (int id : ids) {
            View view = getView(id);
            if (view != null) {
                view.setOnClickListener(clickListener);
            }
        }
        return this;
    }

    public ViewHolder setOnItemClickListener(int id, final AdapterView.OnItemClickListener onItemClickListener) {
        final View view = getView(id);
        final int _id = id;
        if (onItemClickListener != null && view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(null, view, mPosition, _id);
                }
            });
        }
        return this;
    }


}
