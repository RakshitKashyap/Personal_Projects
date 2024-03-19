package com.example.JIRA.TaskManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IssueStatus {

    OPEN,
    IN_PROGRESS,
    RESOLVED,
    CLOSED,
    RE_OPENED,
    ON_HOLD,
    PENDING,
    BLOCKED,
    DEFERRED,
    NEEDS_ATTENTION;

}
