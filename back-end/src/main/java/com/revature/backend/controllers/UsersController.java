package com.revature.backend.controllers;


import com.revature.backend.models.Users;
import com.revature.backend.exceptions.NoSuchUserFoundException;
import com.revature.backend.models.Announcements;
import com.revature.backend.models.Assignments;
import com.revature.backend.models.Courses;
import com.revature.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
//@CrossOrigin(origins = {"http://localhost:3000/users"})
public class UsersController {

    private UsersService userService;

    @Autowired
    public UsersController(UsersService userService){
        this.userService = userService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        if(userService.login(user)) {
            return ResponseEntity.ok().body("Login Success!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
  
    //As a Student, I can view all my courses.
    @GetMapping("users/{studentId}/courses")
    public ResponseEntity<?> getEnrolledCourses(@PathVariable Integer studentId) {
        try {
            Set<Courses> enrolledCourses = userService.getEnrolledCourses(studentId);
            return ResponseEntity.ok(enrolledCourses);
        } catch (NoSuchUserFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No user found with ID: " + studentId);
        }
    }

    //As a Student, I can check my assignments and due dates.

    @GetMapping("/{studentId}/courses/{courseId}/assignments")
    public List<Assignments> getAssignmentsForUserAndCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        // Call the service method to fetch assignments for the user and course
        return userService.getAssignmentsByCourseAndStudent(studentId, courseId);
    }

    //As a Student, I can check course Announcements for different courses.

    @GetMapping("/{studentId}/courses/{courseId}/announcements")
    public ResponseEntity<List<Announcements>> getAnnouncementsForStudentAndCourse(
            @PathVariable("studentId") Integer studentId,
            @PathVariable("courseId") Integer courseId) {

        List<Announcements> announcements = userService.getAllAnnouncementsByCourseId(studentId, courseId);
        if (announcements.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }
}
