package com.dss.review.exception;

public class MovieNotFoundException extends AbstractRuntimeException {

    public MovieNotFoundException() {

    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
