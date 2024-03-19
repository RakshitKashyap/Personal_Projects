package com.example.JIRA.TaskManagement.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorOutput {

    private int statusCode;
    private String message;
    private LocalDateTime timeStamp;

}
