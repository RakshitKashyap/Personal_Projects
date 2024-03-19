package com.example.JIRA.TaskManagement.repository;

import com.example.JIRA.TaskManagement.models.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    Issue findByIssueKey(String issueId);

    List<Issue> findByProjectId(String projectId);
}
