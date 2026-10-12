package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class StudentException extends RuntimeException 
{

    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;

    public StudentException(String msg, HttpStatus httpStatus) 
    {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}