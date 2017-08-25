/*
 * DateTools.java
 */
package org.pub.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.pub.util.format.Formatter;

/**
 * 获取日期的工具集
 *
 */
public class DateTools {
	
	/**
	 * 获取当前日期
	 * @param format
	 * @return
	 */
	public static String getNow(String format) {
		return format(new Date(), format);
	}
	
	/**
	 * 获取日期格式化
	 * @param format
	 * @return
	 */
	public static String format(Date date, String pattern) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return (returnValue);
	}
	
	/**
   	 * 获取天
   	 * @return
   	 * @throws ParseException 
   	 */
   	public static String getDayNo(Date date) throws ParseException{
   		Calendar curr = new GregorianCalendar();
   		curr.setTime(date);
   		return String.valueOf(curr.get(Calendar.DAY_OF_MONTH));
   	}

    /**
     * 得到昨天
     */
    public static Date getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 得到昨天
     */
    public static Date getYesterday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 得到明天
     */
    public static Date getTomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * 得到明天
     */
    public static Date getTomorrow(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * 得到本月的第一天
     */
    public static String getMonthFirstDay(String year_str, String month_str) {
        int year = Integer.parseInt(year_str);
        int month = Integer.parseInt(month_str);
        return getMonthFirstDay(year, month);
    }

    /**
     * 得到本月的第一天
     */
    public static String getMonthFirstDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        return Formatter.dateFormat(calendar.getTime());
    }

    /**
     * 得到本月的第一天
     */
    public static String getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        return Formatter.dateFormat(calendar.getTime());
    }

    /**
     * 得到本月的最后一天
     */
    public static String getMonthLastDay(String year_str, String month_str) {
        int year = Integer.parseInt(year_str);
        int month = Integer.parseInt(month_str);
        return getMonthLastDay(year, month);
    }

    /**
     * 得到本月的最后一天
     */
    public static String getMonthLastDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return Formatter.dateFormat(calendar.getTime());
    }

    /**
     * 得到本月的最后一天
     */
    public static String getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return Formatter.dateFormat(calendar.getTime());
    }

    /**
     * 某年某月的最后一天
     */
    public static int getLastDayOfMonth(String year_str, String month_str) {
        int year = Integer.parseInt(year_str);
        int month = Integer.parseInt(month_str);
        return getLastDayOfMonth(year, month);
    }

    /**
     * 某年某月的最后一天
     */
    public static int getLastDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getActualMaximum(Calendar.DATE);
    }
    
    /**
   	 * 获取？天以前时间
   	 * @return
   	 * @throws ParseException 
   	 */
   	public static Date getDateBeforeDay(int day) throws ParseException{
   		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
   		Calendar curr = Calendar.getInstance();
   		curr.set(Calendar.DATE,curr.get(Calendar.DATE) - day);
//   		Date date = curr.getTime();
   		Date date = f.parse(f.format(curr.getTime()));
   		return date;
   	}
   	
   	/**
   	 * 获取？天以后时间
   	 * @return
   	 * @throws ParseException 
   	 */
   	public static Date getDateNextDay(int day) throws ParseException{
   		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
   		Calendar curr = Calendar.getInstance();
   		curr.set(Calendar.DATE,curr.get(Calendar.DATE) + day);
//   		Date date = curr.getTime();
   		Date date = f.parse(f.format(curr.getTime()));
   		return date;
   	}
   	
   	/**
	 * 根据起始日期和结束日期获取区间全日期
	 * @param date
	 * @throws ParseException 
	 */
	public static List<Date> getListAll(Date startDate, Date endDate){
		
		List<Date> lDate = new ArrayList<Date>();  
        lDate.add(startDate);// 把开始时间加入集合  
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(startDate);
        boolean bContinue = true;
        while (bContinue) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            cal.add(Calendar.DAY_OF_MONTH, 1);  
            // 测试此日期是否在指定日期之后  
            if (endDate.after(cal.getTime())) {  
                lDate.add(cal.getTime());  
            } else {  
                break;  
            }  
        }
        if(lDate.size() == 1){
			if(!endDate.equals(startDate)){
				lDate.add(endDate);// 把结束时间加入集合  
			}
		}
        return lDate;
	}
	
	 /**
	 * 获取1月以后日期
	 * @return
	 */
	public static Date getDateNextMonth(){
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)+1);
		Date date = curr.getTime();
		return date;
	}
	
	/**
	 * 获取1月以后日期
	 * @return
	 * @throws ParseException 
	 */
	public static Date getDateNextMonth(int count) throws ParseException{
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)+ 1 + count);
//			curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)+ 1 + count);
//			Date date = curr.getTime();
		Date date = f.parse(f.format(curr.getTime()));
		return date;
	}
	
	/**
	 * 根据传入具体日期 ，获取下月的这个日期 
	 * @param count	增加的月
	 * @param day	固定的日期
	 * @return
	 * @throws ParseException
	 */
    public static Date getDateNextMonth(int count, int day) throws ParseException {  
    	DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)+ 1 + count);
		curr.set(Calendar.DAY_OF_MONTH, day);
		Date date = f.parse(f.format(curr.getTime()));
        return date;
    }  

