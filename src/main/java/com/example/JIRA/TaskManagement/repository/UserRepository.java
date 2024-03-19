package com.example.JIRA.TaskManagement.repository;

import com.example.JIRA.TaskManagement.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findByMobile(long mobile);
}
