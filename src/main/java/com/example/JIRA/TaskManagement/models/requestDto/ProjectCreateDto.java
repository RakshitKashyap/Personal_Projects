package com.example.JIRA.TaskManagement.models.requestDto;

import com.example.JIRA.TaskManagement.models.entity.Issue;
import com.example.JIRA.TaskManagement.models.entity.User;
import com.example.JIRA.TaskManagement.utils.ProjectStatus;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectCreateDto {

    @NonNull
    private String projectName;

    private String projectDescription;

    @NonNull
    private String key;

    @NonNull
    private MiniUserRequestDto projectLead;

    private List<MiniUserRequestDto> teamMembers;

    private List<Issue> issues;

    private ProjectStatus projectStatus;

}
