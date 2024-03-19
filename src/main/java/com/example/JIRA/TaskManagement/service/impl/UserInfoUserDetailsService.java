package com.example.JIRA.TaskManagement.service.impl;
import com.example.JIRA.TaskManagement.config.UserInfoUserDetails;
import com.example.JIRA.TaskManagement.models.entity.User;
import com.example.JIRA.TaskManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Define it as a Component and have repository as autowired which will load the data from db
 *
 *
 * Now NOTICE THAT loadByUserName() method return [UserDetails] as so we need to create a new class
 * <UserInfoUserDetails> which will implements the UserDetails interface
 *
 *
 * after creating that class
 *
 * return the object of class just created
 *
 */

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = Optional.ofNullable(repository.findByUsername(username));
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

        /**
         * here we mapped the instance of User entity to UserInfoUserDetails
         *
         * and return it,
         *
         * if not then throws the error, <as user not found>
         */

    }
}

