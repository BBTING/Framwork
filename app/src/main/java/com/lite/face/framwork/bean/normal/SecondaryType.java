package com.lite.face.framwork.bean.normal;

import java.io.Serializable;
import java.util.List;

/**
 * Created by whoislcj on 2015/11/1 - 21:28.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class SecondaryType implements Serializable {

    public String mTitle;

    public boolean mMulti;

    public int mPriority;

    public List<SubType> mSubTypes;

}
