package com.dss.movie.exception;

public abstract class AbstractRuntimeException extends RuntimeException {

    protected AbstractRuntimeException(String message) {
        super(message);
    }
}
