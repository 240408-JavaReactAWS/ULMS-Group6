package com.revature.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 * This class represents the Grades entity in the database.
 * Each instance of this class corresponds to a row in the Grades table.
 */
@Entity
@Table(name = "Grades")
public class Grades {

    /**
     * The unique ID of the grade.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GradeId")
    private Integer gradeId;

    /**
     * The value of the grade.
     */
    @Column(name = "Grade")
    private Double grade;

    /**
     * The assignment associated with the grade.
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "assignment_id")
    private Assignments assignment;

    /**
     * The user associated with the grade.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;

    /**
     * Default constructor.
     */
    public Grades() {
    }

    /**
     * Constructor that initializes the grade, assignment, and user.
     * @param grade The value of the grade.
     * @param assignment The assignment associated with the grade.
     * @param user The user associated with the grade.
     */
    public Grades(Double grade, Assignments assignment, Users user) {
        this.grade = grade;
        this.assignment = assignment;
        this.user = user;
    }

    /**
     * Returns the ID of the grade.
     * @return The ID of the grade.
     */
    public Integer getGradeId() { return gradeId; }

    /**
     * Sets the ID of the grade.
     * @param gradeId The ID of the grade.
     */
    public void setGradeId(Integer gradeId) { this.gradeId = gradeId; }

    /**
     * Returns the value of the grade.
     * @return The value of the grade.
     */
    public Double getGrade() { return grade; }

    /**
     * Sets the value of the grade.
     * @param grade The value of the grade.
     */
    public void setGrade(Double grade) { this.grade = grade; }

    /**
     * Returns the assignment associated with the grade.
     * @return The assignment associated with the grade.
     */
    public Assignments getAssignment() { return assignment; }

    /**
     * Sets the assignment associated with the grade.
     * @param assignment The assignment associated with the grade.
     */
    public void setAssignment(Assignments assignment) { this.assignment = assignment; }

    /**
     * Returns the user associated with the grade.
     * @return The user associated with the grade.
     */
    public Users getUser() { return user; }

    /**
     * Sets the user associated with the grade.
     * @param user The user associated with the grade.
     */
    public void setUser(Users user) { this.user = user; }
}