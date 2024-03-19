package com.example.JIRA.TaskManagement.models.requestDto;

import com.example.JIRA.TaskManagement.utils.IssueStatus;
import com.example.JIRA.TaskManagement.utils.IssueType;
import com.example.JIRA.TaskManagement.utils.Priority;
import lombok.Getter;

@Getter
public class IssueFilterRequestDto {

    private IssueType issueType;

    private Priority priority;

    private String projectId;

    private String assignee;

    private String reportTo;

    private IssueStatus issueStatus;

}
