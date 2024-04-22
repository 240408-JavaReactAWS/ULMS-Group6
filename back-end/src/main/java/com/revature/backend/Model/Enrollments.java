package com.revature.backend.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Enrollments")
public class Enrollments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EnrollmentId")
    private Integer enrollmentId;

    @Column(name = "UserID_FK")
    private Integer userId;

    @Column(name = "CourseID_FK")
    private Integer CourseId;


}
