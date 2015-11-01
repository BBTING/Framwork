package com.lite.face.framwork.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by whoislcj on 2015/11/1 - 20:45.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class EmotionBean implements Serializable {

    private void test() {
        EmotionBean emotionBean = new EmotionBean();
        emotionBean.mPrimaryEmotions = new ArrayList<>();

        PrimaryEmotion primaryEmotion = new PrimaryEmotion();
        primaryEmotion.mIndex = 0;
        primaryEmotion.mDescription = "有急性发作(多选)";
        primaryEmotion.mMulti = false;
        primaryEmotion.mSelected = false;

        SecondaryEmotion emotion = new SecondaryEmotion();
        emotion.mDescription = "行动受限(单选)";
        emotion.mBaseEmotions = new ArrayList<>();
        BaseEmotion baseEmotion  = new BaseEmotion();
        baseEmotion.mIndex = 0;
        baseEmotion.mDescription= "无";
        //emotion.mBaseEmotions.add()
    }
    public List<PrimaryEmotion> mPrimaryEmotions;

    public static class PrimaryEmotion implements Serializable {
        public Integer mIndex;

        public String mDescription;

        public boolean mSelected = false;

        public boolean mMulti = false;

        public List<SecondaryEmotion> mSecondaryEmotions;
    }

    public static class SecondaryEmotion implements Serializable {

        public Integer mIndex;

        public String mDescription;

        public boolean mMulti = false;

        public List<BaseEmotion> mBaseEmotions;
    }

    //最基类
    public static class BaseEmotion implements Serializable {
        //索引
        public Integer mIndex;
        //
        public String mDescription;
        //是否被选择
        public boolean mSelected = false;

    }
}
