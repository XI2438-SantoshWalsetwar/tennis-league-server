package com.xebia.tennisleagueserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RegistrationFullException extends RuntimeException {
    public RegistrationFullException(String message, Throwable th) {
        super(message,th);
    }
    public RegistrationFullException(String message) {
        super(message);
    }
}
