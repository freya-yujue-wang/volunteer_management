package com.example.volunteer_management.dao;

import com.example.volunteer_management.model.RecordState;
import com.example.volunteer_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUserName(String userName);

    List<User> findAllByIsDeleted(RecordState isDeleted);
}
