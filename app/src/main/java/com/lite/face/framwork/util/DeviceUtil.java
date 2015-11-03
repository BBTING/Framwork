package com.lite.face.framwork.util;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by whoislcj on 2015/11/3 - 9:56.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class DeviceUtil {

    public static String getDeviceOpenID(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}
