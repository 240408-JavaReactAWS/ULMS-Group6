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

//    @Column(name = "CourseID_FK")
//    private Integer CourseId;

    @ManyToOne
    @JoinColumn(name = "CourseId")
    private Courses course;

    @Column(name = "AssignmentName")
    private String AssignmentName;

    @Column(name = "Deadline")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date deadline;

    public Assignments() {
    }

    public Assignments(String assignmentName, Date deadline, Integer assignmentsId) {
        AssignmentName = assignmentName;
        this.deadline = deadline;
        this.assignmentsId = assignmentsId;
    }

    public Assignments(Integer assignmentsId, Courses course, String assignmentName, Date deadline) {
        this.assignmentsId = assignmentsId;
        this.course = course;
        AssignmentName = assignmentName;
        this.deadline = deadline;
    }

    public Integer getAssignmentsId() {
        return assignmentsId;
    }

    public void setAssignmentsId(Integer assignmentsId) {
        this.assignmentsId = assignmentsId;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public String getAssignmentName() {
        return AssignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        AssignmentName = assignmentName;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
