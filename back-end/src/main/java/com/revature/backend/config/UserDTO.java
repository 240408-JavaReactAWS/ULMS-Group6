package com.revature.backend.config;

import java.util.Objects;

/**
 * This class represents a Data Transfer Object (DTO) for a User.
 * A DTO is an object that carries data between processes, in this case, between the backend and the frontend.
 * The UserDTO includes properties for id, firstName, and lastName.
 */
public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;

    /**
     * Returns the ID of the user.
     * @return An Integer representing the ID of the user.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     * @param id An Integer containing the ID of the user.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the first name of the user.
     * @return A String representing the first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     * @param firstName A String containing the first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the user.
     * @return A String representing the last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     * @param lastName A String containing the last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Checks if this UserDTO is equal to another object.
     * @param o The object to compare this UserDTO to.
     * @return true if the objects are the same; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) &&
                Objects.equals(firstName, userDTO.firstName) &&
                Objects.equals(lastName, userDTO.lastName);
    }

    /**
     * Generates a hash code for this UserDTO.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}