/*
 * FtpException.java
 */
package org.pub.util.ftp;

/**
 * ftp 异常
 *
 */
public class FtpException extends java.lang.RuntimeException {

    private static final long serialVersionUID = 1l;

    /**
     * Creates a new instance of
     * <code>FtpException</code> without detail message.
     */
    public FtpException() {
    }

    /**
     * Constructs an instance of
     * <code>FtpException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public FtpException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of
     * <code>FtpException</code> with the specified detail message and cause.
     *
     * @param cause the cause.
     */
    public FtpException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs an instance of
     * <code>FtpException</code> with the specified detail message and cause.
     *
     * @param msg the detail message.
     * @param cause the cause.
     */
    public FtpException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
