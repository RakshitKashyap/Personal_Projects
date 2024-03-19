package com.example.JIRA.TaskManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProjectStatus {

    NOT_STARTED,
    IN_PROGRESS,
    ON_HOLD,
    COMPLETED,
    CANCELLED,
    BLOCKED,
    APPROVAL_PENDING,
    DELAYED,
    UNDER_REVIEW;
}
