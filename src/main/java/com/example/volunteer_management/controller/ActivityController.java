package com.example.volunteer_management.controller;

import com.example.volunteer_management.model.Activity;
import com.example.volunteer_management.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@Slf4j
@RequestMapping("activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @PostMapping(path = "/addActivity", produces = "application/json")
    public ResponseEntity<Activity> addActivity(@RequestBody Activity activity) {
        log.info("Add activity:" + activity);
        try {
//            if(activity.getName().equals("abc")) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(activity);
//            }
            Activity addedActivity = activityService.addActivity(activity);
            return ResponseEntity.ok(addedActivity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(activity);
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
