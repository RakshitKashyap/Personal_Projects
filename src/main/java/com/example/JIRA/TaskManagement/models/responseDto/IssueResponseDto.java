package com.example.JIRA.TaskManagement.models.responseDto;

import com.example.JIRA.TaskManagement.utils.IssueStatus;
import com.example.JIRA.TaskManagement.utils.IssueType;
import com.example.JIRA.TaskManagement.utils.Priority;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class IssueResponseDto {

    private String issueKey;

    private String description;

    private IssueType issueType;

    private Priority priority;

    private String projectId;

    private MiniUserResponseDto assignee;

    private MiniUserResponseDto reportTo;

    private IssueStatus issueStatus;

}
