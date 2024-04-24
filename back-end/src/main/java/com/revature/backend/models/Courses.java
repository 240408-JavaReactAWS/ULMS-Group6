package com.revature.backend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseId")
    private Integer courseId;


    @Column(name = "CourseName", unique = true)
    private String courseName;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private Users teacher;

    @ManyToMany
    @JoinTable(
            name = "course_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonIgnore
    private Set<Users> students;

    /*
    Constructors
     */
    public Courses() {
    }

    public Courses(String courseName) {
        this.courseName = courseName;
    }

    public Courses(String courseName, Users teacher, Set<Users> students) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.students = students;
    }

    /*
    Getters and Setters
     */

    public Integer getCourseId() { return courseId; }

    public void setCourseId(Integer courseId) { this.courseId = courseId;}

    public String getCourseName() { return courseName; }

    public void setCourseName(String courseName) { this.courseName = courseName; }

    public Users getTeacher() { return teacher; }

    public void setTeacher(Users teacher) { this.teacher = teacher; }

    public Set<Users> getStudents() { return students; }

    public void setStudents(Set<Users> students) { this.students = students;}
}
