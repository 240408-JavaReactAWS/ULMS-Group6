package com.revature.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "Announcements")
public class Announcements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnnouncementId")
    private Integer announcementId;

    @Column(name = "Message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Courses course;

    @Column(name = "Date")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date date;

    /*
    Constructors
     */

    public Announcements() {
    }

    public Announcements(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    public Announcements(String message, Courses course, Date date) {
        this.message = message;
        this.course = course;
        this.date = date;
    }

    /*
    Getters and Setters
     */

    public Integer getAnnouncementId() { return announcementId; }

    public void setAnnouncementId(Integer announcementId) { this.announcementId = announcementId; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Courses getCourse() { return course; }

    public void setCourse(Courses course) { this.course = course; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }
}
