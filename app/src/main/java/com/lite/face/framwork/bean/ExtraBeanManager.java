package com.lite.face.framwork.bean;

import java.util.HashMap;

/**
 * Created by whoislcj on 2015/10/28 - 10:52.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class ExtraBeanManager {

    private HashMap<String, ExtraBean> mExtraBeanHashMap = new HashMap<>();

    public ExtraBean getExtraBean(String key) {
        ExtraBean extraBean = mExtraBeanHashMap.get(key);
        if (extraBean == null) {
        }
        return mExtraBeanHashMap.get(key);
    }

}
