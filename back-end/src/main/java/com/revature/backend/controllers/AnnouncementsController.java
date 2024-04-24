package com.revature.backend.controllers;

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

    @PostMapping
    public ResponseEntity<Announcements> createAnnouncement(@PathVariable("courseId") Integer courseId, @RequestBody Announcements announcement) {
        Announcements createdAnnouncement = announcementsService.createAnnouncement(courseId, announcement);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnnouncement);
    }

    @DeleteMapping("/{announcementId}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable("announcementId") Integer announcementId) {
        announcementsService.deleteAnnouncement(announcementId);
        return ResponseEntity.noContent().build();
    }
}
