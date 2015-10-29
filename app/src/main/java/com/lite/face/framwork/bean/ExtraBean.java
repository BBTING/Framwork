package com.lite.face.framwork.bean;

import com.lite.face.framwork.bean.request.RequestBean;

/**
 * Created by whoislcj on 2015/10/28 - 9:27.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class ExtraBean  implements Cloneable{

    public Integer mRequestCode;

    public Integer mPolicyCode;

    public RequestBean mRequestObj;

    public Object mResponseObj;

    public Class mRetClass;

    public ExtraBean(int requestCode, int policyCode, Class retClass) {
        mRequestCode = requestCode;
        mPolicyCode = policyCode;
        mRetClass = retClass;
    }

    public ExtraBean(int requestCode, int policyCode, RequestBean requestObj, Class retClass) {
        mRequestCode = requestCode;
        mPolicyCode = policyCode;
        mRequestObj = requestObj;
        mRetClass = retClass;
    }

}
