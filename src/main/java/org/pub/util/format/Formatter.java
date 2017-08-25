/*
 * Formatter.java
 */
package org.pub.util.format;

import java.text.DecimalFormat;
import java.util.Date;

import org.pub.util.date.DateConverter;

/**
 * 格式化
 */
public class Formatter {

    public static String FORMAT_DOUBLE = "##0.00";

    //-------------------------------------------------------------------------- xml.format
    /**
     * 格式xml内容字符串 去除非法字符 &,<,>,',\ 如遇到字符串等于“null”，默认将其转成空串
     *
     * @param str 要格式化xml内容的字符串
     */
    public static String xmlFormat(String str) {
        return XmlFormatter.xmlDF(str);
    }

    /**
     * 格式xml内容字符串 去除非法字符 &,<,>,',\
     *
     * @param str 要格式化xml内容的字符串
     * @param wipe 如遇到字符串等于“null”，将其转成空串
     */
    public static String xmlFormat(String str, boolean wipe) {
        return XmlFormatter.xmlDF(str, wipe);
    }
    //-------------------------------------------------------------------------- date.format

    /**
     * 日期字符串 格式化 要转成的格式为：yyyy-MM-dd
     *
     * @param str 要格式化的日期字符串
     * @param strFormat 日期字符串转成日期的格式
     */
    public static String dateFormat(String str, String strFormat) {
        return dateFormat(str, strFormat, DateConverter.DF_YMD);
    }

    /**
     * 日期字符串 格式化
     *
     * @param str 要格式化的日期字符串
     * @param strFormat 日期字符串转成日期的格式
     * @param dateFormat 要转成的格式
     */
    public static String dateFormat(String str, String strFormat, String dateFormat) {
        Date date = DateConverter.string2Date(str, strFormat);
        if (date != null) {
            return DateConverter.date2String(date, dateFormat);
        }
        return "";
    }

    /**
     * 将日期格式化 要转成的格式为：yyyy-MM-dd
     *
     * @param date 要格式化的日期
     */
    public static String dateFormat(Date date) {
        return dateFormat(date, DateConverter.DF_YMD);
    }

    /**
     * 将日期格式化
     *
     * @param date 要格式化的日期
     * @param format 要转成的格式
     */
    public static String dateFormat(Date date, String format) {
        return DateConverter.date2String(date, format);
    }
    //-------------------------------------------------------------------------- datetime.format

    /**
     * 日期时间字符串 格式化 要转成的格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param str 要格式化的日期时间字符串
     * @param strFormat 日期时间字符串转成日期时间的格式
     */
    public static String datetimeFormat(String str, String strFormat) {
        return dateFormat(str, strFormat, DateConverter.DF_YMDHMS);
    }

    /**
     * 日期时间字符串 格式化
     *
     * @param str 要格式化的日期时间字符串
     * @param strFormat 日期时间字符串转成日期时间的格式
     * @param dateFormat 要转成的格式
     */
    public static String datetimeFormat(String str, String strFormat, String dateFormat) {
        return dateFormat(str, strFormat, dateFormat);
    }

    /**
     * 将日期时间格式化 要转成的格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param date 要格式化的日期时间
     */
    public static String datetimeFormat(Date date) {
        return dateFormat(date, DateConverter.DF_YMDHMS);
    }

    /**
     * 将日期时间格式化
     *
     * @param date 要格式化的日期时间
     * @param format 要转成的格式
     */
    public static String datetimeFormat(Date date, String format) {
        return DateConverter.date2String(date, format);
    }
    //-------------------------------------------------------------------------- double.format

    /**
     * 将数字字符串格式化
     *
     * @param num 数字
     */
    public static String doubleFormat(double num) {
        return doubleFormat(num + "", FORMAT_DOUBLE);
    }

    /**
     * 将数字字符串格式化
     *
     * @param numStr 数字字符串
     */
    public static String doubleFormat(String numStr) {
        return doubleFormat(numStr, FORMAT_DOUBLE);
    }

    /**
     * 将数字字符串格式化
     *
     * @param num 数字
     * @param format 要转成的格式
     */
    public static String doubleFormat(double num, String format) {
        return doubleFormat(num + "", format);
    }

    /**
     * 将数字字符串格式化
     *
     * @param numStr 数字字符串
     * @param format 要转成的格式
     */
    public static String doubleFormat(String numStr, String format) {
        String num_new = "";
        if (numStr != null && !numStr.trim().equals("") && !numStr.trim().equalsIgnoreCase("null")) {
            try {
                double num = Double.parseDouble(numStr);
                DecimalFormat df = new DecimalFormat(format);
                num_new = df.format(num);
            } catch (Exception ex) {
            }
        }
        return num_new;
    }
}
