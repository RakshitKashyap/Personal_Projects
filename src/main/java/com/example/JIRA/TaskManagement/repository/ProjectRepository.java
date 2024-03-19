package com.example.JIRA.TaskManagement.repository;

import com.example.JIRA.TaskManagement.models.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, Long> {
}
