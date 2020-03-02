package com.example.volunteer_management.dao;

import com.example.volunteer_management.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {
    List<Register> findAllByUserIdEquals(int userId);
}
