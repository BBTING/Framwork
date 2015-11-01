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

    public SubType(String title) {
        this(title, false);
    }

    public SubType(String title, boolean mutil) {
        this(title, mutil, false);
    }

    public SubType(String title, boolean mutil, boolean selected) {
        this(title, mutil, selected, null);
    }

    public SubType(String title, boolean mutil, boolean selected, List<InnerType> innerTypes) {
        mTitle = title;
        mSelected = selected;
        mMutil = mutil;
        mInnerTypes = innerTypes;
    }
}
