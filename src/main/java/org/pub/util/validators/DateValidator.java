/*
 * DateValidator.java
 */
package org.pub.util.validators;

import java.util.Date;

import org.pub.util.date.DateConverter;

/**
 * 日期验证
 *
 */
public class DateValidator {

    /**
     * 日期时间验证
     */
    public static String datetime(Date date, String min, String max) {
        return datetime(date, min, max, DateConverter.DF_YMDHMS, null);
    }

    /**
     * 日期时间验证
     */
    public static String datetime(Date date, String min, String max, String format, String msg) {
        return dateValidator(date, min, max, format, msg);
    }

    /**
     * 日期验证
     */
    public static String date(Date date, String min, String max) {
        return date(date, min, max, DateConverter.DF_YMD, null);
    }

    /**
     * 日期验证
     */
    public static String date(Date date, String min, String max, String format, String msg) {
        if (date != null) {
            date = DateConverter.string2Date(DateConverter.date2String(date));
        }
        return dateValidator(date, min, max, format, msg);
    }

    /**
     * 日期验证
     */
    private static String dateValidator(Date date, String min, String max, String format, String msg) {
        //System.out.print("date=" + date + "\tmin=" + min + "\tmax=" + max + "\t");
        if (date == null) {
            return "日期不能为空！";
        }
        if (msg == null || msg.equals("")) {
            msg = "[${date}]必须在[${min}]到[${max}]之间！";
        }
        msg = msg.replace("${date}", DateConverter.date2String(date) + "");
        Date min_date = null;//最小日期
        Date max_date = null;//最大日期
        //转换最小日期
        if (min != null && !min.equals("") && !min.equals("*")) {
            min_date = DateConverter.string2Date(min, format);
            if (min_date == null) {
                throw new java.lang.IllegalArgumentException("最小日期[" + min + "]格式错误!");
            }
            msg = msg.replace("${min}", min + "");
        } else {
            msg = msg.replace("${min}", "最小");
        }
        //转换最大日期
        if (max != null && !max.equals("") && !max.equals("*")) {
            max_date = DateConverter.string2Date(max, format);
            if (max_date == null) {
                throw new java.lang.IllegalArgumentException("最大日期[" + max + "]格式错误!");
            }
            msg = msg.replace("${max}", max + "");
        } else {
            msg = msg.replace("${max}", "最大");
        }
        if (min_date == null && max_date == null) {
            throw new java.lang.IllegalArgumentException("最小日期和最大日期不能同时为空!");
        }
        if (min_date != null && date.getTime() < min_date.getTime()) {
            return msg;
        }
        if (max_date != null && date.getTime() > max_date.getTime()) {
            return msg;
        }
        return null;
    }
//    public static void main(String[] args) {
//        try {
//            System.out.println("date = null >>>>> " + date(null, "2008-10-11", ""));
//
//            System.out.println("min is null >>>>> " + date(new Date(), null, "2008-09-01"));
//            System.out.println("min is '' >>>>> " + date(new Date(), "", "2008-09-03"));
//            System.out.println("min is * >>>>> " + date(new Date(), "*", "2008-09-04"));
//            //System.out.println("min is * >>>>> " + date(new Date(),"aa","2008-10-11"));
//
//            System.out.println("max is null >>>>> " + date(new Date(), "2008-09-01", null));
//            System.out.println("max is '' >>>>> " + date(new Date(), "2008-09-03", ""));
//            System.out.println("max is * >>>>> " + date(new Date(), "2008-09-04", "*"));
//            //System.out.println("max is * >>>>> " + date(new Date(),"2008-10-11","b"));
//
//            //System.out.println("max is * >>>>> " + date(new Date(),"","*"));
//
//            System.out.println("min is 2008-09-01 >>>>> " + date(new Date(), "2008-09-01", "2008-10-11"));
//            System.out.println("min is 2008-09-03 >>>>> " + date(new Date(), "2008-09-03", "2008-10-11"));
//            System.out.println("min is 2008-09-04 >>>>> " + date(new Date(), "2008-09-04", "2008-10-11"));
//
//            System.out.println("max is 2008-09-01 >>>>> " + date(new Date(), "2008-08-01", "2008-09-01"));
//            System.out.println("max is 2008-09-03 >>>>> " + date(new Date(), "2008-08-03", "2008-09-03"));
//            System.out.println("max is 2008-09-04 >>>>> " + date(new Date(), "2008-08-04", "2008-09-05"));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
