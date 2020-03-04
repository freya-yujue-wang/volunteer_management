package com.example.volunteer_management.controller;

import com.example.volunteer_management.model.Register;
import com.example.volunteer_management.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping(path = "/addRegister", produces = "application/json")
    public HttpStatus addRegister(@RequestBody Register register) {
        try {
            registerService.addRegister(register);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @DeleteMapping(path = "/deleteRegister/{id}", produces = "application/json")
    public HttpStatus deleteCourse(@PathVariable("id") int id) {
        try {
            registerService.deleteRegister(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping(path = "/getAllRegisterByUser/{userId}", produces = "application/json")
    public HttpEntity<List<Register>> getAllActivities(@PathVariable("userId") int userId) throws Exception {
        return new ResponseEntity<>(registerService.findAllActivitiesByUser(userId), HttpStatus.OK);
    }

    @PutMapping(path = "/updateState", produces = "application/json")
    public HttpStatus updateState(@RequestBody Register register) {
        try {
            registerService.updateState(register);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PutMapping(path = "/updateComment", produces = "application/json")
    public HttpStatus updateComment(@RequestBody Register register) {
        try {
            registerService.updateComment(register);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PutMapping(path = "/updateFeedback", produces = "application/json")
    public HttpStatus updateFeedback(@RequestBody Register register) {
        try {
            registerService.updateFeedback(register);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
