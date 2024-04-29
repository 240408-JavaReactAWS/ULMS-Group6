package com.revature.backend;

import com.revature.backend.controllers.CoursesController;
import com.revature.backend.exceptions.*;
import com.revature.backend.models.Courses;
import com.revature.backend.models.Users;
import com.revature.backend.services.CoursesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class is a test class for the CoursesController.
 * It uses JUnit and Mockito for testing.
 */
public class CoursesControllerTest {

    // The controller that we're testing
    @InjectMocks
    private CoursesController coursesController;

    // The service that the controller depends on
    @Mock
    private CoursesService coursesService;

    /**
     * This method sets up the mocks before each test.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * This test checks if the controller correctly creates a course.
     */
    @Test
    @DisplayName("Should create a course successfully")
    public void createCourseSuccessfully() throws CourseAlreadyExistException {
        Courses course = new Courses();
        when(coursesService.createCourse(course)).thenReturn(course);

        ResponseEntity<Courses> response = coursesController.createCourseHandler(course);

        assertEquals(course, response.getBody());
        verify(coursesService, times(1)).createCourse(course);
    }

    /**
     * This test checks if the controller correctly handles the case when a course already exists.
     */
    @Test
    @DisplayName("Should fail to create a course that already exists")
    public void createExistingCourse() throws CourseAlreadyExistException {
        Courses course = new Courses();
        when(coursesService.createCourse(course)).thenThrow(CourseAlreadyExistException.class);

        ResponseEntity<Courses> response = coursesController.createCourseHandler(course);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(coursesService, times(1)).createCourse(course);
    }

    /**
     * This test checks if the controller correctly fetches all courses.
     */
    @Test
    @DisplayName("Should retrieve all courses")
    public void getAllCourses() {
        Courses course1 = new Courses();
        Courses course2 = new Courses();
        List<Courses> coursesList = Arrays.asList(course1, course2);

        when(coursesService.getAllCourses()).thenReturn(coursesList);

        ResponseEntity<List<Courses>> response = coursesController.getAllCoursesHandler();

        assertEquals(coursesList, response.getBody());
        verify(coursesService, times(1)).getAllCourses();
    }

    /**
     * This test checks if the controller correctly fetches a course by its ID.
     */
    @Test
    @DisplayName("Should retrieve a course by its ID")
    public void getCourseById() throws NoSuchCourseException {
        Courses course = new Courses();
        when(coursesService.getCourseById(1)).thenReturn(Optional.of(course));

        ResponseEntity<Courses> response = coursesController.getCourseByIdHandler(1);

        assertEquals(course, response.getBody());
        verify(coursesService, times(1)).getCourseById(1);
    }

    /**
     * This test checks if the controller correctly handles the case when a course ID is invalid.
     */
    @Test
    @DisplayName("Should fail to retrieve a course by an invalid ID")
    public void getCourseByInvalidId() throws NoSuchCourseException {
        when(coursesService.getCourseById(1)).thenThrow(NoSuchCourseException.class);

        ResponseEntity<Courses> response = coursesController.getCourseByIdHandler(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(coursesService, times(1)).getCourseById(1);
    }

    /**
     * This test checks if the controller correctly deletes a course by its ID.
     */
    @Test
    @DisplayName("Should delete a course by its ID")
    public void deleteCourseById() throws NoSuchCourseException {
        Courses course = new Courses();
        when(coursesService.deleteCourse(1)).thenReturn(Optional.of(course));

        ResponseEntity<Courses> response = coursesController.deleteCourseHandler(1);

        assertEquals(course, response.getBody());
        verify(coursesService, times(1)).deleteCourse(1);
    }

    /**
     * This test checks if the controller correctly handles the case when a course ID is invalid for deletion.
     */
    @Test
    @DisplayName("Should fail to delete a course by an invalid ID")
    public void deleteCourseByInvalidId() throws NoSuchCourseException {
        when(coursesService.deleteCourse(1)).thenThrow(NoSuchCourseException.class);

        ResponseEntity<Courses> response = coursesController.deleteCourseHandler(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(coursesService, times(1)).deleteCourse(1);
    }

    /**
     * This test checks if the controller correctly assigns a teacher to a course.
     */
    @Test
    @DisplayName("Should assign a teacher to a course")
    public void assignTeacherToCourse() throws NoSuchCourseException, NoSuchUserException, InvalidRoleException, TeacherAlreadyPresentException {
        Users teacher = new Users();
        when(coursesService.assignTeacherToCourse(1, 1)).thenReturn(Optional.of(teacher));

        ResponseEntity<Users> response = coursesController.assignTeacherToCourseHandler(1, 1);

        assertEquals(teacher, response.getBody());
        verify(coursesService, times(1)).assignTeacherToCourse(1, 1);
    }

    /**
     * This test checks if the controller correctly handles the case when a course ID is invalid for assigning a teacher.
     */
    @Test
    @DisplayName("Should fail to assign a teacher to a course due to invalid course ID")
    public void assignTeacherToCourseInvalidCourseId() throws NoSuchCourseException, NoSuchUserException, InvalidRoleException, TeacherAlreadyPresentException {
        when(coursesService.assignTeacherToCourse(1, 1)).thenThrow(NoSuchCourseException.class);

        ResponseEntity<Users> response = coursesController.assignTeacherToCourseHandler(1, 1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(coursesService, times(1)).assignTeacherToCourse(1, 1);
    }

    /**
     * This test checks if the controller correctly fetches all students enrolled in a course.
     */
    @Test
    @DisplayName("Should retrieve all students enrolled in a course")
    public void getEnrolledStudents() throws NoSuchCourseException {
        Users student1 = new Users();
        Users student2 = new Users();
        HashSet<Users> students = new HashSet<>(Arrays.asList(student1, student2));

        when(coursesService.getEnrolledStudents(1)).thenReturn(students);

        ResponseEntity<?> response = coursesController.getEnrolledStudents(1);

        assertEquals(students, response.getBody());
        verify(coursesService, times(1)).getEnrolledStudents(1);
    }

    /**
     * This test checks if the controller correctly handles the case when a course ID is invalid for fetching enrolled students.
     */
    @Test
    @DisplayName("Should fail to retrieve students enrolled in a course due to invalid course ID")
    public void getEnrolledStudentsInvalidCourseId() throws NoSuchCourseException {
        when(coursesService.getEnrolledStudents(1)).thenThrow(NoSuchCourseException.class);

        ResponseEntity<?> response = coursesController.getEnrolledStudents(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(coursesService, times(1)).getEnrolledStudents(1);
    }
}