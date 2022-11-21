package com.dss.exception;

import org.apache.http.auth.AuthenticationException;

public class JwtMalformedTokenException extends AuthenticationException {

    public JwtMalformedTokenException(String message) {
        super(message);
    }
}
