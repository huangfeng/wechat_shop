package com.huaibei.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/20 10:41 AM
 * @Version: 1.0
 */
public class JsonUtil {
    public static String toJson(Object o){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(o);
    }
}
