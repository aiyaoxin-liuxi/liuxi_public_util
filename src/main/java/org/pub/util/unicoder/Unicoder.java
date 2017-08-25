/*
 * Unicoder.java
 */
package org.pub.util.unicoder;

import java.util.regex.Pattern;

/**
 *
 */
public class Unicoder {

    /**
     * Creates a new instance of Unicoder
     */
    public Unicoder() {
    }

    /**
     * 将Unicode编码转成字符串
     *
     * @param uniStr 要转换的Unicode编码字符串(如:\\uFFE5\\uFFE5)
     * @return 转换完成的字符串
     */
    public static String decoder(String uniStr) {
        StringBuilder decoded = new StringBuilder();
        if (uniStr.length() < 6) {
            return uniStr;
        } else if (uniStr.length() == 6) {
            if (isBMPUnicoder(uniStr)
                    && !isHighSurrogate(uniStr)
                    && !isLowSurrogate(uniStr)) {
                int codePoint = Integer.parseInt(uniStr.substring(2), 16);
                return String.valueOf(Character.toChars(codePoint));
            } else {
                return uniStr;
            }
        } else {
            int offset = 0;
            int idx;
            while (offset < uniStr.length()) {
                idx = uniStr.indexOf("\\u", offset);
                if (idx < 0 || (idx >= 0 && uniStr.length() - idx < 6)) {
                    decoded.append(uniStr.substring(offset));
                    return decoded.toString();
                } else {
                    decoded.append(uniStr.substring(offset, idx));
                    offset = idx;
                    String sixChar = uniStr.substring(idx, idx + 6);
                    if (isBMPUnicoder(sixChar)) {
                        int codePoint;
                        if (isHighSurrogate(sixChar)) {
                            if (uniStr.length() - (offset + 6) >= 6) {
                                String nextSixChar = uniStr.substring(offset + 6, offset + 12);
                                if (isLowSurrogate(nextSixChar)) {
                                    codePoint = Integer.parseInt(sixChar.substring(2), 16) - 0xd800;
                                    codePoint = codePoint << 11;
                                    codePoint += Integer.parseInt(nextSixChar.substring(2), 16) - 0xdc00;
                                    codePoint += 0x10000;
                                    decoded.append(Character.toChars(codePoint));
                                    offset += 6;
                                } else {
                                    decoded.append(sixChar);
                                }
                            } else {
                                decoded.append(sixChar);
                            }
                        } else if (isLowSurrogate(sixChar)) {
                            decoded.append(sixChar);
                        } else {
                            codePoint = Integer.parseInt(sixChar.substring(2), 16);
                            decoded.append(Character.toChars(codePoint));
                        }
                        offset += 6;
                    } else {
                        decoded.append("\\u");
                        offset += 2;
                    }
                }
            }
        }
        return decoded.toString();
    }

    /**
     * 将字符串转成unicode编码串
     *
     * @param cStr 要转换unicode编码串的字符串
     * @return 转换成的Unicode编码串
     */
    public static String encoder(String str) {
        return encoder(str, false, false);
    }

    /**
     * 将字符串转成unicode编码串
     *
     * @param cStr 要转换unicode编码串的字符串
     * @param all_flag 全部转换标记
     * @return 转换成的Unicode编码串
     */
    public static String encoder(String str, boolean all_flag, boolean upper_flag) {
        StringBuilder encoded = new StringBuilder();
        String str_format = "%1$04x";
        if (upper_flag) {
            str_format = "%1$04X";
        }
        char[] charList = str.toCharArray();
        for (int i = 0; i < charList.length; i++) {
            if (all_flag || Character.codePointAt(charList, i) >= 128) {

                encoded.append("\\u").append(String.format(str_format, Character.codePointAt(charList, i)));
            } else {
                encoded.append(String.valueOf(Character.toString(charList[i])));
            }
        }
        return encoded.toString();
    }

    /**
     * 验证输入的字符串是否为标准的unicode代码
     *
     * @param cpStr 要验证的Unicode字符串
     * @return true;false
     */
    public static boolean isBMPUnicoder(String cpStr) {
        if (Pattern.matches("\\\\u[0-9a-f]{4}", cpStr.toLowerCase())) {
            return true;
        }
        return false;
    }

    private static boolean isHighSurrogate(String cpStr) {
        if (isBMPUnicoder(cpStr)) {
            int i = Integer.parseInt(cpStr.substring(2), 16);
            if (i >= 0xd800 && i <= 0xdbff) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLowSurrogate(String cpStr) {
        if (isBMPUnicoder(cpStr)) {
            int i = Integer.parseInt(cpStr.substring(2), 16);
            if (i >= 0xdc00 && i <= 0xdfff) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("---------- 1 >>> " + decoder("\u0031\u0032\u0033"));
        System.out.println("---------- 1 >>> " + decoder("\u6d4b\u8bd5"));
        System.out.println("---------- 2 >>> " + decoder("abc"));
        System.out.println("---------- 3 >>> " + encoder("123"));
        System.out.println("---------- 4 >>> " + encoder("\u0031\u0032\u0033"));
        System.out.println("---------- 5 >>> " + encoder("abcd"));
        System.out.println("---------- 6 >>> " + encoder("测试"));
        System.out.println("---------- 7 >>> " + encoder("测试abc式菜"));
        System.out.println("---------- 8 >>> " + isLowSurrogate("\u0031\u0032\u0033"));
        System.out.println("---------- 8 >>> " + isHighSurrogate("\u0031\u0032\u0033"));
    }
}
