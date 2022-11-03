package com.dss.actor.exception;

public abstract class AbstractRuntimeException extends RuntimeException {
    
    public AbstractRuntimeException() {

    }

    public AbstractRuntimeException(String message) {
        super(message);
    }
}
