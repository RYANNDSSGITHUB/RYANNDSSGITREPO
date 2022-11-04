package com.dss.actor.exception;

public class MovieNotFoundException extends AbstractRuntimeException {

    public MovieNotFoundException() {

    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
