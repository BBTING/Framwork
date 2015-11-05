package com.lite.face.framwork.common.util;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description:json解析
 * @Copyright:Copyright (c) 2014
 * @Company: 杭州快点网络科技有限公司
 * @File: ParserManager.java
 * @Author: 李超军
 * @Create ：2014-5-28
 * @Version: 1.0.0
 * @Others:
 */
public class JsonUtils {

    /**
     * 对象转化为json
     *
     * @return
     */
    public static String objectToJson(Object object) {
        if (object == null) {
            return "";
        }
        try {
            return JSON.toJSONString(object);
        } catch (JSONException e) {
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * json转化为对象
     *
     * @return
     */
    public static <T> T jsonToObject(Context context, String json, Class<T> clazz) {
        if (TextUtils.isEmpty(json)) {
            return createInstance(clazz);
        }
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {

            //TODO :
        }
        return createInstance(clazz);
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) {

        if (TextUtils.isEmpty(json)) {

            return createInstance(clazz);
        }

        try {

            return JSON.parseObject(json, clazz);
        } catch (JSONException e) {

        } catch (Exception e) {

        }
        return createInstance(clazz);
    }

    /**
     * json转化为对象
     *
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        if (TextUtils.isEmpty(json)) {
            return createArrayInstance(clazz);
        }
        try {
            return JSON.parseArray(json, clazz);
        } catch (JSONException e) {
        } catch (Exception e) {
        }
        return createArrayInstance(clazz);
    }

    public static <T> List<T> createArrayInstance(Class<T> cls) {
        List<T> list = null;
        try {
            list = new ArrayList<T>();
            list.add(cls.newInstance());
        } catch (Exception e) {
        }
        return list;
    }

    public static <T> T createInstance(Class<T> cls) {
        T obj = null;
        try {
            obj = cls.newInstance();
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }

    /**
     * 将Map转化为Json
     *
     * @param map
     * @return String
     */
    public static <T> String mapToJson(Map<String, T> map) {

        StringBuilder buffer = new StringBuilder();
        buffer.append("{");
        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext(); ) {

            buffer.append("\"");
            String key = iterator.next();
            buffer.append(key);
            buffer.append("\":");
            buffer.append(map.get(key));
            if (iterator.hasNext()) {
                buffer.append(",");
            }
        }
        buffer.append("}");
        return buffer.toString();
    }

    public static String objectsToJson(List<?> objects) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object object : objects) {
            sb.append(object.toString());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    public static String objectsToJson(String key, Object object) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\"%s\":", key));
        sb.append("{[");
        sb.append(objectToJson(object));
        sb.append("]}");
        return sb.toString();
    }
}
