/*
 * StringParse.java
 */
package org.pub.util.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串 解析
 *
 */
public class StringParse {

    /**
     * Creates a new instance of StringParse
     */
    public StringParse() {
    }
    
    /**
	 * String null 转为空字符串
	 * @param obj
	 * @return
	 */
	public String StringNotNull(String str){
		return str == null ? "" : str;
	}
	
	public static String replaceChar(String str) {
		
		return str.replaceAll("\\.", "/");
	}	

    /**
     * 替换第一个字符串
     *
     * @param str 要替换的主字符串
     * @param key 替换关键字
     * @param value 替换的值
     */
    public static String replaceFirst(String str, String key, String value) {
        if (str == null) {
            str = "";
        }
        if (key != null) {
            int idx = str.indexOf(key);
            if (idx != -1) {
                str = str.substring(0, idx) + value + str.substring(idx + key.length());
            }
        }
        return str;
    }

    /**
     * 通过数据索引得到字符串数据方法
     *
     * @param str 要解析的字符串
     * @param delim 分隔符
     * @param idx 数据索引
     * @param 报文数据
     */
    public static String getParseString(String str, String delim, int idx) {
        String data_str = "";
        if (idx >= 0 && str != null && !str.equals("")) {
            String[] data_arr = str.split(delim);
            if (data_arr != null && data_arr.length > 0 && data_arr.length > idx) {
                return data_arr[idx];
            }
        }
        return data_str;
    }

    /**
     * 通过给定的分隔符，来解析字符串
     *
     * @param str 要解析的字符串
     * @param delim 分隔符
     * @return 解析出来的字符串列表
     */
    public static ArrayList parseString(String str, String delim) {
        return parseString(str, delim, false);
    }

    /**
     * 通过给定的分隔符，来解析字符串
     *
     * @param str 要解析的字符串
     * @param delim 分隔符
     * @param returnDelims 返回分隔符
     * @return 解析出来的字符串列表
     */
    public static ArrayList parseString(String str, String delim, boolean returnDelims) {
        ArrayList<String> lists = new ArrayList<String>();
        if (str == null || str.equals("") || delim == null || delim.equals("")) {
            return null;
        }
        int sIdx = 0;
        int eIdx = str.indexOf(delim);
        str += delim;
        while (eIdx != -1) {
            if (returnDelims) {
                lists.add(str.substring(sIdx, eIdx) + delim);
            } else {
                lists.add(str.substring(sIdx, eIdx));
            }
            sIdx = eIdx + delim.length();
            eIdx = str.indexOf(delim, sIdx);
        }
        return lists;
    }

    /**
     * 得到两标记这间的代码
     *
     * @param src 操作字符串
     * @param sFlag 开始标记
     * @param eFlag 结束标记
     * @return list 得到的列表
     */
    public static List getBachStrForFlag(String src, String sFlag, String eFlag) {
        return getBachStrForFlag(src, sFlag, eFlag, false);
    }

    /**
     * 得到两标记这间的代码
     *
     * @param src 操作字符串
     * @param sFlag 开始标记
     * @param eFlag 结束标记
     * @return list 得到的列表
     */
    public static List getBachStrForFlag(String src, String sFlag, String eFlag, boolean returnDelims) {
        List<String> list = new ArrayList<String>();
        String gsrc;
        int sl = src.indexOf(sFlag);
        int el = 0;
        while (sl >= 0 && el >= 0) {
            el = src.indexOf(eFlag, sl + 1);
            if (sl != el && el > 0 && sl >= 0) {
                int gsl;
                int gel;
                if (returnDelims) {
                    gsl = sl;
                    gel = el + 1;
                    if (gel > src.length()) {
                        gel = src.length();
                    }
                } else {
                    gsl = sl + 1;
                    gel = el;
                }
                gsrc = src.substring(gsl, gel);
                list.add(gsrc);
            }
            sl = src.indexOf(eFlag, el + 1);
        }

        return list;
    }

    /**
     * 返回给定对象的字符串表达形式。
     *
     * @param obj 将要范围字符串表达形式的对象。
     * @return 若对象为null，则返回“”（空字符串）； 否则返回对象的toString()方法的返回值；
     */
    public static String valueOf(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
    
    /**
     * 判断字符串是否为空
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isEmptyString(String str){
        return str == null || str.trim().length() == 0;
    }
//    /**测试*/
//    private static void test(){
//        ArrayList list = parseString("001:AAA|002:BBB|003:CCC|","|");
//        for(int i = 0; i < list.size(); i++){
//            ArrayList listNode = parseString(list.get(i).toString(),":");
//            System.out.println("i=" + i + " " + list.get(i));
//            for(int j = 0; j < listNode.size(); j++){
//                System.out.println("    j=" + j + " " + listNode.get(j));
//            }
//        }
//    }
}
