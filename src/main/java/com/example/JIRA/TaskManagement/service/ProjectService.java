package com.example.JIRA.TaskManagement.service;

import com.example.JIRA.TaskManagement.models.entity.Project;
import com.example.JIRA.TaskManagement.models.requestDto.ProjectCreateDto;
import com.example.JIRA.TaskManagement.models.responseDto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {
    Project addNewProject(ProjectCreateDto request);

    List<ProjectResponseDto> getAllProjects();
}
