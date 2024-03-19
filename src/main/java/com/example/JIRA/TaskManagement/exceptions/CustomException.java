package com.example.JIRA.TaskManagement.exceptions;

import com.example.JIRA.TaskManagement.utils.CatchableErrors;

public class CustomException extends RuntimeException{

    private final CatchableErrors catchableErrors;

    public CustomException(CatchableErrors catchableErrors) {
        super(catchableErrors.getMessage());
        this.catchableErrors = catchableErrors;
    }

    public int getErrorCode(){
        return catchableErrors.getStatusCode();
    }
}
