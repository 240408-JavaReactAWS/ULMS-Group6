package com.revature.backend.controllers;

import com.revature.backend.exceptions.NoSuchAnnouncementFoundException;
import com.revature.backend.exceptions.NoSuchCourseException;
import com.revature.backend.models.Announcements;
import com.revature.backend.services.AnnouncementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses/{courseId}/announcements")
public class AnnouncementsController {
    private final AnnouncementsService announcementsService;

    @Autowired
    public AnnouncementsController(AnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }

    // return all announcements for a course
    @GetMapping
    public ResponseEntity<Iterable<Announcements>> getAllAnnouncements(@PathVariable("courseId") Integer courseId) {
        Iterable<Announcements> announcements = announcementsService.getAllAnnouncements(courseId);
        return ResponseEntity.ok(announcements);
    }

    // return announcement by id
    @GetMapping("/{announcementId}")
    public ResponseEntity<Announcements> getAnnouncementById(@PathVariable("announcementId") Integer announcementId) throws NoSuchAnnouncementFoundException {
        try{
            Announcements announcement = announcementsService.getAnnouncementById(announcementId);
            return ResponseEntity.ok(announcement);
        }catch (NoSuchAnnouncementFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Announcements> createAnnouncement(@PathVariable("courseId") Integer courseId, @RequestBody Announcements announcement) throws NoSuchCourseException {
        try{
            Announcements createdAnnouncement = announcementsService.createAnnouncement(courseId, announcement);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAnnouncement);
        } catch (NoSuchCourseException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{announcementId}")
    public ResponseEntity<Announcements> deleteAnnouncement(@PathVariable("announcementId") Integer announcementId) throws NoSuchAnnouncementFoundException {
        try{
            Announcements deletedAnnouncement = announcementsService.deleteAnnouncement(announcementId);
            return ResponseEntity.ok(deletedAnnouncement);
        } catch (NoSuchAnnouncementFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
