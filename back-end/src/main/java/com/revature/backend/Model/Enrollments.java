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

//    @Column(name = "UserID_FK")
//    private Integer userId;
//
//    @Column(name = "CourseID_FK")
//    private Integer CourseId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "CourseId")
    private Courses course;

    public Enrollments() {

    }

    public Enrollments(Users user, Courses course) {
        this.user = user;
        this.course = course;
    }

    public Enrollments(Integer enrollmentId, Users user, Courses course) {
        this.enrollmentId = enrollmentId;
        this.user = user;
        this.course = course;
    }

    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }
}
