package com.example.JIRA.TaskManagement.controller;

import com.example.JIRA.TaskManagement.models.entity.Issue;
import com.example.JIRA.TaskManagement.models.entity.Project;
import com.example.JIRA.TaskManagement.models.requestDto.IssueCreateRequest;
import com.example.JIRA.TaskManagement.models.requestDto.IssueFilterRequestDto;
import com.example.JIRA.TaskManagement.models.responseDto.IssueResponseDto;
import com.example.JIRA.TaskManagement.service.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "/v1/issue")
public class IssueController {

    private final IssueService issueService;


    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/findAll")
    public ResponseEntity getAllIssue(){
        List<IssueResponseDto> issueList  = issueService.getAllIssues();
        return new ResponseEntity(issueList, HttpStatus.OK);
    }

    @GetMapping("/findByIssue/{issueId}")
    public ResponseEntity getIssueByIssueId(@PathVariable(name = "issueId")String issueId){
        IssueResponseDto issue  = issueService.getIssuesByIssueId(issueId);
        return new ResponseEntity(issue, HttpStatus.OK);
    }

    @GetMapping("/findByProject/{projectId}")
    public ResponseEntity getIssueByProjectId(@PathVariable(name = "projectId")String projectId){
        List<IssueResponseDto> issue  = issueService.getIssuesByProjectId(projectId, false);
        return new ResponseEntity(issue, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity addNewIssue(@Validated @RequestBody IssueCreateRequest request){
        IssueResponseDto issue = issueService.addNewIssue(request);
        return new ResponseEntity(issue, HttpStatus.CREATED);
    }

    @PostMapping("/issuesByFilter")
    public ResponseEntity getIssueByFilter(IssueFilterRequestDto requestDto){
        if(Objects.isNull(requestDto)){
            getAllIssue();
        }
        List<IssueResponseDto> issueList = issueService.filterRecords(requestDto);
        return new ResponseEntity(issueList, HttpStatus.OK);
    }


}
