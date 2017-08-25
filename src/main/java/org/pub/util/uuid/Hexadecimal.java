/*
 * Hexadecimal.java
 * Copyright org.javaplus, Inc. All rights reserved.
 * org.javaplus PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.pub.util.uuid;

/**
 * Hex is a helper class that enable values to be represented in unsigned
 * hexadecimal string. <p> All convenience methods return unsigned hexadecimal
 * string in uppercase.preceding '0' will always be included to fufill the
 * number of bits. For example, 0xF will be represented as "0F".
 *
 * @author Stephen Suen
 * @version $Revision: 1.2 $ $Date: 2008/03/24 07:07:24 $
 * @since Framework 1.0
 */
public class Hexadecimal {

    private static final char[] hexNumber = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    // Cannot be instantiated.
    private Hexadecimal() {
    }

    /**
     * Get the unsigned hexadecimal string representation for the given byte
     * value.
     *
     * @param b a byte value
     * @return unsigned hexadecimal string respresentation
     */
    public static String toString(byte b) {
        StringBuilder sb = new StringBuilder(2);
        int hi = (b & 0xf0) >> 4;
        int lo = (b & 0x0f);
        sb.append(hexNumber[hi]);
        sb.append(hexNumber[lo]);
        return sb.toString();
    }

    /**
     * Get the unsigned hexadecimal string representation for the given byte
     * array.
     *
     * @param bytes a byte array
     * @return unsigned hexadecimal string representation
     */
    public static String toString(byte[] bytes) {
        int len = bytes.length;
        if (len == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder(len * 2);
        for (int i = 0; i < len; i++) {
            int hi = (bytes[i] & 0xf0) >> 4;
            int lo = (bytes[i] & 0x0f);
            sb.append(hexNumber[hi]);
            sb.append(hexNumber[lo]);
        }

        return sb.toString();
    }

    /**
     * Get unsigned hexadecimal string representation for the given int value.
     *
     * @param n a int value
     * @return unsigned hexadecimal string representation
     */
    public static String toString(int n) {
        int mask = 0x0f;
        char buf[] = new char[8];
        for (int i = 0; i < 8; i++) {
            int h = n & mask;
            n = n >>> 4;
            buf[7 - i] = hexNumber[h];
        }
        return new String(buf, 0, 8);
    }

    /**
     * Get unsigned hexadecimal string representation for the given long value.
     *
     * @param l a long value
     * @return unsigned hexadecimal string representation
     */
    public static String toString(long l) {
        long mask = 0x0f;
        char buf[] = new char[16];
        for (int i = 0; i < 16; i++) {
            long h = l & mask;
            l = l >>> 4;
            buf[15 - i] = hexNumber[(int) h];
        }
        return new String(buf, 0, 16);
    }

    /**
     * Get unsigned hexadecimal string representation for the given char value.
     *
     * @param c a char value
     * @return unsigned hexadecimal string representation
     */
    public static String toString(char c) {
        return toString((byte) c);
    }

    /**
     * Assuming the specified String represents a unsigned hexadecimal byte
     * array, return that byte array. Throws a
     * <CODE>NumberFormatException</CODE> if the String cannot be parsed as a
     * byte array. <p> Examples: <p>
     * <pre>
     *  parseUnsignedBytes("FF01") returns { -1, 1};
     * </pre>
     *
     * @param s the String containing the byte array
     * @return the parsed value of the byte array
     * @throws NumberFormatException If the String does not contain a parsable
     * byte array
     */
    public static byte[] parseUnsignedBytes(String s) {
        if (s == null) {
            throw new NullPointerException("null");
        }

        s = s.trim();
        int length = s.length();
        if (length == 0) {
            throw new NumberFormatException("invalid unsigned hexadecimal string");
        }

        // adjust string to octets
        if ((length & 1) == 1) {
            s = "0" + s;
            length++;
        }

        length /= 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            int index = i * 2;
            int n = Integer.parseInt(s.substring(index, index + 2), 16);

            // convert signed integer to unsigned byte
            if (n > Byte.MAX_VALUE) {
                n -= 256;
            }
            if (n < Byte.MIN_VALUE || n > Byte.MAX_VALUE) {
                throw new NumberFormatException("byte overflow");
            }

            bytes[i] = (byte) n;
        }

        return bytes;
    }
}
