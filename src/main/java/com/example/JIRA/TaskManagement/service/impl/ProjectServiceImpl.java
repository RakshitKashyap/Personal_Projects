package com.example.JIRA.TaskManagement.service.impl;

import com.example.JIRA.TaskManagement.exceptions.CustomException;
import com.example.JIRA.TaskManagement.models.entity.Association;
import com.example.JIRA.TaskManagement.models.entity.Issue;
import com.example.JIRA.TaskManagement.models.entity.Project;
import com.example.JIRA.TaskManagement.models.entity.User;
import com.example.JIRA.TaskManagement.models.requestDto.MiniUserRequestDto;
import com.example.JIRA.TaskManagement.models.requestDto.ProjectCreateDto;
import com.example.JIRA.TaskManagement.models.responseDto.MiniUserResponseDto;
import com.example.JIRA.TaskManagement.models.responseDto.ProjectResponseDto;
import com.example.JIRA.TaskManagement.repository.AssociationRepository;
import com.example.JIRA.TaskManagement.repository.ProjectRepository;
import com.example.JIRA.TaskManagement.repository.UserRepository;
import com.example.JIRA.TaskManagement.service.ProjectService;
import com.example.JIRA.TaskManagement.service.UserService;
import com.example.JIRA.TaskManagement.utils.Associate;
import com.example.JIRA.TaskManagement.utils.CatchableErrors;
import com.example.JIRA.TaskManagement.utils.ProjectStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final AssociationRepository associationRepository;
    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    public ProjectServiceImpl(ProjectRepository projectRepository, AssociationRepository associationRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.associationRepository = associationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Project addNewProject(ProjectCreateDto request){

        Project project = new Project();

        project.setProjectName(request.getProjectName());
        project.setProjectDescription(request.getProjectDescription());
        project.setProjectKey(request.getKey());
        project.setProjectUuid(UUID.randomUUID().toString());
        project.setLeadUserId(request.getProjectLead().getUsername());
        project.setTeamSize(request.getTeamMembers().size());
        project.setStatus(true);

        if(Objects.isNull(request.getProjectStatus())){
            project.setProjectStatus(ProjectStatus.IN_PROGRESS);
        }
        else{
            project.setProjectStatus(request.getProjectStatus());
        }

        project = projectRepository.save(project);
        int count =0;
        for(MiniUserRequestDto miniUser:request.getTeamMembers()){

            User user = userRepository.findByUsername(miniUser.getUsername());

            Association association = new Association();
            association.setMainKey(project.getId().toString());
            association.setAssociateType(Associate.TEAM_MEMBER);
            association.setAssociateKey(user.getUsername());
            association.setSequence(++count);
            association.setStatus(true);
            associationRepository.save(association);
        }

        return project;
    }

    @Override
    public List<ProjectResponseDto> getAllProjects() {
        List<Project> projectList = projectRepository.findAll();
        if(Objects.isNull(projectList)||projectList.isEmpty()){
            throw new CustomException(CatchableErrors.NO_RECORDS_AVAILABLE);
        }
        List<ProjectResponseDto> responseDtoList = new ArrayList<>();
        projectList.stream().forEach(project -> responseDtoList.add(convertToResponse(project)));

        return responseDtoList;
    }

    private ProjectResponseDto convertToResponse(Project project) {

        ProjectResponseDto responseDto = new ProjectResponseDto();
        responseDto.setId(project.getId());
        responseDto.setProjectName(project.getProjectName());
        responseDto.setProjectDescription(project.getProjectDescription());
        responseDto.setProjectKey(project.getProjectKey());
        responseDto.setProjectLead(convertToMiniUserResponseDto(project.getLeadUserId()));
        responseDto.setTeamMembers(getTeamMembers(project.getId()));
        responseDto.setIssues(getAllAssociatedIssues(project.getId()));
        responseDto.setTeamSize(getTeamMembers(project.getId()).size());
        responseDto.setProjectStatus(project.getProjectStatus());

        return responseDto;
    }

    private List<Issue> getAllAssociatedIssues(Long id) {
        //:todo :: create the linked issue functionality from Projects Side

        return null;
    }

    private List<MiniUserResponseDto> getTeamMembers(Long id) {

        List<MiniUserResponseDto> responseDtoList = new ArrayList<>();

        List<Association> associations = associationRepository.findByMainKeyAndAssociateTypeOrderBySequenceAsc(id.toString(), Associate.TEAM_MEMBER);
        if(associations==null || associations.isEmpty()){
            return null;
        }
        for (Association association: associations){
            User user = userRepository.findByUsername(association.getAssociateKey());
            responseDtoList.add(convertToMiniUserResponseDto(user.getUsername()));
        }
        return responseDtoList;
    }

    private MiniUserResponseDto convertToMiniUserResponseDto(String leadUserId) {
        User user = userRepository.findByUsername(leadUserId);
        if(Objects.isNull(user)){
            return null;
        }
        MiniUserResponseDto responseDto = new MiniUserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setUsername(user.getUsername());
        responseDto.setEmail(user.getEmail());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setMobile(user.getMobile());
        return responseDto;
    }
}
