package com.revature.backend.controllers;

import com.revature.backend.exceptions.CourseAlreadyExistException;
import com.revature.backend.exceptions.InvalidRoleException;
import com.revature.backend.exceptions.NoSuchCourseException;
import com.revature.backend.exceptions.NoSuchUserException;
import com.revature.backend.models.Courses;
import com.revature.backend.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("courses")
@CrossOrigin(origins = {"http://localhost:3000"})
public class CoursesController {
    private final CoursesService coursesService;

    @Autowired
    public CoursesController(CoursesService coursesService){
        this.coursesService = coursesService;
    }

    @PostMapping("createCourse")
    public ResponseEntity<Courses> createCourseHandler(@RequestBody Courses course){
        Courses newCourse;
        try{
            newCourse = coursesService.createCourse(course);
        }catch(CourseAlreadyExistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newCourse,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Courses>> getAllCoursesHandler(){
        List<Courses> returnedCourses = coursesService.getAllCourses();
        return new ResponseEntity<>(returnedCourses, HttpStatus.OK);
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
    public ResponseEntity<Courses> assignTeacherToCourseHandler(@PathVariable int courseId, @PathVariable int teacherId){
        try{
            Optional<Courses> assignedCourse = coursesService.assignTeacherToCourse(courseId, teacherId);
            return new ResponseEntity<>(assignedCourse.get(), HttpStatus.OK);

        }catch (NoSuchCourseException | NoSuchUserException | InvalidRoleException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //assign student to course
    @PutMapping("/assignStudent/{courseId}/{studentId}")
    public ResponseEntity<Courses> assignStudentToCourseHandler(@PathVariable int courseId, @PathVariable int studentId){

        try{
            Optional<Courses> assignedCourse = coursesService.assignStudentToCourse(courseId, studentId);
            return new ResponseEntity<>(assignedCourse.get(), HttpStatus.OK);
        }catch (NoSuchCourseException | NoSuchUserException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (InvalidRoleException e) {
            throw new RuntimeException(e);
        }

    }
}
