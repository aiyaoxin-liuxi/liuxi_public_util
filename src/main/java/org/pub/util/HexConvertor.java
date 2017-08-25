/*
 * HexConvertor.java
 */
package org.pub.util;

import org.apache.log4j.Logger;

/**
 * Converts some types data to hex string.
 *
 */
public class HexConvertor {
	static Logger logger = Logger.getLogger(HexConvertor.class);

    /**
     * The digits for hexadecimal (radix 16).
     */
    private static final char[] hexChars = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'A', 'B', 'C', 'D', 'E', 'F'
    };
    /**
     * The digits for radix 64
     */
    private static final char[] chars64 = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
        'y', 'z', '#', '$'
    };

    /**
     * Creates a new instance of HexConvertor
     */
    public HexConvertor() {
    }

    /**
     * Converts a byte to hex digit and writes to the supplied buffer
     *
     * @param b a byte
     * @param buf the supplied buffer.
     */
    public static void byte2hex(byte b, StringBuffer buf) {
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }

    /**
     * Returns a string representation of the byte array argument in base 16.
     *
     * @param block an byte array to be converted to a string.
     * @return the string representation of arguments in hexadecimal.
     */
    public static String toHexString(byte[] block) {
        StringBuffer buf = new StringBuffer();
        int len = block.length;
        for (int i = 0; i < len; i++) {
            byte2hex(block[i], buf);
        }
        return buf.toString();
    }

    /**
     * Converts low 24 bit of an integer to 64 digit and writes to the supplied
     * buffer.
     *
     * @param i an integer.
     * @param buf the supplied buffer.
     * @param bits valid bites in integer.
     */
    public static void int264(int i, StringBuffer buf, int bits) {
        int one = ((i & 0x00fc0000) >> 18);
        int two = ((i & 0x0003f000) >> 12);
        int three = ((i & 0x00000fc0) >> 6);
        int four = (i & 0x0000003f);
        if (bits > 18) {
            buf.append(chars64[one]);
        }
        if (bits > 12) {
            buf.append(chars64[two]);
        }
        if (bits > 6) {
            buf.append(chars64[three]);
        }
        buf.append(chars64[four]);
    }

    /**
     * Returns a string representation of the byte array argument in base 64.
     *
     * @param block a byte array to be converted to a string.
     * @return the string representation of arguments base 64.
     */
    public static String to64String(byte[] block) {
        // first, converts every three byte group to an integer
        // and then, retrieve the string representation of integer base 64;
        StringBuilder buf = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int len = block.length;
        int idx;
        String bs;
        // make binary representation of bytes, leading '0' omitted of byte.
        for (int i = 0; i < len; i++) {
            Byte byt = Byte.valueOf(block[i]);
            int in = byt.intValue();
            bs = Integer.toBinaryString(in); // binary representation
            if (bs.length() > 8) {
                bs = bs.substring(bs.length() - 8);
            }
            logger.debug(bs);
            // omits leading '0'
            idx = bs.indexOf('1');
            if (idx < 0) {
                buf.append('0');
            } else {
                buf.append(bs.substring(idx));
            }
        }
        // make string representation of binary sequence in base 64
        idx = 0;
        len = buf.length();
        while (idx < len) {
            // take a 6 bit group
            int end = Math.min(idx + 6, len);
            bs = buf.substring(idx, end);
            int in = Integer.valueOf(bs, 2);
            result.append(chars64[in]);
            idx += 6;
        }
        return result.toString();
    }
}
