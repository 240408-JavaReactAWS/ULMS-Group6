package com.revature.backend.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Grades")
public class Grades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GradeId")
    private Integer gradeId;

    @Column(name = "EnrollmentId_FK")
    private Integer enrollmentId;

    @Column(name = "AssignmentId_FK")
    private Integer assignmentsId;

    @Column(name = "Grade")
    private Integer grade;
}
