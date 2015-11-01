package com.lite.face.framwork.bean;

import java.util.List;

/**
 * Created by whoislcj on 2015/11/1 - 20:45.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class EmotionBean {

    public Integer mIndex;

    public String mType;

    public boolean mIsSelected;

    public boolean mCanMulti;

    List<EmotionBean> mEmotionBeans;

    public static class SubEmotionBean{
        public Integer mIndex;
        public String mType;
    }
}
