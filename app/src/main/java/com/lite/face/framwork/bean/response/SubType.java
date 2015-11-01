package com.lite.face.framwork.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by whoislcj on 2015/11/1 - 21:23.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description: 相当于Secondary GridView中的一个项
 */
public class SubType implements Serializable {

    public String mTitle;

    public boolean mMutil;

    public boolean mSelected;

    public List<InnerType> mInnerTypes;

    public final int mFract;

    public SubType(String title, int fract) {
        this(title, false, fract);
    }

    public SubType(String title, boolean mutil, int fract) {
        this(title, mutil, false, fract);
    }

    public SubType(String title, boolean mutil, boolean selected, int fract) {
        this(title, mutil, selected, null, fract);
    }

    public SubType(String title, boolean mutil, boolean selected, List<InnerType> innerTypes, int fract) {
        mTitle = title;
        mSelected = selected;
        mMutil = mutil;
        mInnerTypes = innerTypes;
        mFract = fract;
    }
}
