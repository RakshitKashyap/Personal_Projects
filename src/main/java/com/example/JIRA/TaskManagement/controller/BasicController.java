package com.example.JIRA.TaskManagement.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/basic")
public class BasicController {

    @GetMapping("/ping")
    public String yup(){
        return "<h1>hey this is checking." +
                "This is not safe end point......</h1>";
    }

    @GetMapping("/ping/{num}")
    public String num(@PathVariable(name = "num") String num){
        return "<h1>the result is "+num+"</h1>";
    }

}
