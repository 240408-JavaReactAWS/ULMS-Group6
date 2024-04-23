package com.revature.backend.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseId")
    private Integer courseId;

    @OneToMany(mappedBy="course", cascade = CascadeType.ALL)
    private List<Announcements> announcements;

    @Column(name = "CourseName")
    private String courseName;

    @ManyToOne
    @JoinColumn(name = "TeacherId")
    private Users teacher;

    public Courses() {
    }

    public Courses(String courseName, Users teacher) {
        this.courseName = courseName;
        this.teacher = teacher;
    }

    public Courses(List<Announcements> announcements, String courseName, Users teacher) {
        this.announcements = announcements;
        this.courseName = courseName;
        this.teacher = teacher;
    }

    public Courses(Integer courseId, List<Announcements> announcements, String courseName, Users teacher) {
        this.courseId = courseId;
        this.announcements = announcements;
        this.courseName = courseName;
        this.teacher = teacher;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public List<Announcements> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcements> announcements) {
        this.announcements = announcements;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Users getTeacher() {
        return teacher;
    }

    public void setTeacher(Users teacher) {
        this.teacher = teacher;
    }
}
