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

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/addUser", produces = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            User addedUser = userService.addUser(user);
            return ResponseEntity.ok(addedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(user);
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
    public HttpStatus deleteUser(@PathVariable("userName") String userName) {
        try {
            userService.deleteUser(userName);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }






}
