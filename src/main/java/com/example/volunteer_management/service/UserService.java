package com.example.volunteer_management.service;

import com.example.volunteer_management.dao.UserRepository;
import com.example.volunteer_management.model.RecordState;
import com.example.volunteer_management.model.Response;
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

    public User updateUser(User user) throws Exception {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (!optionalUser.isPresent()) {
            throw new Exception("User is not exist.");
        }

        User existingUser = optionalUser.get();
        user.setRegisterDate(existingUser.getRegisterDate());

        return userRepository.save(user);
    }

    public User updatePassword(User user) throws Exception {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (!optionalUser.isPresent()) {
            throw new Exception("User is not exist.");
        }

        User existingUser = optionalUser.get();
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    public User resetPassword(int id) throws Exception {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new Exception("User is not exist.");
        }

        User existingUser = optionalUser.get();
        existingUser.setId(existingUser.getId());
        existingUser.setRegisterDate(existingUser.getRegisterDate());
        existingUser.setPassword("123456");

        return userRepository.save(existingUser);
    }

    public User findUserById(int id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Cannot find user with id: " + id);
        }
        return optional.get();
    }

    public Response login(String userName, String password) {
        Optional<User> user = userRepository.findUserByUserName(userName);
        if(!user.isPresent()) {
            return Response.builder().isSuccessful(0).message("用户名不存在！").build();
        }

        if(!password.equals(user.get().getPassword())) {
            return Response.builder().isSuccessful(0).message("密码不正确！").build();
        }
        return Response.builder().isSuccessful(1).message("登陆成功！").obj(user.get()).build();
    }
}
