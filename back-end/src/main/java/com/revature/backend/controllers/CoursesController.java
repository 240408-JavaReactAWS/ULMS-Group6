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

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = {"http://localhost:3000"})
public class CoursesController {
    private final CoursesService coursesService;

    @Autowired
    public CoursesController(CoursesService coursesService){
        this.coursesService = coursesService;
    }

    @PostMapping("createCourse")
    public ResponseEntity<Courses> createCourseHandler(@RequestBody Courses course){
        try{
            Courses newCourse = coursesService.createCourse(course);
            return new ResponseEntity<>(newCourse,HttpStatus.CREATED);
        }catch(CourseAlreadyExistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Courses>> getAllCoursesHandler(){
        List<Courses> returnedCourses = coursesService.getAllCourses();
        return new ResponseEntity<>(returnedCourses, HttpStatus.OK);
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<Courses> getCourseByIdHandler(@PathVariable int id){
        try{
            Optional<Courses> returnedCourse = coursesService.getCourseById(id);
            return new ResponseEntity<>(returnedCourse.get(), HttpStatus.OK);
        }catch (NoSuchCourseException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // delete course by id
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

    // assign teacher to course
    @PutMapping("/assignTeacher/{courseId}/{teacherId}")
    public ResponseEntity<Users> assignTeacherToCourseHandler(@PathVariable int courseId, @PathVariable int teacherId){
        try{
            Optional<Users> assignedCourse = coursesService.assignTeacherToCourse(courseId, teacherId);
            return new ResponseEntity<>(assignedCourse.get(), HttpStatus.OK);

        }catch (NoSuchCourseException | NoSuchUserException | InvalidRoleException | TeacherAlreadyPresentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //assign student to course
    @PutMapping("/assignStudent/{courseId}/{studentId}")
    public ResponseEntity<Users> assignStudentToCourseHandler(@PathVariable int courseId, @PathVariable int studentId){
        try{
            Optional<Users> assignedCourse = coursesService.assignStudentToCourse(courseId, studentId);
            return new ResponseEntity<>(assignedCourse.get(), HttpStatus.OK);
        }catch (NoSuchCourseException | NoSuchUserException | InvalidRoleException | CourseFullException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<?> getEnrolledStudents(@PathVariable Integer courseId) {
        try {
            Set<Users> enrolledStudents = coursesService.getEnrolledStudents(courseId);
            return new ResponseEntity<>(enrolledStudents, HttpStatus.OK);
        } catch (NoSuchCourseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{courseId}/teacher")
    public ResponseEntity<?> getTeacher(@PathVariable Integer courseId) {
        try {
            Users teacher = coursesService.getTeacher(courseId);
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } catch (NoSuchCourseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

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
