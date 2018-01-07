package com.ccx.models.util;

/**
 * Created by zhaotm on 2017/7/12.
 */
public class RateInValidRuntimeException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public RateInValidRuntimeException(String message) {
        super(message);
    }

    public RateInValidRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
