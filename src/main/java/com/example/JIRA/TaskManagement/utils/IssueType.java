package com.example.JIRA.TaskManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IssueType {

    EPIC("EP","EPIC"),
    STORY("ST","STORY"),
    TASK("TK","TASK"),
    BUG("BG","BUG");

    private String def;
    private String issue;
}
