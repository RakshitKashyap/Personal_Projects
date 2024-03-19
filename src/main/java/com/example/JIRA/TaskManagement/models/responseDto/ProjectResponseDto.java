package com.example.JIRA.TaskManagement.models.responseDto;

import com.example.JIRA.TaskManagement.models.entity.Issue;
import com.example.JIRA.TaskManagement.models.requestDto.MiniUserRequestDto;
import com.example.JIRA.TaskManagement.utils.ProjectStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProjectResponseDto {

    private Long id;
    private String projectName;
    private String projectDescription;
    private String projectKey;
    private MiniUserResponseDto projectLead;
    private List<MiniUserResponseDto> teamMembers;
    private List<Issue> issues;
    private int teamSize;
    private ProjectStatus projectStatus;

}
