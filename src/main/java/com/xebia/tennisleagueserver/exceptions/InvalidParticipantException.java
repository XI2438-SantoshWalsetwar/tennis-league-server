package com.xebia.tennisleagueserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParticipantException extends RuntimeException{
        public InvalidParticipantException(String message, Throwable th) {
            super(message,th);
        }
        public InvalidParticipantException(String message) {
            super(message);
        }
}
