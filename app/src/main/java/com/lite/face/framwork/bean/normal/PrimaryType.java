package com.lite.face.framwork.bean.normal;

import java.io.Serializable;
import java.util.List;

/**
 * Created by whoislcj on 2015/11/1 - 21:30.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class PrimaryType implements Serializable {

    public String mTitle;

    public boolean mSelected = false;

    public List<SecondaryType> mSecondTypes;

}
