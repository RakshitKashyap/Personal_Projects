package com.example.JIRA.TaskManagement.service.impl;

import com.example.JIRA.TaskManagement.exceptions.CustomException;
import com.example.JIRA.TaskManagement.models.entity.User;
import com.example.JIRA.TaskManagement.models.requestDto.UserCreateDto;
import com.example.JIRA.TaskManagement.models.responseDto.MiniUserResponseDto;
import com.example.JIRA.TaskManagement.repository.AssociationRepository;
import com.example.JIRA.TaskManagement.repository.UserRepository;
import com.example.JIRA.TaskManagement.service.UserService;
import com.example.JIRA.TaskManagement.utils.CatchableErrors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AssociationRepository associationRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MiniUserResponseDto registerNewUser(UserCreateDto createDto){

        //Checks for Existing email mobile or username

        User checkUserName = userRepository.findByUsername(createDto.getUsername());
        User checkEmail = userRepository.findByEmail(createDto.getEmail());
        User checkMobile = userRepository.findByMobile(createDto.getMobile());

        if(Objects.nonNull(checkUserName)){
            throw new CustomException(CatchableErrors.USER_NAME_ALREADY_EXIST);
        }

        if(Objects.nonNull(checkEmail)){
            throw new CustomException(CatchableErrors.USER_EMAIL_ALREADY_REGISTERED);
        }

        if(Objects.nonNull(checkMobile)){
            throw new CustomException(CatchableErrors.USER_MOBILE_ALREADY_EXIST);
        }

        User user = new User();
        user.setUserUUID(UUID.randomUUID().toString());
        user.setEmail(createDto.getEmail());
        user.setUsername(createDto.getUsername());
        user.setPassword(passwordEncoder.encode(createDto.getPassword()));
        user.setFirstName(createDto.getFirstName());
        user.setLastName(createDto.getLastName());
        user.setMobile(createDto.getMobile());
        user.setRole(createDto.getRole());
        user.setStatus(true);

        user = userRepository.save(user);

        MiniUserResponseDto responseDto = new MiniUserResponseDto();

        responseDto.setId(user.getId());
        responseDto.setUsername(user.getUsername());
        responseDto.setEmail(user.getEmail());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setMobile(user.getMobile());
        return responseDto;
    }

    @Override
    public Optional<List<MiniUserResponseDto>> getAllUsers() {

        List<User> userList = userRepository.findAll();
        List<MiniUserResponseDto> responseDtoList = new ArrayList<>();

        for (User user: userList){

            responseDtoList.add(convertToMiniUserResponse(user.getUsername()));
        }
        return Optional.of(responseDtoList);
    }

    @Override
    public MiniUserResponseDto convertToMiniUserResponse(String username) {
        if(username.trim().isEmpty()){
            throw new CustomException(CatchableErrors.NO_RECORDS_AVAILABLE);
        }
        User user =  validateUserExistence(username);

        MiniUserResponseDto responseDto = new MiniUserResponseDto();

        responseDto.setId(user.getId());
        responseDto.setUsername(user.getUsername());
        responseDto.setEmail(user.getEmail());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setMobile(user.getMobile());

        return responseDto;
    }

    private User validateUserExistence(String username) {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)){
            throw new CustomException(CatchableErrors.NO_SUCH_USER_EXIST);
        }
        return user;
    }
}
