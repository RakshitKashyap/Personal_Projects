package com.example.JIRA.TaskManagement.repository;

import com.example.JIRA.TaskManagement.models.entity.Association;
import com.example.JIRA.TaskManagement.utils.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociationRepository extends JpaRepository<Association, Long> {

    List<Association> findByMainKeyAndAssociateTypeOrderBySequenceAsc(String toString, Associate teamMember);
}
