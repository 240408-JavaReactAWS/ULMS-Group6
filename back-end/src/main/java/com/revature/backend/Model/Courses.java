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

    @OneToMany(mappedBy="Courses", cascade = CascadeType.ALL)
    private List<Announcements> announcements;

    @Column(name = "CourseName")
    private String courseName;

    @Column(name = "TeacherId")
    private Integer teacherId;

}
