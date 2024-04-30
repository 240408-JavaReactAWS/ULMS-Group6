package com.revature.backend.config;

public class CourseStudentDTO {
    private Integer id;
    private UserDTO student;

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDTO getStudent() {
        return student;
    }

    public void setStudent(UserDTO student) {
        this.student = student;
    }

}