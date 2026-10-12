package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
    @ExceptionHandler(StudentException.class)
    public ResponseEntity<Map<String, Object>> handleStudentException(StudentException studentException) 
    {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "failure");
        response.put("msg", studentException.getMessage());
        response.put("statusCode", studentException.getHttpStatus().value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}