package com.revature.backend;

import com.revature.backend.controllers.AssignmentsController;
import com.revature.backend.models.Assignments;
import com.revature.backend.services.AssignmentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class is a test class for the AssignmentsController.
 * It uses JUnit and Mockito for testing.
 */
public class AssignmentsControllerTest {

    // The controller that we're testing
    @InjectMocks
    private AssignmentsController assignmentsController;

    // The service that the controller depends on
    @Mock
    private AssignmentsService assignmentsService;

    /**
     * This method sets up the mocks before each test.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * This test checks if the controller correctly fetches all assignments for a course.
     */
    @Test
    @DisplayName("Should return all assignments for a course")
    public void getAllAssignmentsForCourse() {
        Assignments assignment1 = new Assignments();
        Assignments assignment2 = new Assignments();
        List<Assignments> assignmentsList = Arrays.asList(assignment1, assignment2);

        when(assignmentsService.getAllAssignmentsByCourseId(1)).thenReturn(assignmentsList);

        ResponseEntity<List<Assignments>> response = assignmentsController.getAssignments(1);

        assertEquals(assignmentsList, response.getBody());
        verify(assignmentsService, times(1)).getAllAssignmentsByCourseId(1);
    }

    /**
     * This test checks if the controller correctly handles the case when there are no assignments for a course.
     */
    @Test
    @DisplayName("Should return no content when no assignments for a course")
    public void getNoAssignmentsForCourse() {
        when(assignmentsService.getAllAssignmentsByCourseId(1)).thenReturn(Arrays.asList());

        ResponseEntity<List<Assignments>> response = assignmentsController.getAssignments(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(assignmentsService, times(1)).getAllAssignmentsByCourseId(1);
    }

    /**
     * This test checks if the controller correctly creates an assignment for a course.
     */
    @Test
    @DisplayName("Should create an assignment for a course")
    public void createAssignmentForCourse() {
        Assignments assignment = new Assignments();
        when(assignmentsService.createAssignment(1, assignment)).thenReturn(assignment);

        ResponseEntity<Assignments> response = assignmentsController.createAssignment(1, assignment);

        assertEquals(assignment, response.getBody());
        verify(assignmentsService, times(1)).createAssignment(1, assignment);
    }

    /**
     * This test checks if the controller correctly deletes an assignment by its ID.
     */
    @Test
    @DisplayName("Should delete an assignment by its ID")
    public void deleteAssignmentById() {
        doNothing().when(assignmentsService).deleteAssignment(1);

        ResponseEntity<Void> response = assignmentsController.deleteAssignment(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(assignmentsService, times(1)).deleteAssignment(1);
    }
}