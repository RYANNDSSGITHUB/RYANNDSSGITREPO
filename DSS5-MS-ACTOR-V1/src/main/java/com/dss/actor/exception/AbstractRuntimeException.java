package com.dss.actor.exception;

public abstract class AbstractRuntimeException extends RuntimeException {
    
    protected AbstractRuntimeException() {

    }

    protected AbstractRuntimeException(String message) {
        super(message);
    }
}
