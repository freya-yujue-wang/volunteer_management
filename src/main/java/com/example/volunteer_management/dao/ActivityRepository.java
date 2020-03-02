package com.example.volunteer_management.dao;

import com.example.volunteer_management.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
}
