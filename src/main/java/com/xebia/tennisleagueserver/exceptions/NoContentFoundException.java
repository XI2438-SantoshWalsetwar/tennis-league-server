package com.xebia.tennisleagueserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentFoundException extends RuntimeException {
    public NoContentFoundException(String message, Throwable th) {
        super(message,th);
    }
    public NoContentFoundException(String message) {
        super(message);
    }
}
