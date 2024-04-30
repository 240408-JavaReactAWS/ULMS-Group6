package com.revature.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

/**
 * This class represents the Courses entity in the database.
 * Each instance of this class corresponds to a row in the Courses table.
 */
@Entity
@Table(name = "Courses")
public class Courses {

    /**
     * The unique ID of the course.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseId")
    private Integer courseId;

    /**
     * The name of the course.
     */
    @Column(name = "CourseName", unique = true)
    private String courseName;

    /**
     * The teacher associated with the course.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private Users teacher;

    /**
     * The students enrolled in the course.
     */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CourseStudent> students;

    /**
     * The maximum number of students that can be enrolled in the course.
     */
    @Column(name = "CourseCapacity")
    private Integer courseCapacity;

    /**
     * Default constructor.
     */
    public Courses() {
    }

    /**
     * Constructor that initializes the name and capacity of the course.
     * @param courseName The name of the course.
     * @param courseCapacity The maximum number of students that can be enrolled in the course.
     */
    public Courses(String courseName, Integer courseCapacity) {
        this.courseName = courseName;
        this.courseCapacity = courseCapacity;
    }

    /**
     * Constructor that initializes the name, capacity, teacher, and students of the course.
     * @param courseName The name of the course.
     * @param courseCapacity The maximum number of students that can be enrolled in the course.
     * @param teacher The teacher associated with the course.
     * @param students The students enrolled in the course.
     */
    public Courses(String courseName, Integer courseCapacity, Users teacher, Set<CourseStudent> students) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.students = students;
        this.courseCapacity = courseCapacity;
    }

    /**
     * Returns the ID of the course.
     * @return The ID of the course.
     */
    public Integer getCourseId() { return courseId; }

    /**
     * Sets the ID of the course.
     * @param courseId The ID of the course.
     */
    public void setCourseId(Integer courseId) { this.courseId = courseId;}

    /**
     * Returns the name of the course.
     * @return The name of the course.
     */
    public String getCourseName() { return courseName; }

    /**
     * Sets the name of the course.
     * @param courseName The name of the course.
     */
    public void setCourseName(String courseName) { this.courseName = courseName; }

    /**
     * Returns the teacher associated with the course.
     * @return The teacher associated with the course.
     */
    public Users getTeacher() { return teacher; }

    /**
     * Sets the teacher associated with the course.
     * @param teacher The teacher associated with the course.
     */
    public void setTeacher(Users teacher) { this.teacher = teacher; }

    /**
     * Returns the students enrolled in the course.
     * @return The students enrolled in the course.
     */
    public Set<CourseStudent> getStudents() { return students; }

    /**
     * Sets the students enrolled in the course.
     * @param students The students enrolled in the course.
     */
    public void setStudents(Set<CourseStudent> students) { this.students = students;}

    /**
     * Returns the maximum number of students that can be enrolled in the course.
     * @return The maximum number of students that can be enrolled in the course.
     */
    public Integer getCourseCapacity() { return courseCapacity; }

    /**
     * Sets the maximum number of students that can be enrolled in the course.
     * @param courseCapacity The maximum number of students that can be enrolled in the course.
     */
    public void setCourseCapacity(Integer courseCapacity) { this.courseCapacity = courseCapacity; }
}