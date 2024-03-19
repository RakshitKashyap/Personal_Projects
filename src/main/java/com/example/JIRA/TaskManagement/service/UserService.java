package com.example.JIRA.TaskManagement.service;

import com.example.JIRA.TaskManagement.models.entity.User;
import com.example.JIRA.TaskManagement.models.requestDto.UserCreateDto;
import com.example.JIRA.TaskManagement.models.responseDto.MiniUserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    MiniUserResponseDto registerNewUser(UserCreateDto createDto);

    Optional<List<MiniUserResponseDto>> getAllUsers();

    MiniUserResponseDto convertToMiniUserResponse(String username);
}
