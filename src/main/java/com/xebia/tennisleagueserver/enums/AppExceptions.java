package com.xebia.tennisleagueserver.enums;

import org.springframework.http.HttpStatus;

public enum AppExceptions {
    PARTICIPANT_NOT_FOUND("Participant Not Found"),
    MATCH_NOT_FOUND("Match Not Found"),
    REGISTRATION_FULL("Registration Full"),
    NO_PARTICIPANT_FOUND("No Participants Registered"),
    NO_MATCH_FOUND("No Match Scheduled"),
    INVALID_ROUND_NAME("Invalid Round Name"),
    INVALID_MATCH_PARTICIPANT("Participant not played this match"),
    INSUFFICIENT_PARTICIPANTS("Insufficient qualified participants"),
    ROUND_NOT_FINISHED("Current round yet to finish");


    public final String msg;

    private AppExceptions(String message){
        this.msg = message;
    }
}
