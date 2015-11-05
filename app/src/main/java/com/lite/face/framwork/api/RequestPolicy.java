package com.lite.face.framwork.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whoislcj on 2015/10/28 - 9:28.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description: 数据请求策略
 */
@SuppressWarnings("unused")
public class RequestPolicy {

    //请求
    private static final int OFFSET_BIT_17 = 17;
    private static final int OFFSET_BIT_16 = 16;
    public static final int FROM_FILE = 0x1 << OFFSET_BIT_17;
    public static final int FROM_DATABASE = 0x2 << OFFSET_BIT_17;
    public static final int FROM_PREFERENCE = 0x03 << OFFSET_BIT_17;
    public static final int FROM_SERVER = 0x04 << OFFSET_BIT_17;
    public static final int FROM_CACHE = 0x05 << OFFSET_BIT_17;

    public static final int TO_FILE = (FROM_FILE + 0x08) << OFFSET_BIT_17;
    public static final int TO_DATABASE = (FROM_DATABASE + 0x08) << OFFSET_BIT_17;
    public static final int TO_PREFERENCE = (FROM_PREFERENCE + 0x08) << OFFSET_BIT_17;
    public static final int TO_SERVER = (FROM_SERVER + 0x08) << OFFSET_BIT_17;
    public static final int TO_CACHE = (FROM_CACHE + 0x08) << OFFSET_BIT_17;

    //取消位
    private static final int BIT_CANCEL = (0x01 << OFFSET_BIT_16);

    //取消请求
    public static final int FROM_FILE_CANCEL = FROM_FILE + BIT_CANCEL;
    public static final int FROM_DATABASE_CANCEL = FROM_DATABASE + BIT_CANCEL;
    public static final int FROM_PREFERENCE_CANCEL = FROM_PREFERENCE + BIT_CANCEL;
    public static final int FROM_SERVER_CANCEL = FROM_SERVER + BIT_CANCEL;
    public static final int FROM_CACHE_CANCEL = FROM_CACHE + BIT_CANCEL;

    public static final int TO_FILE_CANCEL = TO_FILE + BIT_CANCEL;
    public static final int TO_DATABASE_CANCEL = TO_DATABASE + BIT_CANCEL;
    public static final int TO_PREFERENCE_CANCEL = TO_PREFERENCE + BIT_CANCEL;
    public static final int TO_SERVER_CANCEL = TO_SERVER + BIT_CANCEL;
    public static final int TO_CACHE_CANCEL = TO_CACHE + BIT_CANCEL;


    /**
     * 检查策略包
     *
     * @param policy
     * @return
     */
    public static List<Integer> checkRequestPolicies(Integer policy) {
        if (policy == null) {
            return null;
        }
        List<Integer> policies = new ArrayList<>();

        if ((policy & FROM_FILE) == FROM_FILE) {
            policies.add(FROM_FILE);
        }

        if ((policy & FROM_DATABASE) == FROM_DATABASE) {
            policies.add(FROM_DATABASE);
        }

        if ((policy & FROM_PREFERENCE) == FROM_PREFERENCE) {
            policies.add(FROM_PREFERENCE);
        }

        if ((policy & FROM_SERVER) == FROM_SERVER) {
            policies.add(FROM_SERVER);
        }

        if ((policy & FROM_CACHE) == FROM_CACHE) {
            policies.add(FROM_CACHE);
        }
        return policies;
    }

    /**
     * 检查策略包
     *
     * @param policy
     * @return
     */
    public static List<Integer> checkResponsePolicies(Integer policy) {
        if (policy == null) {
            return null;
        }
        List<Integer> policies = new ArrayList<>();

        if ((policy & TO_FILE) == TO_FILE) {
            policies.add(TO_FILE);
        }

        if ((policy & TO_DATABASE) == TO_DATABASE) {
            policies.add(TO_DATABASE);
        }

        if ((policy & TO_PREFERENCE) == TO_PREFERENCE) {
            policies.add(TO_PREFERENCE);
        }

        if ((policy & TO_SERVER) == TO_SERVER) {
            policies.add(TO_SERVER);
        }

        if ((policy & TO_CACHE) == TO_CACHE) {
            policies.add(TO_CACHE);
        }
        return policies;
    }


    /**
     * 取消
     *
     * @param cancel
     * @return
     */
    public static List<Integer> checkCanclePolicy(Integer cancel) {
        return cancel != null ? ((cancel & BIT_CANCEL) == BIT_CANCEL ? checkRequestPolicies(cancel) : null) : null;
    }

}
