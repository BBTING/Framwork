package com.lite.face.framwork.bean.request;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by whoislcj on 2015/10/28 - 15:22.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class RequestBean implements Serializable {

    public final static String POST = "POST";
    public final static String GET = "GET";
    public String mUrl;

    public String mMethod;

    public Map<String, String> mParams;

    public RequestBean(String url, String method) {
        mUrl = url;
        mMethod = method;
    }
}
