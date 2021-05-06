package com.xebia.tennisleagueserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchElementFoundException extends RuntimeException {
    public NoSuchElementFoundException(String message, Throwable th) {
        super(message,th);
    }
    public NoSuchElementFoundException(String message) {
        super(message);
    }
}
