package com.ppmtool.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ProjectIdException extends RuntimeException {

    private HttpStatus httpStatus;

    public ProjectIdException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
