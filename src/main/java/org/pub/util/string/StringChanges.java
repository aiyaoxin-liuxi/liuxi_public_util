/*
 * StringChanges.java
 */
package org.pub.util.string;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.pub.util.MapList;

/**
 *
 */
public class StringChanges {

    /**
     * Creates a new instance of StringChanges
     */
    public StringChanges() {
    }

    /**
     * 首字母转成大写
     *
     * @param str 要转换的字符串
     * @return 首字母转成大写完成的字符串
     */
    public static String toInitialUpperCase(String str) {
        if (str != null) {
            str = toUpperCase(str, 0, 1);
        }
        return str;
    }

    /**
     * 首字母转成小写
     *
     * @param str 要转换的字符串
     * @return 首字母转成小写完成的字符串
     */
    public static String toInitialLowerCase(String str) {
        if (str != null) {
            str = toLowerCase(str, 0, 1);
        }
        return str;
    }

    /**
     * 字符串转成大写
     *
     * @param str 要转换的字符串
     * @param from 开始位置(从0开始)
     * @param length 要转换字符的个数
     * @return 转成大写完成的字符串
     */
    public static String toUpperCase(String str, int from, int length) {
        String s = str;
        if (s == null) {
            return null;
        }
        if (from < 0) {
            from = 0;
        }
        if (from > str.length()) {
            from = str.length();
        }
        if (length < 0) {
            length = 0;
        }
        if (length > str.length()) {
            length = str.length();
        }
        if (length >= from) {
            s = str.substring(0, from);
            s += str.substring(from, length).toUpperCase(Locale.ENGLISH);
            s += str.substring(length);
        }
        return s;
    }

    /**
     * 字符串转成小写
     *
     * @param str 要转换的字符串
     * @param from 开始位置(从0开始)
     * @param length 要转换字符的个数
     * @return 转成小写完成的字符串
     */
    public static String toLowerCase(String str, int from, int length) {
        String s = str;
        if (s == null) {
            return null;
        }
        if (from < 0) {
            from = 0;
        }
        if (from > str.length()) {
            from = str.length();
        }
        if (length < 0) {
            length = 0;
        }
        if (length > str.length()) {
            length = str.length();
        }
        if (length >= from) {
            s = str.substring(0, from);
            s += str.substring(from, length).toLowerCase(Locale.ENGLISH);
            s += str.substring(length);
        }
        return s;
    }

    /**
     * 字符串大小写取反
     *
     * @param str 要取反的字符串
     * @param from 开始位置(从0开始)
     * @param length 要转换字符的个数
     * @return 取反完成的字符串
     */
    public static String toInvertCase(String str, int from, int length) {
        if (str == null) {
            return null;
        }
        StringBuilder str_buld = new StringBuilder(str.length());
        if (from < 0) {
            from = 0;
        }
        if (from > str.length()) {
            from = str.length();
        }
        if (length < 0) {
            length = 0;
        }
        if (length + from > str.length()) {
            length = str.length() - from;
        }
        str_buld.append(str.substring(0, from));

        char[] charStr = str.substring(from, length).toCharArray();
        for (int i = 0; i < charStr.length; i++) {
            if (Character.isLetter(charStr[i])) {
                if (Character.isLowerCase(charStr[i])) {
                    str_buld.append(Character.toUpperCase(charStr[i]));
                } else if (Character.isUpperCase(charStr[i])) {
                    str_buld.append(Character.toLowerCase(charStr[i]));
                }
            } else {
                str_buld.append(charStr[i]);
            }
        }

        str_buld.append(str.substring(from + length));
        return str_buld.toString();
    }

