package com.example.volunteer_management.dao;

import com.example.volunteer_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
