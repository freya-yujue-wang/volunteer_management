package com.example.volunteer_management.controller;

import lombok.extern.slf4j.Slf4j;
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

    @GetMapping(path = "/getUserById/{id}", produces = "application/json")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = null;
        try {
            user = userService.findUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/updateUser", produces = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            User u = userService.updateUser(user);
            return ResponseEntity.ok(u);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(user);
        }
    }

    @PutMapping(path = "/resetPassword/{id}", produces = "application/json")
    public ResponseEntity<User> resetPassword(@PathVariable("id") int id) {
        try {
            User u = userService.resetPassword(id);
            return ResponseEntity.ok(u);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/deleteUser/{userName}", produces = "application/json")
    public ResponseEntity<User> deleteUser(@PathVariable("userName") String userName) {
        try {
            User deleteUser = userService.deleteUser(userName);
            return ResponseEntity.ok(deleteUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }






}
