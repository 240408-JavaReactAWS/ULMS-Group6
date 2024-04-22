package com.revature.backend.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Assignments")
public class Assignments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AssignmentId")
    private Integer assignmentsId;

    @Column(name = "CourseID_FK")
    private Integer CourseId;

    @Column(name = "AssignmentName")
    private String AssignmentName;

    @Column(name = "Deadline")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date deadline;

}
