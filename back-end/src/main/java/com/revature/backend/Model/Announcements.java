package com.revature.backend.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "Announcements")
public class Announcements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnnouncementId")
    private Integer announcementId;

    @ManyToOne
    @JoinColumn(name = "CourseId")
    private Courses course;

    @Column(name = "CourseID_FK")
    private Integer CourseId;

    @Column(name = "Text")
    private String text;

    @Column(name = "Date")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date date;
}
