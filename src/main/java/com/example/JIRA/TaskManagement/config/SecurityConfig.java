package com.example.JIRA.TaskManagement.config;

import com.example.JIRA.TaskManagement.service.impl.UserInfoUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {

    // authentication
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserInfoUserDetailsService();

        /**
         * now if you try to un the application
         *
         * follow steps are done
         * .> create user
         * >load user from db
         * > authenticate it
         * > but to pass it through you'll need an authenttication Provider
         */
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        log.info("___________new api is initiated as :: {}", http.toString());
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/v1/basic/ping", "/v1/basic/ping/{num}").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/v1/project/**").authenticated()
                .and().formLogin().and().build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Authentication Provider is the final step before authenticating a user
     * it will need a username and respective password
     */
}
