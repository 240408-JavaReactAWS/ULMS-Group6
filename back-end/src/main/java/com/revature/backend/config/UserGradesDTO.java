package com.revature.backend.config;

import com.revature.backend.models.Grades;
import java.util.List;

public class UserGradesDTO {
    private UserDTO user;
    private List<Grades> grades;

    // getters and setters
    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }
}