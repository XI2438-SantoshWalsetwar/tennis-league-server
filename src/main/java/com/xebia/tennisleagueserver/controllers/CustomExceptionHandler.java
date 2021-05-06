package com.xebia.tennisleagueserver.controllers;

import com.xebia.tennisleagueserver.exceptions.*;
import com.xebia.tennisleagueserver.model.ErrorResponse;
import com.xebia.tennisleagueserver.model.SubError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.util.*;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(NoSuchElementFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementFoundException(NoSuchElementFoundException ex, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setErrorCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setTimestamp(ZonedDateTime.now());
        LOG.error(String.valueOf(errorResponse));
        return  new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoContentFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoContentException(NoContentFoundException ex, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setErrorCode(String.valueOf(HttpStatus.NO_CONTENT.value()));
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setTimestamp(ZonedDateTime.now());
        LOG.error(String.valueOf(errorResponse));
        return  new ResponseEntity<>(errorResponse,HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(RegistrationFullException.class)
    public ResponseEntity<ErrorResponse> handleOverRegistration(RegistrationFullException ex, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setErrorCode(String.valueOf(HttpStatus.FORBIDDEN.value()));
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setTimestamp(ZonedDateTime.now());
        LOG.error(String.valueOf(errorResponse));
        return  new ResponseEntity<>(errorResponse,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CanNotProceedException.class)
    public ResponseEntity<ErrorResponse> handleCanNotProceed(CanNotProceedException ex, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setErrorCode(String.valueOf(HttpStatus.FORBIDDEN.value()));
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setTimestamp(ZonedDateTime.now());
        LOG.error(String.valueOf(errorResponse));
        return  new ResponseEntity<>(errorResponse,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidParticipantException.class)
    public ResponseEntity<ErrorResponse> handleInvalidParticipantException(InvalidParticipantException ex, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setTimestamp(ZonedDateTime.now());
        LOG.error(String.valueOf(errorResponse));
        return  new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientParticipantsException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientParticipants(InsufficientParticipantsException ex, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setErrorCode(String.valueOf(HttpStatus.FORBIDDEN.value()));
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setTimestamp(ZonedDateTime.now());
        LOG.error(String.valueOf(errorResponse));
        return  new ResponseEntity<>(errorResponse,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> onConstraintValidationException(ConstraintViolationException e,HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setErrorMessage("VALIDATION ERROR");
        errorResponse.setTimestamp(ZonedDateTime.now());
        List<SubError> subErrors = new ArrayList<>();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            subErrors.add(new SubError(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        errorResponse.setSubErrors(subErrors);
        LOG.error(String.valueOf(errorResponse));
        return  new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                 HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getContextPath());
        errorResponse.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setErrorMessage("VALIDATION ERROR");
        errorResponse.setTimestamp(ZonedDateTime.now());
        List<SubError> subErrors = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            subErrors.add(new SubError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        errorResponse.setSubErrors(subErrors);
        LOG.error(String.valueOf(errorResponse));
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getContextPath());
        errorResponse.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setErrorMessage("VALIDATION ERROR");
        errorResponse.setTimestamp(ZonedDateTime.now());
        List<SubError> subErrors = new ArrayList<>();
        subErrors.add(new SubError(e.getParameterName(), "Missing Required Input Parameter"));
        errorResponse.setSubErrors(subErrors);
        LOG.error(String.valueOf(errorResponse));
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleError(MethodArgumentTypeMismatchException e,HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setErrorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setErrorMessage("VALIDATION ERROR");
        errorResponse.setTimestamp(ZonedDateTime.now());
        List<SubError> subErrors = new ArrayList<>();
        subErrors.add(new SubError(e.getName(),e.getMessage()));
        errorResponse.setSubErrors(subErrors);
        LOG.error(String.valueOf(errorResponse));
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
