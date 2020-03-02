package com.example.volunteer_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;
    private String password;
    private String name;
    private String gender;
    private String phoneNumber;
    private String address;
    private Date registerDate;
    private RecordState isDeleted = RecordState.NORMAL;
    private int isManager;

}
