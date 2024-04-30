package com.revature.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

/**
 * This class represents the Announcements entity in the database.
 * Each instance of this class corresponds to a row in the Announcements table.
 */
@Entity
@Table(name = "Announcements")
public class Announcements {

    /**
     * The unique ID of the announcement.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnnouncementId")
    private Integer announcementId;

    /**
     * The message of the announcement.
     */
    @Column(name = "Message")
    private String message;

    /**
     * The course associated with the announcement.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Courses course;

    /**
     * The date of the announcement.
     */
    @Column(name = "Date")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date date;

    /**
     * Default constructor.
     */
    public Announcements() {
    }

    /**
     * Constructor that initializes the message and date of the announcement.
     * @param message The message of the announcement.
     * @param date The date of the announcement.
     */
    public Announcements(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    /**
     * Constructor that initializes the message, course, and date of the announcement.
     * @param message The message of the announcement.
     * @param course The course associated with the announcement.
     * @param date The date of the announcement.
     */
    public Announcements(String message, Courses course, Date date) {
        this.message = message;
        this.course = course;
        this.date = date;
    }

    /**
     * Returns the ID of the announcement.
     * @return The ID of the announcement.
     */
    public Integer getAnnouncementId() { return announcementId; }

    /**
     * Sets the ID of the announcement.
     * @param announcementId The ID of the announcement.
     */
    public void setAnnouncementId(Integer announcementId) { this.announcementId = announcementId; }

    /**
     * Returns the message of the announcement.
     * @return The message of the announcement.
     */
    public String getMessage() { return message; }

    /**
     * Sets the message of the announcement.
     * @param message The message of the announcement.
     */
    public void setMessage(String message) { this.message = message; }

    /**
     * Returns the course associated with the announcement.
     * @return The course associated with the announcement.
     */
    public Courses getCourse() { return course; }

    /**
     * Sets the course associated with the announcement.
     * @param course The course associated with the announcement.
     */
    public void setCourse(Courses course) { this.course = course; }

    /**
     * Returns the date of the announcement.
     * @return The date of the announcement.
     */
    public Date getDate() { return date; }

    /**
     * Sets the date of the announcement.
     * @param date The date of the announcement.
     */
    public void setDate(Date date) { this.date = date; }
}