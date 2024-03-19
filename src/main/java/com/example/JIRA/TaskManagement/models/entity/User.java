package com.example.JIRA.TaskManagement.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * First step > create a USER ENTITY WHICH STORE BASIC USER DETAILS AND LATER ON STORED IN DB
 *
 * NEXT CREATE A CORRESPONDING REPO TO LOAD IT INTO DB
 *
 * NEXT CREATE A USERDETAIL SERVICE CLASS WHICH IMPLEMENTS USERDETAILSSERVICE WHICH IS USED FOR AUTHENTICATING USER
 * IN SECURITY CONFIG
 */


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String userUUID;

   @Column(unique = true)
   private String username;

   @Column(unique = true)
   private String email;

   private String password;

   private String FirstName;

   private String lastName;

   @Column(unique = true)
   private long mobile;

   private String role;

   private boolean status;

}
