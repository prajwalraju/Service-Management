package com.serviceManagementAPI.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@ControllerAdvice
public class GlobalExceptionHandler {

    // exception 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> HandleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), resourceNotFoundException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    // exception 400
    @ExceptionHandler(ResourceBadException.class)
    public ResponseEntity<?> HandleResourceDeletedException(ResourceBadException resourceDeletedException, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), resourceDeletedException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // exception 422
    @ExceptionHandler(ResourceAlreadyProcessedException.class)
    public ResponseEntity<?> HandleResourceAlreadyPresentException(ResourceAlreadyProcessedException resourceAlreadyPresentException, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), resourceAlreadyPresentException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // exception 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> HandleException(Exception exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
