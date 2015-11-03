package com.lite.face.framwork.bean.normal;

import java.io.Serializable;

/**
 * Created by whoislcj on 2015/11/1 - 21:21.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description: 最里层数据 Inner GridView中的一项
 */
public class InnerType implements Serializable{

    public String mTitle;

    public boolean mSelected;

    public InnerType(String title) {
        this(title, false);
    }

    public InnerType(String title, boolean selected) {
        mTitle = title;
        mSelected = selected;
    }
}
