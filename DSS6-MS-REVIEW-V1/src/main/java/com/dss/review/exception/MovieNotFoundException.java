package com.dss.review.exception;

public class MovieNotFoundException extends AbstractRuntimeException {

    public MovieNotFoundException(String message) {
        super(message);
    }
}
