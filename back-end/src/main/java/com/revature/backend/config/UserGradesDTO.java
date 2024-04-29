package com.revature.backend.config;

import com.revature.backend.models.Grades;
import java.util.List;

/**
 * This class represents a Data Transfer Object (DTO) for a User's Grades.
 * A DTO is an object that carries data between processes, in this case, between the backend and the frontend.
 * The UserGradesDTO includes properties for user and a list of grades.
 */
public class UserGradesDTO {
    private UserDTO user;
    private List<Grades> grades;

    /**
     * Returns the UserDTO object of the user.
     * @return A UserDTO object representing the user.
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * Sets the UserDTO object of the user.
     * @param user A UserDTO object containing the user's details.
     */
    public void setUser(UserDTO user) {
        this.user = user;
    }

    /**
     * Returns the list of grades of the user.
     * @return A List of Grades objects representing the grades of the user.
     */
    public List<Grades> getGrades() {
        return grades;
    }

    /**
     * Sets the list of grades of the user.
     * @param grades A List of Grades objects containing the grades of the user.
     */
    public void setGrades(List<Grades> grades) {
        this.grades = grades;
    }
}