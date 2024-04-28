package com.revature.backend;

import com.revature.backend.config.UserGradesDTO;
import com.revature.backend.controllers.GradesController;
import com.revature.backend.exceptions.NoSuchCourseException;
import com.revature.backend.exceptions.NoSuchUserFoundException;
import com.revature.backend.models.Grades;
import com.revature.backend.services.GradesService;
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
 * This class is a test class for the GradesController.
 * It uses JUnit and Mockito for testing.
 */
public class GradesControllerTest {

    // The controller that we're testing
    @InjectMocks
    private GradesController gradesController;

    // The service that the controller depends on
    @Mock
    private GradesService gradesService;

    /**
     * This method sets up the mocks before each test.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * This test checks if the controller correctly fetches all grades for a student.
     */
    @Test
    @DisplayName("Should return all grades for a student")
    public void getStudentGrades() {
        Grades grade1 = new Grades();
        Grades grade2 = new Grades();
        List<Grades> gradesList = Arrays.asList(grade1, grade2);

        when(gradesService.getStudentAllGrades(1)).thenReturn(gradesList);

        ResponseEntity<List<Grades>> response = gradesController.getStudentGrades(1);

        assertEquals(gradesList, response.getBody());
        verify(gradesService, times(1)).getStudentAllGrades(1);
    }

    /**
     * This test checks if the controller correctly fetches all grades for an assignment.
     */
    @Test
    @DisplayName("Should return all grades for an assignment")
    public void getAssignmentGrades() {
        Grades grade1 = new Grades();
        Grades grade2 = new Grades();
        List<Grades> gradesList = Arrays.asList(grade1, grade2);

        when(gradesService.getAssignmentAllGrades(1)).thenReturn(gradesList);

        ResponseEntity<List<Grades>> response = gradesController.getAssignmentGrades(1);

        assertEquals(gradesList, response.getBody());
        verify(gradesService, times(1)).getAssignmentAllGrades(1);
    }

    /**
     * This test checks if the controller correctly fetches all grades for a course.
     */
    @Test
    @DisplayName("Should return all grades for a course")
    public void getAllGradesForCourse() throws NoSuchCourseException {
        UserGradesDTO grade1 = new UserGradesDTO();
        UserGradesDTO grade2 = new UserGradesDTO();
        List<UserGradesDTO> gradesList = Arrays.asList(grade1, grade2);

        when(gradesService.getAllGradesForCourse(1)).thenReturn(gradesList);

        ResponseEntity<List<UserGradesDTO>> response = gradesController.getAllGradesForCourse(1);

        assertEquals(gradesList, response.getBody());
        verify(gradesService, times(1)).getAllGradesForCourse(1);
    }

    /**
     * This test checks if the controller correctly fetches a grade for a specific student and assignment.
     */
    @Test
    @DisplayName("Should return a grade for a specific student and assignment")
    public void getAssignmentGradesForStudent() throws NoSuchUserFoundException {
        Grades grade = new Grades();
        when(gradesService.getAssignmentGrades(1, 1)).thenReturn(grade);

        ResponseEntity<Grades> response = gradesController.getAssignmentGrades(1, 1);

        assertEquals(grade, response.getBody());
        verify(gradesService, times(1)).getAssignmentGrades(1, 1);
    }

    /**
     * This test checks if the controller correctly assigns a grade to a specific student and assignment.
     */
    @Test
    @DisplayName("Should assign a grade to a specific student and assignment")
    public void assignGrade() {
        Grades grade = new Grades();
        when(gradesService.assignGrade(1, 1, 85.0)).thenReturn(grade);

        ResponseEntity<Grades> response = gradesController.assignGrade(1, 1, 85.0);

        assertEquals(grade, response.getBody());
        verify(gradesService, times(1)).assignGrade(1, 1, 85.0);
    }

    /**
     * This test checks if the controller correctly assigns grades in bulk.
     */
    @Test
    @DisplayName("Should assign grades in bulk")
    public void assignBulkGrades() {
        Grades grade1 = new Grades();
        Grades grade2 = new Grades();
        List<Grades> gradesList = Arrays.asList(grade1, grade2);

        when(gradesService.assignBulkGrades(anyList())).thenReturn(gradesList);

        ResponseEntity<List<Grades>> response = gradesController.assignBulkGrades(Arrays.asList());

        assertEquals(gradesList, response.getBody());
        verify(gradesService, times(1)).assignBulkGrades(anyList());
    }
}