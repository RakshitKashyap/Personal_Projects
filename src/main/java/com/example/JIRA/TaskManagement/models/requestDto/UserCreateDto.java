package com.example.JIRA.TaskManagement.models.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@Getter
@Setter
@Validated
public class UserCreateDto {


    @NonNull
    private String username;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String firstName;

    private String lastName;

    @NonNull
    private long mobile;

    @NonNull
    private String role;

}
