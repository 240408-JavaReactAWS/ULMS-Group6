package com.revature.backend.models;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userId;

    @Column(name = "Username", unique = true)
    private String username;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Password")
    private String password;

    @Column(name = "Role")
    @Enumerated(EnumType.STRING)
    private Roles role;

    @ManyToMany(mappedBy = "students")
    private Set<Courses> enrolledCourses;

    @OneToMany(mappedBy = "teacher")
    private List<Courses> taughtCourses;

    /*
    Constructors
     */
    public Users() {
    }

    public Users(String username, String password, Roles role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Users(String username, String firstName, String lastName, String password, Roles role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public Users(Integer userId, String username, String firstName, String lastName, String password, Roles role) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    /*
    Getters and Setters
     */
    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Roles getRole() { return role; }

    public void setRole(Roles role) { this.role = role; }

    public Set<Courses> getEnrolledCourses() { return enrolledCourses; }

    public void setEnrolledCourses(Set<Courses> enrolledCourses) { this.enrolledCourses = enrolledCourses; }

    public List<Courses> getTaughtCourses() { return taughtCourses; }

    public void setTaughtCourses(List<Courses> taughtCourses) { this.taughtCourses = taughtCourses; }
}
