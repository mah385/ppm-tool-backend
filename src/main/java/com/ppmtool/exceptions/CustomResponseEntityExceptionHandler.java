package com.ppmtool.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<ProjectIdExceptionResponse> handleProjectIdException(ProjectIdException e, WebRequest webRequest) {
        return new ResponseEntity<>(new ProjectIdExceptionResponse(e.getMessage()), e.getHttpStatus());
    }

}