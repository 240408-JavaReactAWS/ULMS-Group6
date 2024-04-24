package com.revature.backend.services;


import com.revature.backend.models.Announcements;
import com.revature.backend.models.Courses;
import com.revature.backend.models.Users;
import com.revature.backend.repos.AnnouncementsDAO;
import com.revature.backend.repos.CoursesDAO;
import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementsService {
    private AnnouncementsDAO announcementsDAO;
    private CoursesDAO coursesDAO;

    @Autowired
    public AnnouncementsService(AnnouncementsDAO announcementsDAO, CoursesDAO coursesDAO) {
        this.announcementsDAO = announcementsDAO;
        this.coursesDAO = coursesDAO;
    }

    public Announcements createAnnouncement(Integer courseId, Announcements announcement) {
        Courses course = coursesDAO.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        announcement.setCourse(course);

        return announcementsDAO.save(announcement);
    }

    public void deleteAnnouncement(Integer announcementId) {
        announcementsDAO.deleteById(announcementId);
    }
}
