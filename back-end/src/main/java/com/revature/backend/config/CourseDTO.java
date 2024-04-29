package com.revature.backend.config;

/**
 * This class represents a Data Transfer Object (DTO) for a Course.
 * A DTO is an object that carries data between processes, in this case, between the backend and the frontend.
 * The CourseDTO includes properties for id, courseName, courseCapacity, and teacher.
 */
public class CourseDTO {
    private Integer id;
    private String courseName;
    private Integer courseCapacity;
    private UserDTO teacher;

    /**
     * Returns the ID of the course.
     * @return An Integer representing the ID of the course.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the course.
     * @param id An Integer containing the ID of the course.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the name of the course.
     * @return A String representing the name of the course.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the name of the course.
     * @param courseName A String containing the name of the course.
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Returns the capacity of the course.
     * @return An Integer representing the capacity of the course.
     */
    public Integer getCourseCapacity() {
        return courseCapacity;
    }

    /**
     * Sets the capacity of the course.
     * @param courseCapacity An Integer containing the capacity of the course.
     */
    public void setCourseCapacity(Integer courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    /**
     * Returns the teacher of the course.
     * @return A UserDTO object representing the teacher of the course.
     */
    public UserDTO getTeacher() {
        return teacher;
    }

    /**
     * Sets the teacher of the course.
     * @param teacher A UserDTO object containing the teacher of the course.
     */
    public void setTeacher(UserDTO teacher) {
        this.teacher = teacher;
    }
}