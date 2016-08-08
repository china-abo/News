package com.abo.news.Http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Json转换
 * Created by abo on 16/8/8.
 */
public class JsonUtils {
    private static Gson mGson = new Gson();

    /**
     * 将对象转换为Json字符串
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String serialize(T object){
        return mGson.toJson(object);
    }

    /**
     * 将Json字符串转换为对象
     * @param json
     * @param tClass
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T deserialize(String json ,Class<T> tClass) throws JsonSyntaxException{
        return mGson.fromJson(json,tClass);
    }

    /**
     * 将Json对象转换为实体对象
     * @param jsonObject
     * @param tClass
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T deserialize(JsonObject jsonObject,Class<T> tClass) throws JsonSyntaxException{
        return mGson.fromJson(jsonObject,tClass);

    }

    public static <T> T deserialize(String json, Type type) throws JsonSyntaxException{
        return mGson.fromJson(json,type);
    }
}
