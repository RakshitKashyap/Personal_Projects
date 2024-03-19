package com.example.JIRA.TaskManagement.controller;

import com.example.JIRA.TaskManagement.exceptions.CustomException;
import com.example.JIRA.TaskManagement.models.entity.User;
import com.example.JIRA.TaskManagement.models.requestDto.UserCreateDto;
import com.example.JIRA.TaskManagement.models.responseDto.MiniUserResponseDto;
import com.example.JIRA.TaskManagement.service.UserService;
import com.example.JIRA.TaskManagement.utils.CatchableErrors;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity addNewUser(@RequestBody UserCreateDto createDto){
        log.info("initiating endpoint to register a new user");
        MiniUserResponseDto user = userService.registerNewUser(createDto);
        if(Objects.isNull(user))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/viewAll")
    public ResponseEntity getAllUsers(){

        Optional<List<MiniUserResponseDto>> optionalList = userService.getAllUsers();
        if(optionalList.isEmpty()){
            throw new CustomException(CatchableErrors.NO_RECORDS_AVAILABLE);
        }
        List<MiniUserResponseDto> responseDtoList = optionalList.get();

        return new ResponseEntity(responseDtoList, HttpStatus.OK);
    }

    @PostMapping("/test-request")
    public ResponseEntity testPostRequest() {
        return ResponseEntity.ok("POST request successful");
    }
}
