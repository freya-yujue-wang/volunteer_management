package com.example.volunteer_management.dao;

import com.example.volunteer_management.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {
    List<Register> findAllByUserIdEquals(int userId);
    List<Register> findAllByActivityIdEquals(int activityId);
    Optional<Register> findByActivityIdEqualsAndUserIdEquals(int activityId, int userId);
}
