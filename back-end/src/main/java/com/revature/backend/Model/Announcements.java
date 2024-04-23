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

//    @Column(name = "CourseID_FK")
//    private Integer CourseId;

    @Column(name = "Text")
    private String text;

    @Column(name = "Date")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date date;

    public Announcements() {
    }

    public Announcements(Courses course, String text, Date date) {
        this.course = course;
        this.text = text;
        this.date = date;
    }

    public Announcements(Integer announcementId, String text, Date date, Courses course) {
        this.announcementId = announcementId;
        this.text = text;
        this.date = date;
        this.course = course;
    }

    public Integer getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Integer announcementId) {
        this.announcementId = announcementId;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
