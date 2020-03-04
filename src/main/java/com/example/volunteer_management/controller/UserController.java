package com.example.volunteer_management.controller;

import com.example.volunteer_management.model.Activity;
import com.example.volunteer_management.model.User;
import com.example.volunteer_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/addUser", produces = "application/json")
    public HttpStatus addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping(path = "/getUsers", produces = "application/json")
    public HttpEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @PutMapping(path = "/updateUser", produces = "application/json")
    public HttpStatus updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PutMapping(path = "/deleteUser/{userName}", produces = "application/json")
    public HttpStatus deleteCourse(@PathVariable("userName") String userName) {
        try {
            userService.deleteUser(userName);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }






}
