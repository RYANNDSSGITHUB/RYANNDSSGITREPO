package com.dss.actor.exception;

public class MovieNotFoundException extends AbstractRuntimeException {

    public MovieNotFoundException(String message) {
        super(message);
    }
}
