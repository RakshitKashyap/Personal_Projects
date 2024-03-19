package com.example.JIRA.TaskManagement.models.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class MiniUserRequestDto {
    private String username;
    private String email;
}
