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

@Service
public class AnnouncementsService {
    private AnnouncementsDAO announcementsDAO;
    private CoursesDAO coursesDAO;

    @Autowired
    public AnnouncementsService(AnnouncementsDAO announcementsDAO, CoursesDAO coursesDAO) {
        this.announcementsDAO = announcementsDAO;
        this.coursesDAO = coursesDAO;
    }

    public Announcements createAnnouncement(Integer courseId, Announcements announcement) throws NoSuchCourseException{
        Courses course = coursesDAO.findById(courseId)
                .orElseThrow(() -> new NoSuchCourseException("No course found with ID: " + courseId));

        announcement.setCourse(course);

        return announcementsDAO.save(announcement);
    }

    public Announcements deleteAnnouncement(Integer announcementId) throws NoSuchAnnouncementFoundException {
        Optional<Announcements> announcement = announcementsDAO.findById(announcementId);
        if(announcement.isEmpty()){
            throw new NoSuchAnnouncementFoundException("No announcement found with ID: " + announcementId);
        }
        announcementsDAO.deleteById(announcementId);
        return announcement.get();
    }

    public List<Announcements> getAllAnnouncements(Integer courseId) {
        return announcementsDAO.findByCourse_CourseId(courseId);
    }

    public Announcements getAnnouncementById(Integer announcementId) throws NoSuchAnnouncementFoundException {
        return announcementsDAO.findById(announcementId)
                .orElseThrow(() -> new NoSuchAnnouncementFoundException("No announcement found with ID: " + announcementId));

    }
}
