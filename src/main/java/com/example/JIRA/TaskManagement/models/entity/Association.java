package com.example.JIRA.TaskManagement.models.entity;

import com.example.JIRA.TaskManagement.utils.Associate;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Data
public class Association {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Enumerated(value = EnumType.STRING)
    private Associate associateType;

    private String mainKey;

    private String associateKey;

    private int sequence;

    private boolean status;
}
