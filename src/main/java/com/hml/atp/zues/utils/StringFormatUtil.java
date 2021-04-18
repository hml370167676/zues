package com.hml.atp.zues.utils;

import com.alibaba.fastjson.JSONObject;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hanminglu
 */
public class StringFormatUtil {
    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

    private static Matcher matcher;

    /**
     * 格式化字符串 字符串中使用{key}表示占位符
     *
     * @param sourstr 需要匹配的字符串
     * @param param   参数集
     * @return
     */

    public static String format(String sourstr, Map param) {
        return getString(sourstr, param);

    }

    private static String getString(String sourstr, Map param) {
        String tagerstr = sourstr;

        if (param == null) {
            return tagerstr;
        }

        try {
            matcher = PATTERN.matcher(tagerstr);

            while (matcher.find()) {
                String key = matcher.group();

                String keyclone = key.substring(1, key.length() - 1).trim();

                Object value = param.get(keyclone);

                if (value != null) {
                    tagerstr = tagerstr.replace(key, value.toString());
                }

            }

        } catch (Exception e) {
            return null;

        }

        return tagerstr;
    }

    /**
     * 格式化字符串 字符串中使用{key}表示占位符 利用反射 自动获取对象属性值 (必须有get方法)
     *
     * @param sourstr 需要匹配的字符串
     * @return
     */

    public static String format(String sourstr, Object obj) {
        String tagerstr = sourstr;

        matcher = PATTERN.matcher(tagerstr);

        if (obj == null) {

            return tagerstr;
        }

        PropertyDescriptor pd;

        Method getmethod;

// 匹配{}中间的内容 包括括号

        while (matcher.find()) {
            String key = matcher.group();

            String keyclone = key.substring(1, key.length() - 1).trim();

            try {
                pd = new PropertyDescriptor(keyclone, obj.getClass());

                // 获得get方法
                getmethod = pd.getReadMethod();

                Object value = getmethod.invoke(obj);

                if (value != null) {
                    tagerstr = tagerstr.replace(key, value.toString());
                }

            } catch (Exception e) {

            }

        }

        return tagerstr;

    }

    /**
     * 格式化字符串 (替换所有) 字符串中使用{key}表示占位符
     *
     * @param sourstr 需要匹配的字符串
     * @param param   参数集
     * @return
     */

    public static String formatAll(String sourstr, Map param) {
        return getString(sourstr, param);

    }

    /**
     * 格式花字符串，按照占位符名字
     * <p>
     * 输入：sourstr = xxxxx{a}xxxx{b} ,param = {a:a,b:b}
     * <p>
     * 输出：targetstr = xxxxaxxxxb
     *
     * @param sourstr
     * @param param
     * @return
     */

    public static String format(String sourstr, JSONObject param) {
        return getString(sourstr, param);

    }
}