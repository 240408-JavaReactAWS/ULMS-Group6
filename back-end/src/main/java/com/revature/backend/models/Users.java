package com.revature.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

/**
 * This class represents the Users entity in the database.
 * Each instance of this class corresponds to a row in the Users table.
 */
@Entity
@Table(name = "Users")
public class Users {

    /**
     * The unique ID of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userId;

    /**
     * The username of the user.
     */
    @Column(name = "Username", unique = true)
    private String username;

    /**
     * The first name of the user.
     */
    @Column(name = "FirstName")
    private String firstName;

    /**
     * The last name of the user.
     */
    @Column(name = "LastName")
    private String lastName;

    /**
     * The password of the user.
     */
    @Column(name = "Password")
    private String password;

    /**
     * The role of the user.
     */
    @Column(name = "Role")
    @Enumerated(EnumType.STRING)
    private Roles role;

    /**
     * The courses that the user is enrolled in.
     */
    @ManyToMany(mappedBy = "students")
    @JsonIgnore
    private Set<Courses> enrolledCourses;

    /**
     * The courses that the user is teaching.
     */
    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Courses> taughtCourses;

    /**
     * Default constructor.
     */
    public Users() {
    }

    /**
     * Constructor that initializes the username and password of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor that initializes the username, password, and role of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param role The role of the user.
     */
    public Users(String username, String password, Roles role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Constructor that initializes the username, first name, last name, password, and role of the user.
     * @param username The username of the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param password The password of the user.
     * @param role The role of the user.
     */
    public Users(String username, String firstName, String lastName, String password, Roles role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    /**
     * Constructor that initializes the user ID, username, first name, last name, password, and role of the user.
     * @param userId The unique ID of the user.
     * @param username The username of the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param password The password of the user.
     * @param role The role of the user.
     */
    public Users(Integer userId, String username, String firstName, String lastName, String password, Roles role) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    /**
     * Returns the ID of the user.
     * @return The ID of the user.
     */
    public Integer getUserId() { return userId; }

    /**
     * Sets the ID of the user.
     * @param userId The ID of the user.
     */
    public void setUserId(Integer userId) { this.userId = userId; }

    /**
     * Returns the username of the user.
     * @return The username of the user.
     */
    public String getUsername() { return username; }

    /**
     * Sets the username of the user.
     * @param username The username of the user.
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Returns the first name of the user.
     * @return The first name of the user.
     */
    public String getFirstName() { return firstName; }

    /**
     * Sets the first name of the user.
     * @param firstName The first name of the user.
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * Returns the last name of the user.
     * @return The last name of the user.
     */
    public String getLastName() { return lastName; }

    /**
     * Sets the last name of the user.
     * @param lastName The last name of the user.
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Returns the password of the user.
     * @return The password of the user.
     */
    public String getPassword() { return password; }

    /**
     * Sets the password of the user.
     * @param password The password of the user.
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Returns the role of the user.
     * @return The role of the user.
     */
    public Roles getRole() { return role; }

    /**
     * Sets the role of the user.
     * @param role The role of the user.
     */
    public void setRole(Roles role) { this.role = role; }

    /**
     * Returns the courses that the user is enrolled in.
     * @return The courses that the user is enrolled in.
     */
    public Set<Courses> getEnrolledCourses() { return enrolledCourses; }

    /**
     * Sets the courses that the user is enrolled in.
     * @param enrolledCourses The courses that the user is enrolled in.
     */
    public void setEnrolledCourses(Set<Courses> enrolledCourses) { this.enrolledCourses = enrolledCourses; }

    /**
     * Returns the courses that the user is teaching.
     * @return The courses that the user is teaching.
     */
    public List<Courses> getTaughtCourses() { return taughtCourses; }

    /**
     * Sets the courses that the user is teaching.
     * @param taughtCourses The courses that the user is teaching.
     */
    public void setTaughtCourses(List<Courses> taughtCourses) { this.taughtCourses = taughtCourses; }

    /**
     * Returns a string representation of the user.
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", enrolledCourses=" + enrolledCourses +
                ", taughtCourses=" + taughtCourses +
                '}';
    }
}