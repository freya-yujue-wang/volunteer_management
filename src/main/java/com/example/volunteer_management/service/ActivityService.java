package com.example.volunteer_management.service;

import com.example.volunteer_management.dao.ActivityRepository;
import com.example.volunteer_management.model.Activity;
import com.example.volunteer_management.model.RecordState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public void addActivity(Activity activity) throws Exception {
        activity.setPublishTime(new Date());
        try {
            activityRepository.save(activity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Activity> findAllActivities() {
        List<Activity> activities = activityRepository.findAllByIsDeleted(RecordState.NORMAL);
        return activities;
    }

    public void deleteActivity(int id) throws Exception {
        Optional<Activity> optionalActivity = activityRepository.findById(id);

        if (!optionalActivity.isPresent()) {
            throw new Exception("Activity does not exist.");
        }

        Activity existingActivity = optionalActivity.get();
        existingActivity.setIsDeleted(RecordState.DELETED);

        try {
            activityRepository.save(existingActivity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void updateActivity(Activity activity) throws Exception {
        Optional<Activity> optionalActivity = activityRepository.findById(activity.getId());
        if (!optionalActivity.isPresent()) {
            throw new Exception("Activity is not exist.");
        }

        Activity existingActivity = optionalActivity.get();
        activity.setId(existingActivity.getId());
        activity.setPublishTime(existingActivity.getPublishTime());
        try {
            activityRepository.save(activity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
