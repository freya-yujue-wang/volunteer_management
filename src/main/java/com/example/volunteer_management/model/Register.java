package com.example.volunteer_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "register")
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int activityId;
    private RegisterState state = RegisterState.PENDING;
    private String comment;
    private String feedback;

    @Transient
    private String activityName;
    @Transient
    private Date startTime;
    @Transient
    private Date endTime;
}
