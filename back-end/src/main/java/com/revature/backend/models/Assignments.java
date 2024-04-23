package com.revature.backend.models;

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

    @Column(name = "AssignmentName")
    private String assignmentName;


    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses course;

    @Column(name = "Deadline")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date deadline;

    /*
    Constructors
     */

    public Assignments() {
    }

    public Assignments(String assignmentName, Date deadline) {
        this.assignmentName = assignmentName;
        this.deadline = deadline;
    }

    public Assignments(String assignmentName, Courses course, Date deadline) {
        this.assignmentName = assignmentName;
        this.course = course;
        this.deadline = deadline;
    }

    /*
    Getters and Setters
     */

    public Integer getAssignmentsId() { return assignmentsId; }

    public void setAssignmentsId(Integer assignmentsId) { this.assignmentsId = assignmentsId; }

    public String getAssignmentName() { return assignmentName; }

    public void setAssignmentName(String assignmentName) { this.assignmentName = assignmentName; }

    public Courses getCourse() { return course; }

    public void setCourse(Courses course) { this.course = course; }

    public Date getDeadline() { return deadline; }

    public void setDeadline(Date deadline) { this.deadline = deadline; }
}
