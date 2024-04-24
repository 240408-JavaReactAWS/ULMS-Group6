package com.revature.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Grades")
public class Grades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GradeId")
    private Integer gradeId;

    @Column(name = "Grade")
    private Double grade;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "assignment_id")
    private Assignments assignment;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;

    /*
     no-arg constructor
     */
    public Grades() {
    }

    /*
    Constructor with assignment, user and grade as arguments
     */
    public Grades(Double grade, Assignments assignment, Users user) {
        this.grade = grade;
        this.assignment = assignment;
        this.user = user;
    }

    /*
     Getters and setters
     */

    public Integer getGradeId() { return gradeId; }

    public void setGradeId(Integer gradeId) { this.gradeId = gradeId; }

    public Double getGrade() { return grade; }

    public void setGrade(Double grade) { this.grade = grade; }

    public Assignments getAssignment() { return assignment; }

    public void setAssignment(Assignments assignment) { this.assignment = assignment; }

    public Users getUser() { return user; }

    public void setUser(Users user) { this.user = user; }
}
