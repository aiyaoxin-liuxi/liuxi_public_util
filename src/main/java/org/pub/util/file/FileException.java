/*
 * FileException.java
 */
package org.pub.util.file;

/**
 * 文件 资源管理器 异常
 *
 */
public class FileException extends java.lang.RuntimeException {

    private static final long serialVersionUID = 1l;

    /**
     * Creates a new instance of
     * <code>FileException</code> without detail message.
     */
    public FileException() {
    }

    /**
     * Constructs an instance of
     * <code>FileException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public FileException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of
     * <code>FileException</code> with the specified detail message and cause.
     *
     * @param cause the cause.
     */
    public FileException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs an instance of
     * <code>FileException</code> with the specified detail message and cause.
     *
     * @param msg the detail message.
     * @param cause the cause.
     */
    public FileException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