//    public static void main(String[] args) {
//        System.out.println("1 " + DateTools.getMonthFirstDay());
//        System.out.println("1.1 " + DateTools.getMonthFirstDay(2010, 10));
//        System.out.println("1.2 " + DateTools.getMonthFirstDay("2011", "02"));
//        System.out.println("1.3 " + DateTools.getMonthFirstDay("2011", "02"));
//        System.out.println("2 " + DateTools.getMonthLastDay());
//        System.out.println("2.1 " + DateTools.getMonthLastDay(2009, 3));
//        System.out.println("2.2 " + DateTools.getMonthLastDay("2007", "12"));
//        System.out.println("2.3 " + DateTools.getMonthLastDay("2006", "2"));
//        System.out.println("3.1 " + DateTools.getLastDayOfMonth(2007, 1));
//        System.out.println("3.2 " + DateTools.getLastDayOfMonth(2007, 2));
//        System.out.println("3.3 " + DateTools.getLastDayOfMonth(2007, 3));
//        System.out.println("3.4 " + DateTools.getLastDayOfMonth(2007, 4));
//        System.out.println("3.5 " + DateTools.getLastDayOfMonth(2007, 5));
//        System.out.println("3.6 " + DateTools.getLastDayOfMonth(2007, 6));
//        System.out.println("3.7 " + DateTools.getLastDayOfMonth(2007, 7));
//        System.out.println("3.8 " + DateTools.getLastDayOfMonth(2007, 8));
//        System.out.println("3.9 " + DateTools.getLastDayOfMonth(2007, 9));
//        System.out.println("3.10 " + DateTools.getLastDayOfMonth(2007, 10));
//        System.out.println("3.11 " + DateTools.getLastDayOfMonth(2007, 11));
//        System.out.println("3.12 " + DateTools.getLastDayOfMonth(2007, 12));
//        System.out.println("3.20 " + DateTools.getLastDayOfMonth("2007", "11"));
//        System.out.println("getYesterday() " + (new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(getYesterday())));
//        System.out.println("getYesterday() " + (new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(getYesterday(getYesterday()))));
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DATE, 1);
//        calendar.set(Calendar.MONTH, 0);
//        calendar.set(Calendar.YEAR, 2010);
//        System.out.println("getYesterday() " + (new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(getYesterday(calendar.getTime()))));
//        System.out.println("getTomorrow() " + (new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(getTomorrow())));
//        System.out.println("getTomorrow() " + (new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(getTomorrow(getTomorrow()))));
//        calendar.set(Calendar.DATE, 31);
//        calendar.set(Calendar.MONTH, 11);
//        calendar.set(Calendar.YEAR, 2010);
//        System.out.println("getTomorrow() " + (new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(getTomorrow(calendar.getTime()))));
//    }
}
