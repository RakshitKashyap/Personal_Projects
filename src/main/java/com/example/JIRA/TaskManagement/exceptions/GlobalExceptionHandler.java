package com.example.JIRA.TaskManagement.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorOutput> handleCustomException(CustomException exception){
        ErrorOutput response = new ErrorOutput(exception.getErrorCode(), exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(exception.getErrorCode()));
    }
}
