package com.revature.backend.controllers;

import com.revature.backend.models.Announcements;
import com.revature.backend.services.AnnouncementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< Updated upstream
import java.util.List;

=======
/**
 * Controller class for handling HTTP requests related to Announcements.
 */
>>>>>>> Stashed changes
@RestController
@RequestMapping("/courses/{courseId}/announcements")
@CrossOrigin(origins = {"http://localhost:3000"})
public class AnnouncementsController {
    private final AnnouncementsService announcementsService;

    /**
     * Constructs an AnnouncementsController with the specified AnnouncementsService.
     * @param announcementsService the service to manage announcements
     */
    @Autowired
    public AnnouncementsController(AnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }

<<<<<<< Updated upstream
=======
    /**
     * Handles the GET request to retrieve all announcements for a course.
     * @param courseId the ID of the course
     * @return a ResponseEntity containing a list of all announcements for the course
     */
>>>>>>> Stashed changes
    @GetMapping
    public ResponseEntity<Iterable<Announcements>> getAnnouncements(@PathVariable("courseId") Integer courseId) {
        List<Announcements> announcements = announcementsService.getAllAnnouncementsByCourseId(courseId);
        return ResponseEntity.ok(announcements);
    }

<<<<<<< Updated upstream
=======
    /**
     * Handles the GET request to retrieve an announcement by its ID.
     * @param announcementId the ID of the announcement
     * @return a ResponseEntity containing the announcement if found, or a not found status otherwise
     * @throws NoSuchAnnouncementFoundException if no announcement is found with the specified ID
     */
    @GetMapping("/{announcementId}")
    public ResponseEntity<Announcements> getAnnouncementById(@PathVariable("announcementId") Integer announcementId) throws NoSuchAnnouncementFoundException {
        try{
            Announcements announcement = announcementsService.getAnnouncementById(announcementId);
            return ResponseEntity.ok(announcement);
        }catch (NoSuchAnnouncementFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Handles the POST request to create an announcement for a course.
     * @param courseId the ID of the course
     * @param announcement the announcement to be created
     * @return a ResponseEntity containing the created announcement if successful, or a not found status otherwise
     * @throws NoSuchCourseException if no course is found with the specified ID
     */
>>>>>>> Stashed changes
    @PostMapping
    public ResponseEntity<Announcements> createAnnouncement(@PathVariable("courseId") Integer courseId, @RequestBody Announcements announcement) {
        Announcements createdAnnouncement = announcementsService.createAnnouncement(courseId, announcement);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnnouncement);
    }

    /**
     * Handles the DELETE request to delete an announcement by its ID.
     * @param announcementId the ID of the announcement to be deleted
     * @return a ResponseEntity containing the deleted announcement if successful, or a not found status otherwise
     * @throws NoSuchAnnouncementFoundException if no announcement is found with the specified ID
     */
    @DeleteMapping("/{announcementId}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable("announcementId") Integer announcementId) {
        announcementsService.deleteAnnouncement(announcementId);
        return ResponseEntity.noContent().build();
    }
}