package com.example.JIRA.TaskManagement.service.impl;

import com.example.JIRA.TaskManagement.exceptions.CustomException;
import com.example.JIRA.TaskManagement.models.entity.Issue;
import com.example.JIRA.TaskManagement.models.requestDto.IssueCreateRequest;
import com.example.JIRA.TaskManagement.models.requestDto.IssueFilterRequestDto;
import com.example.JIRA.TaskManagement.models.responseDto.IssueResponseDto;
import com.example.JIRA.TaskManagement.models.responseDto.MiniUserResponseDto;
import com.example.JIRA.TaskManagement.repository.IssueRepository;
import com.example.JIRA.TaskManagement.service.IssueService;
import com.example.JIRA.TaskManagement.service.UserService;
import com.example.JIRA.TaskManagement.utils.CatchableErrors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserService userService;

    @Override
    public IssueResponseDto addNewIssue(IssueCreateRequest request) {

        Issue issue = new Issue();
        if(Objects.isNull(request.getProjectKey())){
            throw new CustomException(CatchableErrors.NO_SUCH_PROJECT_AVAILABLE);
        }
        int count = (getIssuesByProjectId(request.getProjectKey(), true)).size();
        String issueId = request.getProjectKey()+"_"+request.getIssueType().getDef()+"_"+(count+1);

        issue.setIssueKey(issueId);
        issue.setProjectId(request.getProjectKey());
        issue.setIssueUuid(UUID.randomUUID().toString());
        issue.setDescription(request.getDescription());
        issue.setIssueType(request.getIssueType());
        issue.setPriority(request.getPriority());
        issue.setAssignee(request.getAssignee());
        issue.setReportTo(request.getReportTo());
        issue.setIssueStatus(request.getIssueStatus());
        issue.setCreatedBy(request.getReportTo());
        issue.setCreatedOn(LocalDateTime.now());
        issue.setModifiedBy(request.getReportTo());
        issue.setModifiedOn(LocalDateTime.now());
        issue.setStatus(true);
        issue = issueRepository.save(issue);

        return convertToResponse(issue);
    }

    private IssueResponseDto convertToResponse(Issue issue) {

        IssueResponseDto responseDto = new IssueResponseDto();

        responseDto.setIssueKey(issue.getIssueKey());
        responseDto.setDescription(issue.getDescription());
        responseDto.setIssueType(issue.getIssueType());
        responseDto.setPriority(issue.getPriority());
        responseDto.setProjectId(issue.getProjectId());
        responseDto.setIssueStatus(issue.getIssueStatus());
        responseDto.setAssignee(convertToMiniUserResponseDto(issue.getAssignee()));
        responseDto.setReportTo(convertToMiniUserResponseDto(issue.getReportTo()));

        return responseDto;
    }

    private MiniUserResponseDto convertToMiniUserResponseDto(String username) {
        return userService.convertToMiniUserResponse(username);
    }

    @Override
    public List<IssueResponseDto> getAllIssues() {
        List<Issue> issueList = issueRepository.findAll();

        if (Objects.isNull(issueList)|| issueList.isEmpty()){
            throw new CustomException(CatchableErrors.NO_RECORDS_AVAILABLE);
        }
        List<IssueResponseDto> responseDtoList = new ArrayList<>();
        for(Issue issue:issueList){
            responseDtoList.add(convertToResponse(issue));
        }

        return responseDtoList;
    }

    @Override
    public IssueResponseDto getIssuesByIssueId(String issueId) {
        if(issueId.trim().isEmpty()){
            throw new CustomException(CatchableErrors.INVALID_INPUT);
        }
        Issue issue = issueRepository.findByIssueKey(issueId);
        if(Objects.isNull(issue)){
            throw new CustomException(CatchableErrors.NO_SUCH_ISSUE_AVAILABLE);
        }
        return convertToResponse(issue);
    }

    @Override
    public List<IssueResponseDto> filterRecords(IssueFilterRequestDto requestDto) {
        return null;
    }

    @Override
    public List<IssueResponseDto> getIssuesByProjectId(String projectId, boolean recordCreation) {

        if(projectId.trim().isEmpty()){
            throw new CustomException(CatchableErrors.INVALID_INPUT);
        }
        List<Issue> issueList = issueRepository.findByProjectId(projectId);
        if((Objects.isNull(issueList) || issueList.isEmpty()) && !recordCreation ){
            throw new CustomException(CatchableErrors.NO_ISSUE_AVAILABLE);
        }
        else if ((Objects.isNull(issueList) || issueList.isEmpty()) && recordCreation ){
            return new ArrayList<>();
        }

        return issueList.stream().map(issue->convertToResponse(issue)).collect(Collectors.toList());

    }
}
