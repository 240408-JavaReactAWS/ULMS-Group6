package com.revature.backend.services;


import com.revature.backend.exceptions.CourseAlreadyExistException;
import com.revature.backend.exceptions.InvalidRoleException;
import com.revature.backend.exceptions.NoSuchCourseException;
import com.revature.backend.exceptions.NoSuchUserException;
import com.revature.backend.models.Courses;
import com.revature.backend.models.Roles;
import com.revature.backend.models.Users;
import com.revature.backend.repos.CoursesDAO;
import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesService {
    private CoursesDAO coursesDAO;
    private UsersDAO usersDAO;

    @Autowired
    public CoursesService(CoursesDAO coursesDAO, UsersDAO usersDAO) {
        this.coursesDAO = coursesDAO;
        this.usersDAO = usersDAO;
    }


    public Courses createCourse(Courses course) throws CourseAlreadyExistException {
        Optional<Courses> possibleCourse = coursesDAO.findCourseByCourseName(course.getCourseName());
        if(possibleCourse.isEmpty()){
            return coursesDAO.save(course);
        }
        throw new CourseAlreadyExistException("Course with courseName:"+course.getCourseName() + " already exist");
    }

    //create a method to get all courses
    public List<Courses> getAllCourses(){
        return coursesDAO.findAll();
    }

    public Optional<Courses> getCourseById(int id) throws NoSuchCourseException {
        return coursesDAO.findById(id);
    }

    public Optional<Courses> deleteCourse(int id) throws NoSuchCourseException {
        Optional<Courses> deletedCourse = coursesDAO.findById(id);
        if(deletedCourse.isPresent()){
            coursesDAO.deleteById(id);
        } else{
            throw new NoSuchCourseException("No course with id:"+ id + "found");
        }
        return deletedCourse;
    }

    public Optional<Courses> assignTeacherToCourse(Integer courseId, Integer teacherId) throws NoSuchCourseException, NoSuchUserException, InvalidRoleException {
        Courses course = coursesDAO.findById(courseId).orElseThrow(() -> new NoSuchCourseException("No course with id:"+ courseId + "found"));
        Users teacher = usersDAO.findById(teacherId).orElseThrow(() -> new NoSuchUserException("No user with id:"+ teacherId + "found"));

        if(!teacher.getRole().equals(Roles.TEACHER)) {
            throw new InvalidRoleException("User with id:"+ teacherId + " is not a teacher");
        }

        course.setTeacher(teacher);
        return Optional.of(coursesDAO.save(course));
    }

    public Optional<Courses> assignStudentToCourse(Integer courseId, Integer studentId) throws NoSuchCourseException, NoSuchUserException, InvalidRoleException {
        Courses course = coursesDAO.findById(courseId).orElseThrow(() -> new NoSuchCourseException("No course with id:"+ courseId + "found"));
        Users student = usersDAO.findById(studentId).orElseThrow(() -> new NoSuchUserException("No user with id:"+ studentId + "found"));

        if(!student.getRole().equals(Roles.STUDENT)) {
            throw new InvalidRoleException("User with id:"+ studentId + " is not a student");
        }

        course.getStudents().add(student);
        return Optional.of(coursesDAO.save(course));
    }
}
