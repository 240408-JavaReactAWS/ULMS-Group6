package com.revature.backend.controllers;

import com.revature.backend.exceptions.ForbiddenException;
import com.revature.backend.exceptions.NoSuchUserException;
import com.revature.backend.exceptions.UsernameAlreadyTakenException;
import com.revature.backend.models.*;
import com.revature.backend.exceptions.NoSuchUserFoundException;
import com.revature.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller class for handling HTTP requests related to Users.
 */
@RestController
@RequestMapping("users")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UsersController {

    private final UsersService usersService;

    /**
     * Constructs a UsersController with the specified UsersService.
     * @param us the service to manage users
     */
    @Autowired
    public UsersController(UsersService us){
        this.usersService = us;
    }

    /**
     * Handles the GET request to retrieve all users.
     * @return a ResponseEntity containing a list of all users
     */
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsersHandler(){
        List<Users> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Handles the GET request to retrieve all students.
     * @return a ResponseEntity containing a list of all students
     */
    @GetMapping("/students")
    public ResponseEntity<List<Users>> getAllStudentsHandler(){
        List<Users> students = usersService.getAllUsers();
        students = students.stream().filter(user -> user.getRole().equals(Roles.STUDENT)).collect(Collectors.toList());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    /**
     * Handles the GET request to retrieve all teachers.
     * @return a ResponseEntity containing a list of all teachers
     */
    @GetMapping("/teachers")
    public ResponseEntity<List<Users>> getAllTeachersHandler(){
        List<Users> teachers = usersService.getAllUsers();
        teachers = teachers.stream().filter(user -> user.getRole().equals(Roles.TEACHER)).collect(Collectors.toList());
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    /**
     * Handles the GET request to retrieve a user by its ID.
     * @param id the ID of the user
     * @return a ResponseEntity containing the user, or a bad request status if the user does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserByIdHandler(@PathVariable Integer id){
        try{
            Optional<Users> returnedUser = usersService.getUserById(id);
            return new ResponseEntity<>(returnedUser.get(), HttpStatus.OK);
        }catch (NoSuchUserException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handles the POST request to register a user.
     * @param user the user to be registered
     * @return a ResponseEntity containing the registered user, or a bad request status if the username is already taken
     */
    @PostMapping("/register")
    public ResponseEntity<Users> registerUserHandler(@RequestBody Users user){
        try{
            Users savedUser = usersService.createUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }catch (UsernameAlreadyTakenException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handles the DELETE request to delete a user by its ID.
     * @param id the ID of the user
     * @return a ResponseEntity with an OK status if the operation is successful, a no content status if the user does not exist, or a forbidden status if the operation is not allowed
     */
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Users> deleteUserHandler(@PathVariable Integer id) throws NoSuchUserException, ForbiddenException {
        try{
            usersService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchUserException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ForbiddenException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Handles the POST request to login a user.
     * @param user the user to be logged in
     * @return a ResponseEntity with an OK status and a success message if the operation is successful, or an unauthorized status and an error message if the operation is not successful
     */
    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody Users user) {
        if(usersService.login(user) != null) {
            return ResponseEntity.ok(usersService.login(user));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    /**
     * Handles the GET request to retrieve all courses a student is enrolled in.
     * @param studentId the ID of the student
     * @return a ResponseEntity containing a set of the enrolled courses, or a not found status and an error message if the student does not exist
     */
    @GetMapping("/{studentId}/courses")
    public ResponseEntity<?> getEnrolledCourses(@PathVariable Integer studentId) {
        try {
            Set<Courses> enrolledCourses = usersService.getEnrolledCourses(studentId);
            Set<Map<String, Object>> response = new HashSet<>();

            for (Courses course : enrolledCourses) {
                Map<String, Object> courseDetails = new HashMap<>();
                courseDetails.put("courseId", course.getCourseId());
                courseDetails.put("courseName", course.getCourseName());
                courseDetails.put("teacherName", course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName());
                response.add(courseDetails);
            }

            return ResponseEntity.ok(response);
        } catch (NoSuchUserFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No user found with ID: " + studentId);
        }
    }

    /**
     * Handles the GET request to retrieve all courses a teacher is teaching.
     * @param teacherId the ID of the teacher
     * @return  a ResponseEntity containing a set of the taught courses, or a not found status and an error message if the teacher does not exist
     */
    @GetMapping("/{teacherId}/taught")
    public ResponseEntity<?> getTaughtCourses(@PathVariable Integer teacherId) {
        try {
            List<Courses> taughtCourses = usersService.getTaughtCourses(teacherId);
            Set<Map<String, Object>> response = new HashSet<>();

            for (Courses course : taughtCourses) {
                Map<String, Object> courseDetails = new HashMap<>();
                courseDetails.put("courseId", course.getCourseId());
                courseDetails.put("courseName", course.getCourseName());
                courseDetails.put("teacherName", course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName());
                response.add(courseDetails);
            }

            return ResponseEntity.ok(response);
        } catch (NoSuchUserFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No user found with ID: " + teacherId);
        }
    }

    /**
     * Handles the GET request to retrieve all assignments and due dates for a specific student and course.
     * @param studentId the ID of the student
     * @param courseId the ID of the course
     * @return a list of all assignments for the student and course
     */
    @GetMapping("/{studentId}/courses/{courseId}/assignments")
    public List<Assignments> getAssignmentsForUserAndCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        // Call the service method to fetch assignments for the user and course
        return usersService.getAssignmentsByCourseAndStudent(studentId, courseId);
    }

    /**
     * Handles the GET request to retrieve all announcements for a specific student and course.
     * @param studentId the ID of the student
     * @param courseId the ID of the course
     * @return a ResponseEntity containing a list of all announcements for the student and course, or a no content status if the list is empty
     */
    @GetMapping("/{studentId}/courses/{courseId}/announcements")
    public ResponseEntity<List<Announcements>> getAnnouncementsForStudentAndCourse(
            @PathVariable("studentId") Integer studentId,
            @PathVariable("courseId") Integer courseId) {

        List<Announcements> announcements = usersService.getAllAnnouncementsByCourseId(studentId, courseId);
        if (announcements.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }
}