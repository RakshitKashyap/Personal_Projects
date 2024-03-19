package com.example.JIRA.TaskManagement.models.entity;

import com.example.JIRA.TaskManagement.utils.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="project_name", unique = true)
    private String projectName;

    private String projectUuid;

    private String projectDescription;

    @Column(name="project_key", unique = true)
    private String projectKey;

    @Column(name = "project_lead")
    private String leadUserId;

    private int teamSize;

    private LocalDate startDate;

    private LocalDate endDate;

    private ProjectStatus projectStatus;

    private boolean status;

}
