package com.xebia.tennisleagueserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CanNotProceedException extends  RuntimeException{
    public CanNotProceedException(String message, Throwable th) {
        super(message,th);
    }
    public CanNotProceedException(String message) {
        super(message);
    }
}