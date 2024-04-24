package com.revature.backend.controllers;
import com.revature.backend.exceptions.ForbiddenException;
import com.revature.backend.exceptions.NoSuchUserException;
import com.revature.backend.exceptions.UsernameAlreadyTakenException;
import com.revature.backend.models.Users;
import com.revature.backend.exceptions.NoSuchUserFoundException;
import com.revature.backend.models.Announcements;
import com.revature.backend.models.Assignments;
import com.revature.backend.models.Courses;
import com.revature.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService us){
        this.usersService = us;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsersHandler(){
        List<Users> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserByIdHandler(@PathVariable Integer id){
        try{
            Optional<Users> returnedUser = usersService.getUserById(id);
            return new ResponseEntity<>(returnedUser.get(), HttpStatus.OK);
        }catch (NoSuchUserException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUserHandler(@RequestBody Users user){
        try{
            Users savedUser = usersService.createUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }catch (UsernameAlreadyTakenException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // delete user by id
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
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        if(usersService.login(user)) {
            return ResponseEntity.ok().body("Login Success!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
  
    //As a Student, I can view all my courses.
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

    //As a Student, I can check my assignments and due dates.
    @GetMapping("/{studentId}/courses/{courseId}/assignments")
    public List<Assignments> getAssignmentsForUserAndCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        // Call the service method to fetch assignments for the user and course
        return usersService.getAssignmentsByCourseAndStudent(studentId, courseId);
    }

    //As a Student, I can check course Announcements for different courses.
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
