package org.xiangbalao.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 解析Json数据的工具类
 *
 * @author longtaoge
 */
public class GsonUtil {

    public GsonUtil() {
    }

    public static String createGsonString(Object object) {
        Gson gson = new Gson();
        String gsonString = gson.toJson(object);
        return gsonString;
    }

    public static <T> T changeGsonToBean(String gsonString, Class<T> cls) {
        Gson gson = new Gson();
        T t = gson.fromJson(gsonString, cls);
        return t;
    }

    public static <T> T changeGsonToBean(InputStreamReader gsonString, Class<T> cls) {
        Gson gson = new Gson();
        T t = gson.fromJson(gsonString, cls);
        return t;
    }


    /**
     * 将Json List 解析为 List 通用方法
     *
     * @param s
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
    }


  /*  public static <T> List<T> changeGsonToList(String gsonString, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
        }.getType());
        return list;
    }*/

    /**
     * 将Json List 解析为  指定类型List
     */
//    public static List<GsonTest> changeGsonToGsonTestList(String gsonString) {
//        Gson gson = new Gson();
//        List<GsonTest> list = gson.fromJson(gsonString,
//                new TypeToken<List<GsonTest>>() {
//                }.getType());
//        return list;
//    }
    public static <T> List<Map<String, T>> changeGsonToListMaps(
            String gsonString) {
        List<Map<String, T>> list = null;
        Gson gson = new Gson();
        list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
        }.getType());
        return list;
    }

    public static <T> Map<String, T> changeGsonToMaps(String gsonString) {
        Map<String, T> map = null;
        Gson gson = new Gson();
        map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
        }.getType());
        return map;
    }


}
