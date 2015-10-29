package com.lite.face.framwork.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Json鏁版嵁瑙ｆ瀽宸ュ叿绫伙紝鏀寔鐨凧son鏍煎紡鏈�
 * [{"":"","":""},{"":"","":""},{"":"","":""}]銆�{"":"","":""}]  鍗曞眰宓屽
 * {"":"","":{}}  鍙敮鎸佸灞傜殑Json瀵硅薄宓屽    澶氬眰鐨勬暟缁勫祵濂楋紝娣峰悎宓屽鏆備笉涓嶆敮鎸�
 *
 * @author asus1
 */
public class JsonUtil {
    /**
     * json鏁版嵁瑙ｆ瀽     [{"":"","":""},{"":"","":""},{"":"","":""}]  鍗曞眰宓屽妯″紡
     * 鏆備笉鏀寔[,,,,](鏁扮粍涓祵濂楃殑涓嶆槸Json瀵硅薄鑰屾槸鏅�瀵硅薄)
     *
     * @param clazz
     * @param json
     * @return
     * @throws Exception
     */
    public static <T> List<T> parserJsonToList(Class<T> clazz, String json) throws Exception {
        Field[] names = clazz.getFields();
        JSONArray array = new JSONArray(json);
        List<T> list = new ArrayList<T>();
        int length = array.length();
        for (int i = 0; i < length; i++) {
            T object = clazz.newInstance();
            JSONObject jsonobj = array.getJSONObject(i);
            for (int j = 0; j < names.length; j++) {
                String strings = names[j].getName();
                names[j].set(object, jsonobj.get(names[j].getName()));
            }
            list.add(object);
        }
        return list;
    }

    /**
     * json鏁版嵁瑙ｆ瀽    [{"":"","":""},{"":"","":""},{"":"","":""}]
     *
     * @param clazz
     * @param array
     * @return
     * @throws Exception
     */
    public static <T> List<T> parserJsonToList(Class<T> clazz, JSONArray array) throws Exception {
        Field[] names = clazz.getFields();
        List<T> list = new ArrayList<T>();
        int length = array.length();
        for (int i = 0; i < length; i++) {
            T object = clazz.newInstance();
            JSONObject jsonobj = array.getJSONObject(i);
            for (int j = 0; j < names.length; j++) {
                names[j].set(object, jsonobj.get(names[j].getName()));
            }
            list.add(object);
        }
        return list;
    }

    /**
     * json鏁版嵁瑙ｆ瀽  {}  绠�崟妯″紡
     *
     * @param clazz
     * @param json
     * @return
     * @throws Exception
     */
    public static <T> T parserJsonToObject(Class<T> clazz, String json) throws Exception {
        Field[] names = clazz.getFields();
        JSONObject jsonobj = new JSONObject(json);
        T object = clazz.newInstance();
        for (int i = 0; i < names.length; i++) {
            Object hah = jsonobj.get(names[i].getName());
            if (hah instanceof JSONObject) {
                hah = parserJsonToObject(names[i].getType(), (JSONObject) hah);
            }
            names[i].set(object, hah);
        }
        return object;
    }

    /**
     * json鏁版嵁瑙ｆ瀽  {}  绠�崟妯″紡
     *
     * @param clazz
     * @param jsonobj
     * @return
     * @throws Exception
     */
    public static <T> T parserJsonToObject(Class<T> clazz, JSONObject jsonobj) throws Exception {
        Field[] names = clazz.getFields();
        T object = clazz.newInstance();
        for (int i = 0; i < names.length; i++) {
            Object hah = jsonobj.get(names[i].getName());
            if (hah instanceof JSONObject) {
                hah = parserJsonToObject(names[i].getType(), (JSONObject) hah);
            }
            names[i].set(object, hah);
        }
        return object;
    }

    public static <T> T parserJsonToBean(Class<T> clazz, JSONObject jsonObject) {
        Gson gson = new Gson();
        T t = gson.fromJson(jsonObject.toString(), clazz);
        return t;
    }

    public static <T> T parserJsonToBean(Class<T> clazz, String jsonString) {
        try {
            Gson gson = new Gson();
            T t = gson.fromJson(jsonString, clazz);
            return t;
        } catch (Exception exception) {
            return null;
        }
    }

	/*

	HashMap<String, Object> map = new HashMap<String, Object>();
    JSONObject jsonObject = net.sf.json.JSONObject.fromObject(values);

    Iterator keyIter = jsonObject.keys();
    String key;
    Object value;
    while( keyIter.hasNext())
    {
        key = (String)keyIter.next();
        value = jsonObject.get(key);
        map.put(key,value);
    }
	 */

    /**
     * 瑙ｆ瀽JSONObject
     *
     * @param jsonObject json瀵硅薄
     * @return 杩斿洖Map鏁版嵁
     */
    public static HashMap<String, Object> parserJsonObject(JSONObject jsonObject) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            Iterator keyIter = jsonObject.keys();
            String key;
            Object value;
            while (keyIter.hasNext()) {
                key = (String) keyIter.next();
                value = jsonObject.get(key);
                hashMap.put(key, value);
            }
        } catch (JSONException je) {
        }
        return hashMap;
    }

/*    public static <T> List<T> parserJsonString2List(String jsonString) {
        Gson gson = new Gson();

        List<T> list = new ArrayList<T>();

        Type type = new TypeToken<ArrayList<T>>() {}.getType();

        list = gson.fromJson(jsonString, type);
        return list;
    }*/

    public static List parserJsonString2List(TypeToken typeToken, String jsonString) {
        Type type = typeToken.getType();
        return new Gson().fromJson(jsonString, type);
    }


}
