package com.example.volunteer_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String content;
    private Date startTime;
    private Date endTime;
    private Date publishTime;
    private RecordState isDeleted = RecordState.NORMAL;
    private int isCompleted;
    private EnrollState enrollState = EnrollState.STARTED;
}
