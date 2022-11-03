package com.dss.movie.exception;

public class InvalidMovieException extends AbstractRuntimeException {

    public InvalidMovieException() {

    }

    public InvalidMovieException(String message) {
        super(message);
    }
}
