/*
 * XmlFormatter.java
 */
package org.pub.util.format;

/**
 * xml格式化
 *
 */
public class XmlFormatter {

    /**
     * Creates a new instance of XmlFormatter
     */
    public XmlFormatter() {
    }

    /**
     * xml数据格式化
     */
    public static String xmlDF(String str) {
        return xmlDF(str, true);
    }

    /**
     * xml数据格式化
     *
     * @param str 要格式化的字符串
     * @param wipe 是否将null转为空窜
     */
    public static String xmlDF(String str, boolean wipe) {
        if (str != null) {
            if (wipe && str.equalsIgnoreCase("null")) {
                return "";
            }
            str = replaceAmp(str);
            str = replaceLt(str);
            str = replaceGt(str);
            str = replaceApos(str);
            str = replaceQuot(str);
        } else {
            return "";
        }
        return str;
    }

    /**
     * 替换xml中的 & 为 &amp;
     */
    public static String replaceAmp(String str) {
        return str.replace("&", "&amp;");
    }

    /**
     * 替换xml中的 < 为 &lt;
     */
    public static String replaceLt(String str) {
        return str.replace("<", "&lt;");
    }

    /**
     * 替换xml中的 > 为 &gt;
     */
    public static String replaceGt(String str) {
        return str.replace(">", "&gt;");
    }

    /**
     * 替换xml中的 ' 为 &apos;
     */
    public static String replaceApos(String str) {
        return str.replace("'", "&apos;");
    }

    /**
     * 替换xml中的 " 为 &quot;
     */
    public static String replaceQuot(String str) {
        return str.replace("\"", "&quot;");
    }
}
