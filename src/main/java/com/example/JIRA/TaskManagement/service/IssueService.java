package com.example.JIRA.TaskManagement.service;

import com.example.JIRA.TaskManagement.models.entity.Issue;
import com.example.JIRA.TaskManagement.models.requestDto.IssueCreateRequest;
import com.example.JIRA.TaskManagement.models.requestDto.IssueFilterRequestDto;
import com.example.JIRA.TaskManagement.models.responseDto.IssueResponseDto;

import java.util.List;

public interface IssueService {
    IssueResponseDto addNewIssue(IssueCreateRequest request);

    List<IssueResponseDto> getAllIssues();

    IssueResponseDto getIssuesByIssueId(String issueId);

    List<IssueResponseDto> filterRecords(IssueFilterRequestDto requestDto);

    List<IssueResponseDto> getIssuesByProjectId(String projectId, boolean recordCreation);
}
