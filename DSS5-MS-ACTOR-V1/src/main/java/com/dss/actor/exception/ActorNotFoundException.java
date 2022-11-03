package com.dss.actor.exception;

public class ActorNotFoundException extends AbstractRuntimeException {

    public ActorNotFoundException() {

    }

    public ActorNotFoundException(String message) {
        super(message);
    }
}
