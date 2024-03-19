package com.example.JIRA.TaskManagement.controller;

import com.example.JIRA.TaskManagement.exceptions.CustomException;
import com.example.JIRA.TaskManagement.models.entity.Project;
import com.example.JIRA.TaskManagement.models.requestDto.ProjectCreateDto;
import com.example.JIRA.TaskManagement.models.responseDto.ProjectResponseDto;
import com.example.JIRA.TaskManagement.service.ProjectService;
import com.example.JIRA.TaskManagement.utils.CatchableErrors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/viewAll")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity viewAllProject(){

        List<ProjectResponseDto> projects = projectService.getAllProjects();
        if(Objects.isNull(projects)||projects.isEmpty()){
            throw new CustomException(CatchableErrors.NO_RECORDS_AVAILABLE);
        }
        return new ResponseEntity(projects, HttpStatus.OK);
    }


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity addNewProject(@Validated @RequestBody ProjectCreateDto request){
        Project project = projectService.addNewProject(request);
        return new ResponseEntity(project, HttpStatus.CREATED);
    }
}
