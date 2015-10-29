package com.lite.face.framwork.request;

import android.content.Context;

import com.lite.face.framwork.bean.ExtraBean;
import com.lite.face.framwork.inteface.RequestCallback;
import com.lite.face.framwork.inteface.RequestPlugin;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by whoislcj on 2015/10/28 - 9:25.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
@SuppressWarnings("unused")
public class RequestPluginPools extends Observable implements RequestCallback {

    private WeakReference<Context> mContextWeakReference;
    private WeakReference<Observer> mObserverWeakReference;

    private HashMap<Integer, RequestPlugin> mRequestMap = new HashMap<>();

    public boolean addRequestPlugin(Integer policy, RequestPlugin requestPlugin) {
        if (policy == null || requestPlugin == null || mRequestMap.get(policy) != null) {
            return false;
        }
        mRequestMap.put(policy, requestPlugin);
        return true;
    }

    public boolean removeRequestPlugin(Integer policy, RequestPlugin requestPlugin) {
        if (policy == null || requestPlugin == null || mRequestMap.get(policy) == null) {
            return false;
        }
        mRequestMap.remove(policy);
        return true;
    }

    /**
     * 请求
     *
     * @param extraBean
     * @return
     */
    public boolean doRequest(ExtraBean extraBean) {
        //// TODO: 2015/10/28 can do otherCheck
        doRealRequest(extraBean);
        return true;
    }

    /**
     * 请求
     *
     * @param extraBean
     */
    public void doRealRequest(ExtraBean extraBean) {
        List<Integer> policies = RequestPolicy.checkRequestPolicies(extraBean.mPolicyCode);
        if (policies != null) {
            for (Integer policy : policies) {
                RequestPlugin requestPlugin = mRequestMap.get(policy);
                if (requestPlugin != null) {
                    requestPlugin.request(extraBean, this);
                }
            }
        }
    }

    /**
     * 取消request
     *
     * @param extraBean
     */
    public void cancel(ExtraBean extraBean) {
        if (extraBean != null && extraBean.mRequestCode != null) {
            List<Integer> policies = RequestPolicy.checkCanclePolicy(extraBean.mPolicyCode);
            if (policies != null && policies.size() > 0) {
                for (Integer policy : policies) {
                    RequestPlugin requestPlugin = mRequestMap.get(policy);
                    if (requestPlugin != null) {
                        requestPlugin.cancel(extraBean);
                    }
                }
            }
        }
    }

    @Override
    public void callback(ExtraBean extraBean) {
        //// TODO: 2015/10/28
        realCallball(extraBean);
    }

    private void realCallball(ExtraBean extraBean) {
        setChanged();
        notifyObservers(extraBean);
    }
}
