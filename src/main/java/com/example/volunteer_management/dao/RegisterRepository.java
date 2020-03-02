package com.example.volunteer_management.dao;

import com.example.volunteer_management.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, Integer> {
}
