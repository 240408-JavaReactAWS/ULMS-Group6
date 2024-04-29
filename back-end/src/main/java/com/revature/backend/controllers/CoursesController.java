package com.revature.backend.controllers;

import com.revature.backend.config.CourseDTO;
import com.revature.backend.config.UserDTO;
import com.revature.backend.exceptions.*;
import com.revature.backend.models.Courses;
import com.revature.backend.models.Users;
import com.revature.backend.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Controller class for handling HTTP requests related to Courses.
 */
@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = {"http://localhost:3000"})
public class CoursesController {
    private final CoursesService coursesService;

    /**
     * Constructs a CoursesController with the specified CoursesService.
     * @param coursesService the service to manage courses
     */
    @Autowired
    public CoursesController(CoursesService coursesService){
        this.coursesService = coursesService;
    }

    /**
     * Handles the POST request to create a course.
     * @param course the course to be created
     * @return a ResponseEntity containing the created course, or a bad request status if the course already exists
     */
    @PostMapping("createCourse")
    public ResponseEntity<Courses> createCourseHandler(@RequestBody Courses course){
        try{
            Courses newCourse = coursesService.createCourse(course);
            return new ResponseEntity<>(newCourse,HttpStatus.CREATED);
        }catch(CourseAlreadyExistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handles the GET request to retrieve all courses.
     * @return a ResponseEntity containing a list of all courses
     */
    @GetMapping
    public ResponseEntity<List<Courses>> getAllCoursesHandler(){
        List<Courses> returnedCourses = coursesService.getAllCourses();
        return new ResponseEntity<>(returnedCourses, HttpStatus.OK);
    }

    /**
     * Handles the GET request to retrieve a list of CourseDTOs.
     * @return a ResponseEntity containing a list of CourseDTOs
     */
    @GetMapping("/courseList")
    public ResponseEntity<List<CourseDTO>> getCourseList(){
        List<Courses> returnedCourses = coursesService.getAllCourses();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Courses course : returnedCourses){
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setId(course.getCourseId());
            courseDTO.setCourseCapacity(course.getCourseCapacity());
            UserDTO teacherDTO = new UserDTO();
            teacherDTO.setId(course.getTeacher().getUserId());
            teacherDTO.setFirstName(course.getTeacher().getFirstName());
            teacherDTO.setLastName(course.getTeacher().getLastName());
            courseDTO.setTeacher(teacherDTO);
            courseDTOList.add(courseDTO);
        }

        return new ResponseEntity<>(courseDTOList, HttpStatus.OK);
    }

    /**
     * Handles the GET request to retrieve a course by its ID.
     * @param id the ID of the course
     * @return a ResponseEntity containing the course, or a bad request status if the course does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Courses> getCourseByIdHandler(@PathVariable int id){
        try{
            Optional<Courses> returnedCourse = coursesService.getCourseById(id);
            return new ResponseEntity<>(returnedCourse.get(), HttpStatus.OK);
        }catch (NoSuchCourseException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handles the DELETE request to delete a course by its ID.
     * @param id the ID of the course
     * @return a ResponseEntity containing the deleted course, or a bad request status if the course does not exist
     */
    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<Courses> deleteCourseHandler(@PathVariable int id) throws NoSuchCourseException {
        Optional<Courses> deletedCourse;
        try{
            deletedCourse = coursesService.deleteCourse(id);
        }catch (NoSuchCourseException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(deletedCourse.get(), HttpStatus.OK);
    }

    /**
     * Handles the PUT request to assign a teacher to a course.
     * @param courseId the ID of the course
     * @param teacherId the ID of the teacher
     * @return a ResponseEntity containing the assigned course, or a bad request status if the course or teacher does not exist, the role is invalid, or a teacher is already present
     */
    @PutMapping("/assignTeacher/{courseId}/{teacherId}")
    public ResponseEntity<Users> assignTeacherToCourseHandler(@PathVariable int courseId, @PathVariable int teacherId){
        try{
            Optional<Users> assignedCourse = coursesService.assignTeacherToCourse(courseId, teacherId);
            return new ResponseEntity<>(assignedCourse.get(), HttpStatus.OK);

        }catch (NoSuchCourseException | NoSuchUserException | InvalidRoleException | TeacherAlreadyPresentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handles the PUT request to assign a student to a course.
     * @param courseId the ID of the course
     * @param studentId the ID of the student
     * @return a ResponseEntity containing the assigned course, or a bad request status if the course or student does not exist, the role is invalid, or the course is full
     */
    @PutMapping("/assignStudent/{courseId}/{studentId}")
    public ResponseEntity<Users> assignStudentToCourseHandler(@PathVariable int courseId, @PathVariable int studentId){
        try{
            Optional<Users> assignedCourse = coursesService.assignStudentToCourse(courseId, studentId);
            return new ResponseEntity<>(assignedCourse.get(), HttpStatus.OK);
        }catch (NoSuchCourseException | NoSuchUserException | InvalidRoleException | CourseFullException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Handles the GET request to retrieve the students enrolled in a course.
     * @param courseId the ID of the course
     * @return a ResponseEntity containing a set of the enrolled students, or a bad request status if the course does not exist
     */
    @GetMapping("/{courseId}/students")
    public ResponseEntity<?> getEnrolledStudents(@PathVariable Integer courseId) {
        try {
            Set<Users> enrolledStudents = coursesService.getEnrolledStudents(courseId);
            return new ResponseEntity<>(enrolledStudents, HttpStatus.OK);
        } catch (NoSuchCourseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handles the GET request to retrieve the teacher of a course.
     * @param courseId the ID of the course
     * @return a ResponseEntity containing the teacher, or a bad request status if the course does not exist
     */
    @GetMapping("/{courseId}/teacher")
    public ResponseEntity<?> getTeacher(@PathVariable Integer courseId) {
        try {
            Users teacher = coursesService.getTeacher(courseId);
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } catch (NoSuchCourseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handles the DELETE request to remove a student from a course.
     * @param courseId the ID of the course
     * @param studentId the ID of the student
     * @return a ResponseEntity with an OK status if the operation is successful, or a bad request status if the course or student does not exist
     */
    @DeleteMapping("/removeStudent/{courseId}/{studentId}")
    public ResponseEntity<?> removeStudentFromCourse(@PathVariable Integer courseId, @PathVariable Integer studentId) {
        try {
            coursesService.removeStudentFromCourse(courseId, studentId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchCourseException | NoSuchUserException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
