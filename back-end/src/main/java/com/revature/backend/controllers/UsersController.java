package com.revature.backend.controllers;

import com.revature.backend.exceptions.NoSuchUserFoundException;
import com.revature.backend.models.Assignments;
import com.revature.backend.models.Courses;
import com.revature.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
//@RequestMapping("users")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UsersController {

    private UsersService userService;

    @Autowired
    public UsersController(UsersService userService){
        this.userService = userService;
    }

    //As a Student, I can view all my courses.
    @GetMapping("users/{userId}/courses")
    public ResponseEntity<?> getEnrolledCourses(@PathVariable Integer userId) {
        try {
            Set<Courses> enrolledCourses = userService.getEnrolledCourses(userId);
            return ResponseEntity.ok(enrolledCourses);
        } catch (NoSuchUserFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No user found with ID: " + userId);
        }
    }

    //As a Student, I can check my assignments and due dates.
    @GetMapping("users/{userId}/courses/{courseId}/assignments")
    public List<Assignments> getAssignmentsForUserAndCourse(@PathVariable Integer userId, @PathVariable Integer courseId) {
        // Call the service method to fetch assignments for the user and course
        return userService.getAssignmentsForUserAndCourse(userId, courseId);
    }
}
