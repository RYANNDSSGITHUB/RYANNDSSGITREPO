package com.dss.exception;

public class CustomErrorException extends Exception {

    public CustomErrorException() {

    }

    public CustomErrorException(String message) {
        super(message);
    }
}
