package com.example.volunteer_management.service;

import com.example.volunteer_management.dao.UserRepository;
import com.example.volunteer_management.model.RecordState;
import com.example.volunteer_management.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User u) throws Exception {
        Optional<User> user = userRepository.findUserByUserName(u.getUserName());
        if (user.isPresent()) {
            throw new Exception("User is existing.");
        }
        u.setRegisterDate(new Date());
        return userRepository.save(u);
    }

    public List<User> findAllUsers() {
        List<User> users = userRepository.findAllByIsDeleted(RecordState.NORMAL);
        return users;
    }

    public User deleteUser(String userName) throws Exception {
        Optional<User> optionalUser = userRepository.findUserByUserName(userName);

        if (!optionalUser.isPresent()) {
            throw new Exception("Course does not exist.");
        }

        User existingUser = optionalUser.get();
        existingUser.setIsDeleted(RecordState.DELETED);

        return userRepository.save(existingUser);
    }

    public void updateUser(User user) throws Exception {
        Optional<User> optionalUser = userRepository.findUserByUserName(user.getUserName());
        if (!optionalUser.isPresent()) {
            throw new Exception("User is not exist.");
        }

        User existingUser = optionalUser.get();
        user.setId(existingUser.getId());
        user.setRegisterDate(existingUser.getRegisterDate());

        userRepository.save(user);
    }

}
