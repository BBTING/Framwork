package com.lite.face.framwork.common.util;

/**
 * Created by whoislcj on 2015/11/4 - 16:36.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.lite.face.framwork.ui.base.AppContext;


/**
 * @Description: 配置管理
 * @Copyright:Copyright (c) 2015
 * @Company: 杭州医本健康科技有限公司
 * @File: EBConfigManager.java
 * @Author: 李超军
 * @Create ：2015-5-27
 * @Version: 1.0.0
 * @Others:
 */
public class ConfigManager {


    /**
     * 获取 version code
     */
    public static int getVersionCode(Context context) {
        // 获取packagemanager的实例
        int versionCode = 0;
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取version name
     */
    public static String getVersionName() {
        // 获取packagemanager的实例
        String versionName = "UNKOWN";
        Context context = AppContext.getAppContext();
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取DeviceId
     */
    public static String getDeviceId() {

        Context context = AppContext.getAppContext();

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        return tm.getDeviceId();
    }

    /**
     * 获取当前移动网络类型名字
     *
     * @param context
     */
    private static String getMobileNetwork(Context context) {
        String netTypeName = "UNKOWN";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                netTypeName = "2g";
                break;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                netTypeName = "3g";
                break;
            case TelephonyManager.NETWORK_TYPE_LTE:
                netTypeName = "4g";
                break;
        }
        return netTypeName;
    }

    /**
     * 获取当前网络类型名字
     *
     * @param context
     */

    public static String getNetTypeName(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            String type = info.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                return "wifi";
            } else if (type.equalsIgnoreCase("MOBILE")) {
                return getMobileNetwork(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "UNKOWN";
    }

}

