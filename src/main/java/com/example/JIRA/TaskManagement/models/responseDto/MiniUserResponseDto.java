package com.example.JIRA.TaskManagement.models.responseDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MiniUserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String FirstName;
    private String lastName;
    private long mobile;

}
