package com.lite.face.framwork.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by acer （handgunbreak@gmail.com�?
 * Data: 2015-05-22
 * Time: 15:43
 * Description:
 */

public class PreferenceUtil {

    public static String getPrefString(Context context, String key, final String defaultValue) {
        final SharedPreferences settings = PreferenceManager .getDefaultSharedPreferences(context);
        return settings.getString(key, defaultValue);
    }
    public static void setPrefString(Context context, String key, final String defaultValue) {
        final SharedPreferences settings = PreferenceManager .getDefaultSharedPreferences(context);
        settings.edit().putString(key, defaultValue);
        settings.edit().commit();
    }



    public static void clearPreference(Context context,final SharedPreferences p) {
        final SharedPreferences.Editor editor = p.edit();
        editor.clear();
        editor.apply();
    }
}
