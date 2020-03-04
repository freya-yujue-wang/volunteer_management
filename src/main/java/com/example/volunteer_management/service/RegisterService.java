package com.example.volunteer_management.service;

import com.example.volunteer_management.dao.ActivityRepository;
import com.example.volunteer_management.dao.RegisterRepository;
import com.example.volunteer_management.model.Activity;
import com.example.volunteer_management.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    private ActivityRepository activityRepository;

    public void addRegister(Register register) {
        registerRepository.save(register);
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

    public void updateState(Register register) throws Exception {
        Optional<Register> optionalRegister = registerRepository.findById(register.getId());
        if (!optionalRegister.isPresent()) {
            throw new Exception("Register does not exist.");
        }

        Register existingRegister = optionalRegister.get();
        existingRegister.setState(register.getState());

        registerRepository.save(existingRegister);
    }

    public void updateComment(Register register) throws Exception {
        Optional<Register> optionalRegister = registerRepository.findById(register.getId());
        if (!optionalRegister.isPresent()) {
            throw new Exception("Register does not exist.");
        }

        Register existingRegister = optionalRegister.get();
        existingRegister.setComment(register.getComment());

        registerRepository.save(existingRegister);
    }

    public void updateFeedback(Register register) throws Exception {
        Optional<Register> optionalRegister = registerRepository.findById(register.getId());
        if (!optionalRegister.isPresent()) {
            throw new Exception("Register does not exist.");
        }

        Register existingRegister = optionalRegister.get();
        existingRegister.setFeedback(register.getFeedback());

        registerRepository.save(existingRegister);
    }

}
