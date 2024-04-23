package com.revature.backend.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Grades")
public class Grades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GradeId")
    private Integer gradeId;

//    @Column(name = "EnrollmentId_FK")
//    private Integer enrollmentId;
//
//    @Column(name = "AssignmentId_FK")
//    private Integer assignmentsId;

    @ManyToOne
    @JoinColumn(name = "EnrollmentID")
    private Enrollments enrollment;

    @ManyToOne
    @JoinColumn(name = "AssignmentID")
    private Assignments assignment;

    @Column(name = "Grade")
    private Double  grade;

    public Grades() {
    }

    public Grades(Enrollments enrollment, Assignments assignment) {
        this.enrollment = enrollment;
        this.assignment = assignment;
    }

    public Grades(Enrollments enrollment, Assignments assignment, Double grade) {
        this.enrollment = enrollment;
        this.assignment = assignment;
        this.grade = grade;
    }

    public Grades(Integer gradeId, Enrollments enrollment, Assignments assignment, Double grade) {
        this.gradeId = gradeId;
        this.enrollment = enrollment;
        this.assignment = assignment;
        this.grade = grade;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Enrollments getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollments enrollment) {
        this.enrollment = enrollment;
    }

    public Assignments getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignments assignment) {
        this.assignment = assignment;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
