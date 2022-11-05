package com.dss.movie.exception;

public abstract class AbstractRuntimeException extends RuntimeException {

    public AbstractRuntimeException() {

    }

    public AbstractRuntimeException(String message) {
        super(message);
    }
}
