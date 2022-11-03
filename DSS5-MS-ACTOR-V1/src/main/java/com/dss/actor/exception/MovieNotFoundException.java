package com.dss.actor.exception;

import com.dss.movie.exception.AbstractRuntimeException;

public class MovieNotFoundException extends AbstractRuntimeException {

    public MovieNotFoundException() {

    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
