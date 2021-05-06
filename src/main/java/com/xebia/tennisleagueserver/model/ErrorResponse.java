package com.xebia.tennisleagueserver.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class ErrorResponse {
    private String errorMessage;
    private String errorCode;
    private ZonedDateTime timestamp;
    private String path;
    private List<SubError> subErrors;


}
