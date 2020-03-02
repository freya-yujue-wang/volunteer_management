package com.example.volunteer_management.controller;

import com.example.volunteer_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class UserController {
    @Autowired
    private UserService userService;


}
