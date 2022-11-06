package com.dss.movie.exception;

public class MovieNotFoundException extends AbstractRuntimeException {

    public MovieNotFoundException(String message) {
        super(message);
    }
}
