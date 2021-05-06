package com.xebia.tennisleagueserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InsufficientParticipantsException extends  RuntimeException{
        public InsufficientParticipantsException(String message, Throwable th) {
            super(message,th);
        }
        public InsufficientParticipantsException(String message) {
            super(message);
        }
}
