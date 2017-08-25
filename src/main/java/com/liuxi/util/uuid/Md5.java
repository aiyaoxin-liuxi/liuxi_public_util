/*
 * Md5.java
 * Copyright org.javaplus, Inc. All rights reserved.
 * org.javaplus PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.liuxi.util.uuid;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class is a convenience to generate MD5 message digest.
 *
 * @author Stephen Suen
 * @version $Revision: 1.2 $ $Date: 2008/03/24 07:07:24 $
 * @since Framework 1.0
 */
public class Md5 {

    private static final String DEFAULT_ENCODING = "UTF8";
    private static final String ALGORITHM = "MD5";
    private static MessageDigest md5 = null;

    static {
        try {
            md5 = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("cannot find MD5 algorithm");
        }
    }

    // Cannot be instantiated outside of this class.
    private Md5() {
    }

    /**
     * Generate byte array representation of message digest from a given string
     * message.
     *
     * @param message the given message
     * @return byte array representation of message digest
     * @throws RuntimeException if cannot find MD5 algorithm
     */
    public static byte[] getDigest(String message) {
        if (message == null) {
            throw new NullPointerException("message cannot be null");
        }

        byte[] bytes = null;
        try {
            bytes = message.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            // should never happen
        }
        md5.update(bytes);
        return md5.digest();
    }

    /**
     * Generate a byte array of message digest from a given string message with
     * specified encoding.
     *
     * @param message the given message
     * @param enc the specified encoding
     * @return byte array representation of message digest
     * @throws RuntimeException if cannot find MD5 algorithm
     * @throws UnsupportedEncodingException if don't support specified encoding
     */
    public static byte[] getDigest(String message, String enc)
            throws UnsupportedEncodingException {
        if (message == null) {
            throw new NullPointerException("messgae cannot be null");
        }

        byte[] bytes = message.getBytes(enc);
        md5.update(bytes);
        return md5.digest();
    }

    /**
     * Generate a string representation of the message digest from a given
     * string message. The string is hexadecimal digit representation.
     *
     *
     * @param message the given message
     * @return string representation of message digest
     * @throws RuntimeException if cannot find MD5 algorithm
     */
    public static String getDigestString(String message) {
        return Hexadecimal.toString(getDigest(message));
    }

    /**
     * Generate a string representation of the message digest from a given
     * string message. The string is hexadecimal digit representation.
     *
     *
     * @param message the given message
     * @param enc the specified encoding
     * @return string representation of message digest
     * @throws RuntimeException if cannot find MD5 algorithm
     * @throws UnsupportedEncodingException if don't support specified encoding.
     */
    public static String getDigestString(String message, String enc)
            throws UnsupportedEncodingException {
        return Hexadecimal.toString(getDigest(message, enc));
    }
}
