package org.pub.util.validators;

import org.apache.log4j.Logger;

/**
 * 字符串验证
 *
 */
public class StringValidator {
	static Logger logger = Logger.getLogger(StringValidator.class);

    /**
     * Creates a new instance of StringValidator
     */
    public StringValidator() {
    }

    /**
     * 检测给定的字符串是否为空字符串。 若给定的字符串符合下面的规则，则认为它是空字符串：
     * <pre>
     *   <ol>
     *     <li>是null。</li>
     *     <li>有效字符是“null”的各种大小写形式。</li>
     *     <li>长度为零。</li>
     *     <li>仅包含空字符。</li>
     *   </ol>
     * </pre>
     *
     * @param str 待测试的字符串。
     * @return 如果字符串符合上述规则，则返回<code>true</code>， 否则返回<code>false</code>
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        String tmpStr = str.trim();
        if (tmpStr.equalsIgnoreCase("null")) {
            return true;
        }
        tmpStr = tmpStr.replaceAll("\\s", "");
        if (tmpStr.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 如果指定的字符串包含指定键的映射关系，则返回 true
     */
    public static boolean containsKey(String str, String... keys) {
        if (str != null && !str.equals("")) {
            if (keys != null) {
                for (int i = 0; i < keys.length; i++) {
                    if (str.equals(keys[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 如果指定的字符串包含指定键的映射关系（忽略大小写），则返回 true
     */
    public static boolean containsKeyIgnoreCase(String str, String... keys) {
        if (str != null && !str.equals("")) {
            if (keys != null) {
                for (int i = 0; i < keys.length; i++) {
                    if (str.equalsIgnoreCase(keys[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 字符串是否为必须字段
     */
    public static String required(String str) {
        return required(str, null);
    }

    /**
     * 字符串是否为必须字段
     */
    public static String required(String str, String msg) {
        if (msg == null || msg.equals("")) {
            msg = "不能为空！";
        }
        if (isEmpty(str)) {
            return msg;
        }
        return null;
    }

    /**
     * 字符串长度验证
     */
    public static String stringLength(String str, int min, int max) {
        return stringLength(str, min, max, null);
    }

    /**
     * 字符串长度验证
     */
    public static String stringLength(String str, int min, int max, String msg) {
    	logger.info("str=" + str + "\tmin=" + min + "\tmax=" + max + "\t");
        if (str == null) {
            str = "";
        }
        if (msg == null || msg.equals("")) {
            msg = "[${str}]长度必须在[${min}]到[${max}]之间！";
        }
        msg = msg.replace("${str}", str);
        msg = msg.replace("${min}", min + "");
        msg = msg.replace("${max}", max + "");
        if (str.length() < min || str.length() > max) {
            return msg;
        }
        return null;
    }
//    public static void main(String[] args) {
//        //必须字段
//        System.out.println(">>>>> " + required(null));
//        System.out.println(">>>>> " + required("","此字段不能为空！"));
//        System.out.println(">>>>> " + required("a"));
//        System.out.println(">>>>> " + required("a"));
//        
//        //字符串长度验证
//        System.out.println(">>>>> " + stringLength(null, 1, 3));
//        System.out.println(">>>>> " + stringLength("", 1, 3));
//        System.out.println(">>>>> " + stringLength("a", 1, 3));
//        System.out.println(">>>>> " + stringLength("aa", 1, 3));
//        System.out.println(">>>>> " + stringLength("aaa", 1, 3));
//        System.out.println(">>>>> " + stringLength("aaaa", 1, 3));
//    }
}
