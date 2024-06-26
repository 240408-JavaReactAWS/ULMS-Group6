package com.revature.backend.services;

import com.revature.backend.exceptions.NoSuchAnnouncementFoundException;
import com.revature.backend.exceptions.NoSuchCourseException;
import com.revature.backend.models.Announcements;
import com.revature.backend.models.Courses;
import com.revature.backend.repos.AnnouncementsDAO;
import com.revature.backend.repos.CoursesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This service class handles the business logic related to announcements.
 * It uses the AnnouncementsDAO and CoursesDAO to interact with the database.
 */
@Service
public class AnnouncementsService {
    private AnnouncementsDAO announcementsDAO;
    private CoursesDAO coursesDAO;

    /**
     * Constructs an AnnouncementsService with the specified AnnouncementsDAO and CoursesDAO.
     * @param announcementsDAO the DAO to manage announcements
     * @param coursesDAO the DAO to manage courses
     */
    @Autowired
    public AnnouncementsService(AnnouncementsDAO announcementsDAO, CoursesDAO coursesDAO) {
        this.announcementsDAO = announcementsDAO;
        this.coursesDAO = coursesDAO;
    }

    /**
     * Creates an announcement for a specific course.
     * @param courseId the ID of the course
     * @param announcement the announcement to be created
     * @return the created announcement
     * @throws NoSuchCourseException if no course is found with the specified ID
     */
    public Announcements createAnnouncement(Integer courseId, Announcements announcement) throws NoSuchCourseException{
        Courses course = coursesDAO.findById(courseId)
                .orElseThrow(() -> new NoSuchCourseException("No course found with ID: " + courseId));
        announcement.setCourse(course);
        return announcementsDAO.save(announcement);
    }


    /**
     * Deletes an announcement by its ID.
     * @param announcementId the ID of the announcement to be deleted
     * @return the deleted announcement
     * @throws NoSuchAnnouncementFoundException if no announcement is found with the specified ID
     */

    public Announcements deleteAnnouncement(Integer announcementId) throws NoSuchAnnouncementFoundException {
        Optional<Announcements> announcement = announcementsDAO.findById(announcementId);
        if(announcement.isEmpty()){
            throw new NoSuchAnnouncementFoundException("No announcement found with ID: " + announcementId);
        }
        announcementsDAO.deleteById(announcementId);
        return announcement.get();
    }


    /**
     * Retrieves all announcements for a specific course.
     * @param courseId the ID of the course
     * @return a list of announcements for the specified course
     */
    public List<Announcements> getAllAnnouncements(Integer courseId) {
        return announcementsDAO.findByCourse_CourseId(courseId);
    }

    /**
     * Retrieves an announcement by its ID.
     * @param announcementId the ID of the announcement
     * @return the announcement with the specified ID
     * @throws NoSuchAnnouncementFoundException if no announcement is found with the specified ID
     */
    public Announcements getAnnouncementById(Integer announcementId) throws NoSuchAnnouncementFoundException {
        return announcementsDAO.findById(announcementId)
                .orElseThrow(() -> new NoSuchAnnouncementFoundException("No announcement found with ID: " + announcementId));
    }
}

