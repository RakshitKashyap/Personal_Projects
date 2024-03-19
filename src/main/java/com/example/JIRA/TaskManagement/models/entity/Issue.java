package com.example.JIRA.TaskManagement.models.entity;

import com.example.JIRA.TaskManagement.utils.IssueStatus;
import com.example.JIRA.TaskManagement.utils.IssueType;
import com.example.JIRA.TaskManagement.utils.Priority;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Getter
@Setter
@Table(name = "issue")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "issue_key")
    private String issueKey;

    private String issueUuid;

    @Column(name = "description" )
    private String description;

    @Enumerated( value = EnumType.STRING)
    @Column(name = "issue_type")
    private IssueType issueType;

    @Enumerated( value = EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    private String projectId;

    private String assignee;

    private String reportTo;

    @Enumerated( value = EnumType.STRING)
    private IssueStatus issueStatus;

    private LocalDateTime createdOn;

    private LocalDateTime modifiedOn;

    private String createdBy;

    private String modifiedBy;

    private boolean status;

}
