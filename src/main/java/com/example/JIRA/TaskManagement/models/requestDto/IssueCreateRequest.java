package com.example.JIRA.TaskManagement.models.requestDto;

import com.example.JIRA.TaskManagement.utils.IssueStatus;
import com.example.JIRA.TaskManagement.utils.IssueType;
import com.example.JIRA.TaskManagement.utils.Priority;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IssueCreateRequest {

    @NonNull
    private String description;

    @NonNull
    private IssueType issueType;

    @NonNull
    private Priority priority;

    @NonNull
    private String projectKey;

    private String assignee;

    private String reportTo;

    private IssueStatus issueStatus;
}
