package com.dss.review.exception;

public abstract class AbstractRuntimeException extends RuntimeException {

    protected AbstractRuntimeException(String message) {
        super(message);
    }
}
