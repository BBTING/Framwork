package com.lite.face.framwork.common.util;


import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by acer （handgunbreak@gmail.com??
 * Data: 2015-06-15
 * Time: 11:49
 * Description:
 */

public class MathUtil {


    private static final String IntDecFormat = "##0";
    private static final String OneDecFormat = "##0.0";
    private static final String TwoDecFormat = "##0.00";//6我咨询的，7是待我回复

    public static String transDouble2Str(Double doubleStr, int dic) {
        return transDouble2Str(doubleStr, dic, null);
    }

    public static Object transDoubleString2String(String string, int dic) {
        return transDoubleString2String(string, dic, null);
    }

    public static String transDouble2Str(Double doubleStr, int dic, String defaultObj) {
        if (doubleStr == null) {
            return defaultObj;
        }
        String format;
        switch (dic) {
            case 0:
                format = IntDecFormat;
                break;
            case 1:
                format = OneDecFormat;
                break;
            case 2:
                format = TwoDecFormat;
                break;
            default:
                return defaultObj;
        }
        String result = transDouble2String(doubleStr, format);
        if (defaultObj != null && result == null) {
            return defaultObj;
        }
        return result;
    }

    public static Object transDoubleString2String(String string, int dic, String defaultObject) {
        if (!TextUtils.isEmpty(string)) {
            try {
                Double dou = Double.valueOf(string);
                return transDouble2Str(dou, dic, defaultObject);
            } catch (Exception ex) {
                return defaultObject;
            }
        }
        return defaultObject;
    }

    private static String transDouble2String(double value, String format) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat(format);
            return decimalFormat.format(value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取百分??
     *
     * @param floatValue
     * @return
     */
    public static String floatTransPercent(Float floatValue, int frac) {
        NumberFormat numberFormatt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小??
        numberFormatt.setMinimumFractionDigits(frac);
        return numberFormatt.format(floatValue);
    }


    public static String doubleTransPercent(Double doubleValue, int frac) {
        NumberFormat numberFormatt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小??
        numberFormatt.setMinimumFractionDigits(frac);
        return numberFormatt.format(doubleValue);
    }

    /**
     * double --> 分数
     *
     * @param doubleValue
     * @return
     */
    public static String doubleTransFraction(Double doubleValue) {
        if (doubleValue == null) {
            return null;
        }
        return doubleTransFraction(String.valueOf(doubleValue));
    }

    /**
     * 浮点--》分??
     *
     * @param floatValue
     * @return
     */
    public static String doubleTransFraction(Float floatValue) {
        if (floatValue == null) {
            return null;
        }
        return doubleTransFraction(String.valueOf(floatValue));
    }

    private static String doubleTransFraction(String stringValue) {
        if (TextUtils.isEmpty(stringValue)) {
            return null;
        }
        if (!stringValue.contains(".")) {
            return stringValue;
        }

        String intPart = stringValue.substring(0, stringValue.indexOf("."));
        String fracPart = stringValue.substring(stringValue.indexOf(".") + 1);

        int fracLen = fracPart.length(); //小数位数

        long fracMoth = 1;
        for (int k = 0; k < fracLen; k++)
            fracMoth *= 10;

        long fracSon = Long.parseLong(intPart + fracPart);

        long calFrac = (fracSon < fracMoth) ? fracSon : fracMoth;

        long j; //????公约??
        for (j = calFrac; j > 1; j--) {
            if (fracSon % j == 0 && fracMoth % j == 0) {
                break;
            }
        }

        fracSon = fracSon / j;
        fracMoth = fracMoth / j;

        return String.valueOf(fracSon) + "/" + String.valueOf(fracMoth);

    }


}
