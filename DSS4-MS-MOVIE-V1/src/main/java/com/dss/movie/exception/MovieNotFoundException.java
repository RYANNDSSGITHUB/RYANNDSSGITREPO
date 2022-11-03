package com.dss.movie.exception;

public class MovieNotFoundException extends AbstractRuntimeException {

    public MovieNotFoundException() {

    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
