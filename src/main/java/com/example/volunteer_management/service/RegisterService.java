package com.example.volunteer_management.service;

import com.example.volunteer_management.dao.ActivityRepository;
import com.example.volunteer_management.dao.RegisterRepository;
import com.example.volunteer_management.dao.UserRepository;
import com.example.volunteer_management.model.Activity;
import com.example.volunteer_management.model.Register;
import com.example.volunteer_management.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private UserRepository userRepository;

    public Register addRegister(Register register) {
        Optional<Register> optionalRegister = registerRepository.findByActivityIdEqualsAndUserIdEquals(register.getActivityId(), register.getUserId());
        if (optionalRegister.isPresent()) {
            throw new RuntimeException("Register already exists.");
        }
        return registerRepository.save(register);
    }

    public void deleteRegister(int id) {
        registerRepository.deleteById(id);
    }

    public List<Register> findAllActivitiesByUser(int userId) throws Exception {
        List<Register> registerList = registerRepository.findAllByUserIdEquals(userId);
        for (Register register : registerList) {
            Optional<Activity> activityOptional = activityRepository.findById(register.getActivityId());
            if (!activityOptional.isPresent()) {
                throw new Exception(String.format("Activity %d not found!", register.getActivityId()));
            }
            Activity activity = activityOptional.get();
            register.setActivityName(activity.getName());
            register.setStartTime(activity.getStartTime());
            register.setEndTime(activity.getEndTime());
        }
        return registerList;
    }

    public List<Register> findAllUsersByActivity(int activityId) throws Exception {
        List<Register> registerList = registerRepository.findAllByActivityIdEquals(activityId);
        for (Register register : registerList) {
            Optional<User> userOptional = userRepository.findById(register.getUserId());
            if (!userOptional.isPresent()) {
                throw new Exception(String.format("User %d not found!", register.getUserId()));
            }
            User user = userOptional.get();
            register.setName(user.getName());
            register.setGender(user.getGender());
        }
        return registerList;
    }

    public Register updateState(Register register) throws Exception {
        Optional<Register> optionalRegister = registerRepository.findById(register.getId());
        if (!optionalRegister.isPresent()) {
            throw new Exception("Register does not exist.");
        }

        Register existingRegister = optionalRegister.get();
        existingRegister.setState(register.getState());

        return registerRepository.save(existingRegister);
    }

    public Register updateComment(Register register) throws Exception {
        Optional<Register> optionalRegister = registerRepository.findById(register.getId());
        if (!optionalRegister.isPresent()) {
            throw new Exception("Register does not exist.");
        }

        Register existingRegister = optionalRegister.get();
        existingRegister.setComment(register.getComment());

        return registerRepository.save(existingRegister);
    }

    public Register updateFeedback(Register register) throws Exception {
        Optional<Register> optionalRegister = registerRepository.findById(register.getId());
        if (!optionalRegister.isPresent()) {
            throw new Exception("Register does not exist.");
        }

        Register existingRegister = optionalRegister.get();
        existingRegister.setFeedback(register.getFeedback());

        return registerRepository.save(existingRegister);
    }

}
