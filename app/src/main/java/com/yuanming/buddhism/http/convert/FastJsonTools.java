package com.yuanming.buddhism.http.convert;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FastJsonTools {

    /**
     * 用fastjson 将json字符串解析为一个 JavaBean
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T getBean(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = JSON.parseObject(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return t;
    }

    /**
     * 用fastjson 将json字符串 解析成为一个 List<JavaBean> 及 List<String>
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> getBeans(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("HSKAS1", e.toString());
        }
        return list;
    }

    /**
     * 用fastjson 将jsonString 解析成 List<Map<String,Object>>
     *
     * @param jsonString
     * @return
     */
    public static List<Map<String, Object>> getListMap(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            // 两种写法
            // list = JSON.parseObject(jsonString, new
            // TypeReference<List<Map<String, Object>>>(){}.getType());

            list = JSON.parseObject(jsonString,
                    new TypeReference<List<Map<String, Object>>>() {
                    });
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;

    }

    /**
     * 将java类型的对象转换为JSON格式的字符串
     *
     * @param object java类型的对象
     * @return JSON格式的字符串
     */
    public static <T> String serialize(T object) {

        return JSON.toJSONString(object);

    }


    /**
     * 将JSON格式的字符串转换成任意Java类型的对象
     *
     * @param json JSON格式的字符串
     * @param type 任意Java类型
     * @return 任意Java类型的对象
     * <p/>
     * 案例：
     * List<HashMap<String, Object>> list = FastJSONHelper.deserializeAny(jsonString,
     * new TypeReference<List<HashMap<String, Object>>>() {
     * });
     */
    public static <T> T deserializeAny(String json, TypeReference<T> type) {

        return JSON.parseObject(json, type);

    }
}
