package com.example.JIRA.TaskManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Priority {

    ESCALATION(1, "Escalation"),
    URGENT(2, "Urgent"),
    HIGH(3, "High"),
    MEDIUM(4, "Medium"),
    LOW(5, "Low");

    private int id;
    private String priority;
}
