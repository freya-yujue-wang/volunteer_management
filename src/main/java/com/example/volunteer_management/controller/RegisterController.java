package com.example.volunteer_management.controller;

import com.example.volunteer_management.model.Register;
import com.example.volunteer_management.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping(path = "/addRegister", produces = "application/json")
    public ResponseEntity<Register> addRegister(@RequestBody Register register) {
        try {
            Register addedRegister = registerService.addRegister(register);
            return ResponseEntity.ok(addedRegister);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(register);
        }
    }

    @DeleteMapping(path = "/deleteRegister/{id}", produces = "application/json")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") int id) {
        try {
            registerService.deleteRegister(id);
            return ResponseEntity.ok("delete is successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/getAllRegisterByUser/{userId}", produces = "application/json")
    public HttpEntity<List<Register>> getAllActivities(@PathVariable("userId") int userId) throws Exception {
        return new ResponseEntity<>(registerService.findAllActivitiesByUser(userId), HttpStatus.OK);
    }

    @GetMapping(path = "/getAllUserByActivity/{activityId}", produces = "application/json")
    public HttpEntity<List<Register>> getAllUsers(@PathVariable("activityId") int activityId) throws Exception {
        return new ResponseEntity<>(registerService.findAllUsersByActivity(activityId), HttpStatus.OK);
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
