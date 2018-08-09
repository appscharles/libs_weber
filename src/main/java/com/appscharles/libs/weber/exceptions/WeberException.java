package com.appscharles.libs.weber.exceptions;

/**
 * The type Weber exception.
 */
public class WeberException extends Exception {

    /**
     * The Serial version uid.
     */
    static final long serialVersionUID = 7231375828146530432L;

    /**
     * Instantiates a new Weber exception.
     */
    public WeberException() {
        super();
    }

    /**
     * Instantiates a new Weber exception.
     *
     * @param message the message
     */
    public WeberException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Weber exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public WeberException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Weber exception.
     *
     * @param cause the cause
     */
    public WeberException(Throwable cause) {
        super(cause);
    }

}