    /**
     * 将关键字替换为指定数据
     *
     * @param curStr 操作的字符串
     * @param map 关键字数组
     * @return 替换完成的字符串
     */
    public static String replaceAllStr(String curStr, Map<String, String> map) {
        if (curStr == null || map == null) {
            return null;
        }
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            curStr = curStr.replace(key, map.get(key));
        }
        return curStr;
    }

    /**
     * 用正则表达式列表替换为指定数据
     *
     * @param curStr 操作的字符串
     * @param mapList <要替换的正则表达式，替换的正则表达式>
     * @return 替换完成的字符串
     */
    public static String replaceAllStr(String curStr, MapList<String, String> mapList) {
        if (curStr == null || mapList == null) {
            return null;
        }
        for (int i = 0; i < mapList.size(); i++) {
            curStr = curStr.replaceAll(mapList.getKey(i), mapList.get(i) + "");
        }
        return curStr;
    }

    /**
     * 得到两标记之间代码
     *
     * @param currStr 当前操作字符串
     * @param index 操作字符串的位置
     * @param sFlag 开始标记
     * @param eFlag 结束标记
     * @return 两标记之间代码，如果无法取到代码返回null
     */
    public String getStrInFlags(String currStr, int index, String sFlag, String eFlag) {
        return getStrInFlags(currStr, index, sFlag, eFlag, false);
    }

    /**
     * 得到两标记之间代码
     *
     * @param currStr 当前操作字符串
     * @param index 操作字符串的位置
     * @param sFlag 开始标记
     * @param eFlag 结束标记
     * @param addFlag 是否加标记 true:加标记;false:不加标记只取中间值
     * @return 两标记之间代码，如果无法取到代码返回null
     */
    public String getStrInFlags(String currStr, int index, String sFlag, String eFlag, boolean addFlag) {
        String retStr = null;
        index = currStr.indexOf(sFlag, index + 1);
        int endLoc = currStr.indexOf(eFlag, index + 1);
        if (index > 0 && endLoc > 0) {
            if (addFlag) {
                retStr = currStr.substring(index, endLoc + eFlag.length());
            } else {
                retStr = currStr.substring(index + sFlag.length(), endLoc);
            }
        }
        return retStr;
    }

    /**
     * 在前补充字符 将当前字符串补充到和最终字符串位数相同的字符串
     *
     * @param currentStr 当前字符串
     * @param finalStr 最终字符串
     * @param sf 补充的字符
     * @return 补充后的字符
     */
    public static String supplyStrBefore(String currentStr,
            String finalStr, String sf) {
        return supplyStr(0, 0, currentStr, finalStr, sf, true);
    }

    /**
     * 在后补充字符 将当前字符串补充到和最终字符串位数相同的字符串
     *
     * @param currentStr 当前字符串
     * @param finalStr 最终字符串
     * @param sf 补充的字符
     * @return 补充后的字符
     */
    public static String supplyStrBack(String currentStr,
            String finalStr, String sf) {
        return supplyStr(0, 0, currentStr, finalStr, sf, false);
    }

    /**
     * 补充字符 将当前字符串补充到和最终字符串位数相同的字符串
     *
     * @param bNum 补充标记的比较变量
     * @param cNum 补充标记的比较常量
     * @param currentStr 当前字符串
     * @param finalStr 最终字符串
     * @param sf 补充的字符
     * @param beforeFlag 前补标记
     * @return 补充后的字符
     */
    public static String supplyStr(int bNum, int cNum, String currentStr,
            String finalStr, String sf, boolean beforeFlag) {
        if (currentStr == null) {
            currentStr = "";
        }
        if (finalStr == null) {
            finalStr = "";
        }
        String str;
        if (bNum == cNum) {
            str = loopAddStr(sf, finalStr.length() - currentStr.length());
            if (beforeFlag) {
                str += currentStr;
            } else {
                str = currentStr + str;
            }
        } else {
            str = currentStr;
        }
        return str;
    }

    /**
     * 循环累加字符
     *
     * @param str 要累加的字符串
     * @param count 累加次数
     * @return 累加过的字符串
     */
    public static String loopAddStr(String str, int count) {
        StringBuilder str_buld = new StringBuilder(str.length() * count);
        for (int i = 0; i < count; i++) {
            str_buld.append(str);
        }
        return str_buld.toString();
    }
}
