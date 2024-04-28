package com.revature.backend;

import com.revature.backend.controllers.AnnouncementsController;
import com.revature.backend.exceptions.NoSuchAnnouncementFoundException;
import com.revature.backend.exceptions.NoSuchCourseException;
import com.revature.backend.models.Announcements;
import com.revature.backend.services.AnnouncementsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class is a test class for the AnnouncementsController.
 * It uses JUnit and Mockito for testing.
 */
public class AnnouncementsControllerTest {

    // The controller that we're testing
    @InjectMocks
    private AnnouncementsController announcementsController;

    // The service that the controller depends on
    @Mock
    private AnnouncementsService announcementsService;

    /**
     * This method sets up the mocks before each test.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * This test checks if the controller correctly fetches all announcements for a course.
     */
    @Test
    @DisplayName("Should return all announcements for a course")
    public void getAllAnnouncements() {
        Announcements announcement1 = new Announcements();
        Announcements announcement2 = new Announcements();
        List<Announcements> announcementsList = Arrays.asList(announcement1, announcement2);

        when(announcementsService.getAllAnnouncements(1)).thenReturn(announcementsList);

        ResponseEntity<Iterable<Announcements>> response = announcementsController.getAllAnnouncements(1);

        assertEquals(announcementsList, response.getBody());
        verify(announcementsService, times(1)).getAllAnnouncements(1);
    }

    /**
     * This test checks if the controller correctly fetches an announcement by its ID.
     */
    @Test
    @DisplayName("Should return an announcement by its ID")
    public void getAnnouncementById() throws NoSuchAnnouncementFoundException {
        Announcements announcement = new Announcements();
        when(announcementsService.getAnnouncementById(1)).thenReturn(announcement);

        ResponseEntity<Announcements> response = announcementsController.getAnnouncementById(1);

        assertEquals(announcement, response.getBody());
        verify(announcementsService, times(1)).getAnnouncementById(1);
    }

    /**
     * This test checks if the controller correctly creates an announcement for a course.
     */
    @Test
    @DisplayName("Should create an announcement for a course")
    public void createAnnouncement() throws NoSuchCourseException {
        Announcements announcement = new Announcements();
        when(announcementsService.createAnnouncement(1, announcement)).thenReturn(announcement);

        ResponseEntity<Announcements> response = announcementsController.createAnnouncement(1, announcement);

        assertEquals(announcement, response.getBody());
        verify(announcementsService, times(1)).createAnnouncement(1, announcement);
    }

    /**
     * This test checks if the controller correctly deletes an announcement by its ID.
     */
    @Test
    @DisplayName("Should delete an announcement by its ID")
    public void deleteAnnouncement() throws NoSuchAnnouncementFoundException {
        Announcements announcement = new Announcements();
        when(announcementsService.deleteAnnouncement(1)).thenReturn(announcement);

        ResponseEntity<Announcements> response = announcementsController.deleteAnnouncement(1);

        assertEquals(announcement, response.getBody());
        verify(announcementsService, times(1)).deleteAnnouncement(1);
    }
}