package com.example.volunteer_management.controller;

import com.example.volunteer_management.model.Activity;
import com.example.volunteer_management.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @PostMapping(path = "/addActivity", produces = "application/json")
    public HttpStatus addActivity(@RequestBody Activity activity) {
        try {
            activityService.addActivity(activity);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping(path = "/getAllActivities", produces = "application/json")
    public HttpEntity<List<Activity>> getAllActivities() {
        return new ResponseEntity<>(activityService.findAllActivities(), HttpStatus.OK);
    }

    @PutMapping(path = "/updateActivity", produces = "application/json")
    public HttpStatus updateActivity(@RequestBody Activity activity) {
        try {
            activityService.updateActivity(activity);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PutMapping(path = "/deleteActivity/{id}", produces = "application/json")
    public HttpStatus deleteActivity(@PathVariable("id") int id) {
        try {
            activityService.deleteActivity(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
