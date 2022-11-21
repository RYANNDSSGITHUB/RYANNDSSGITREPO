package com.dss.exception;

import org.apache.http.auth.AuthenticationException;

public class JwtMissingTokenException extends AuthenticationException {

    public JwtMissingTokenException(String message) {
        super(message);
    }
}
