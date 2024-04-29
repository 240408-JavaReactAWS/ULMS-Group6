package com.revature.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

/**
 * This class represents the Assignments entity in the database.
 * Each instance of this class corresponds to a row in the Assignments table.
 */
@Entity
@Table(name = "Assignments")
public class Assignments {

    /**
     * The unique ID of the assignment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AssignmentId")
    private Integer assignmentsId;

    /**
     * The name of the assignment.
     */
    @Column(name = "AssignmentName")
    private String assignmentName;

    /**
     * The course associated with the assignment.
     */
    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Courses course;

    /**
     * The deadline of the assignment.
     */

    //Added LocalDate
    @Column(name = "Deadline")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate deadline;

    /**
     * Default constructor.
     */
    public Assignments() {
    }

    /**
     * Constructor that initializes the name and deadline of the assignment.
     * @param assignmentName The name of the assignment.
     * @param deadline The deadline of the assignment.
     */
    public Assignments(String assignmentName, LocalDate deadline) {
        this.assignmentName = assignmentName;
        this.deadline = deadline;
    }

    /**
     * Constructor that initializes the name, course, and deadline of the assignment.
     * @param assignmentName The name of the assignment.
     * @param course The course associated with the assignment.
     * @param deadline The deadline of the assignment.
     */
    public Assignments(String assignmentName, Courses course, LocalDate deadline) {
        this.assignmentName = assignmentName;
        this.course = course;
        this.deadline = deadline;
    }

    /**
     * Returns the ID of the assignment.
     * @return The ID of the assignment.
     */
    public Integer getAssignmentsId() { return assignmentsId; }

    /**
     * Sets the ID of the assignment.
     * @param assignmentsId The ID of the assignment.
     */
    public void setAssignmentsId(Integer assignmentsId) { this.assignmentsId = assignmentsId; }

    /**
     * Returns the name of the assignment.
     * @return The name of the assignment.
     */
    public String getAssignmentName() { return assignmentName; }

    /**
     * Sets the name of the assignment.
     * @param assignmentName The name of the assignment.
     */
    public void setAssignmentName(String assignmentName) { this.assignmentName = assignmentName; }

    /**
     * Returns the course associated with the assignment.
     * @return The course associated with the assignment.
     */
    public Courses getCourse() { return course; }

    /**
     * Sets the course associated with the assignment.
     * @param course The course associated with the assignment.
     */
    public void setCourse(Courses course) { this.course = course; }

    /**
     * Returns the deadline of the assignment.
     * @return The deadline of the assignment.
     */
    public LocalDate getDeadline() { return deadline; }

    /**
     * Sets the deadline of the assignment.
     * @param deadline The deadline of the assignment.
     */
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

