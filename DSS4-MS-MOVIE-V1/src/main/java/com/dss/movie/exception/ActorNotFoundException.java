package com.dss.movie.exception;

public class ActorNotFoundException extends AbstractRuntimeException {

    public ActorNotFoundException(String message) {
        super(message);
    }
}
