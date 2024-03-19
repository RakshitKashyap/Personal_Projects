package com.example.JIRA.TaskManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CatchableErrors {

    USER_NAME_ALREADY_EXIST(400, "USER NAME ALREADY EXIST"),
    NO_SUCH_USER_EXIST(404, "USER NOT EXIST, INVALID USERNAME"),
    INVALID_INPUT(422, "INVALID_INPUT"),
    USER_EMAIL_ALREADY_REGISTERED(400, "USER EMAIL ALREADY REGISTERED"),
    USER_MOBILE_ALREADY_EXIST(400, "USER MOBILE ALREADY REGISTERED"),
    NO_RECORDS_AVAILABLE(204, "NO RECORDS AVAILABLE"),
    NO_SUCH_PROJECT_AVAILABLE(404, "NO SUCH PROJECT AVAILABLE"),
    NO_SUCH_ISSUE_AVAILABLE(404,"NO SUCH ISSUE AVAILABLE"),
    NO_ISSUE_AVAILABLE(201,"NO ISSUE AVAILABLE" );






    private int statusCode;
    private String message;

}
