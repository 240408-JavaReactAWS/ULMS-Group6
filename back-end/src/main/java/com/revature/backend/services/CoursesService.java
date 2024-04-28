package com.revature.backend.services;

import com.revature.backend.exceptions.*;
import com.revature.backend.models.Courses;
import com.revature.backend.models.Roles;
import com.revature.backend.models.Users;
import com.revature.backend.repos.CoursesDAO;
import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for handling operations related to Courses.
 */
@Service
public class CoursesService {
    private CoursesDAO coursesDAO;
    private UsersDAO usersDAO;

    /**
     * Constructs a CoursesService with the specified CoursesDAO and UsersDAO.
     * @param coursesDAO the DAO to manage courses
     * @param usersDAO the DAO to manage users
     */
    @Autowired
    public CoursesService(CoursesDAO coursesDAO, UsersDAO usersDAO) {
        this.coursesDAO = coursesDAO;
        this.usersDAO = usersDAO;
    }

    /**
     * Creates a course if it does not already exist.
     * @param course the course to be created
     * @return the created course
     * @throws CourseAlreadyExistException if a course with the same name already exists
     */
    public Courses createCourse(Courses course) throws CourseAlreadyExistException {
        Optional<Courses> possibleCourse = coursesDAO.findCourseByCourseName(course.getCourseName());
        if(possibleCourse.isEmpty()){
            return coursesDAO.save(course);
        }
        throw new CourseAlreadyExistException("Course with courseName:"+course.getCourseName() + " already exist");
    }

    /**
     * Retrieves all courses.
     * @return a list of all courses
     */
    public List<Courses> getAllCourses(){
        return coursesDAO.findAll();
    }

    /**
     * Retrieves a course by its ID.
     * @param id the ID of the course
     * @return the course with the specified ID
     * @throws NoSuchCourseException if no course is found with the specified ID
     */
    public Optional<Courses> getCourseById(int id) throws NoSuchCourseException {
        Optional<Courses> possibleCourser =  coursesDAO.findById(id);
        if(possibleCourser.isEmpty()){
            throw new NoSuchCourseException("No course with id:"+ id + "found");
        }
        return possibleCourser;
    }

    /**
     * Deletes a course by its ID.
     * @param id the ID of the course to be deleted
     * @return the deleted course
     * @throws NoSuchCourseException if no course is found with the specified ID
     */
    public Optional<Courses> deleteCourse(int id) throws NoSuchCourseException {
        Optional<Courses> deletedCourse = coursesDAO.findById(id);
        if(deletedCourse.isPresent()){
            coursesDAO.deleteById(id);
        } else{
            throw new NoSuchCourseException("No course with id:"+ id + "found");
        }
        return deletedCourse;
    }

    /**
     * Assigns a teacher to a course.
     * @param courseId the ID of the course
     * @param teacherId the ID of the teacher
     * @return the teacher assigned to the course
     * @throws NoSuchCourseException if no course is found with the specified ID
     * @throws NoSuchUserException if no user is found with the specified ID
     * @throws InvalidRoleException if the user is not a teacher
     * @throws TeacherAlreadyPresentException if the course already has a teacher
     */
    public Optional<Users> assignTeacherToCourse(Integer courseId, Integer teacherId) throws NoSuchCourseException, NoSuchUserException, InvalidRoleException, TeacherAlreadyPresentException {
        Courses course = coursesDAO.findById(courseId).orElseThrow(() -> new NoSuchCourseException("No course with id:"+ courseId + "found"));
        Users teacher = usersDAO.findById(teacherId).orElseThrow(() -> new NoSuchUserException("No user with id:"+ teacherId + "found"));

        // check if the user is a teacher
        if(!teacher.getRole().equals(Roles.TEACHER)) {
            throw new InvalidRoleException("User with id:"+ teacherId + " is not a teacher");
        }

        // check if the course already has a teacher
        if(course.getTeacher() != null){
            throw new TeacherAlreadyPresentException("Course with id:"+ courseId + " already has a teacher");
        }

        course.setTeacher(teacher);
        coursesDAO.save(course);
        return Optional.of(teacher);
    }

    /**
     * Assigns a student to a course.
     * @param courseId the ID of the course
     * @param studentId the ID of the student
     * @return the student assigned to the course
     * @throws NoSuchCourseException if no course is found with the specified ID
     * @throws NoSuchUserException if no user is found with the specified ID
     * @throws InvalidRoleException if the user is not a student
     * @throws CourseFullException if the course is full
     */
    public Optional<Users> assignStudentToCourse(Integer courseId, Integer studentId) throws NoSuchCourseException, NoSuchUserException, InvalidRoleException, CourseFullException {
        Courses course = coursesDAO.findById(courseId).orElseThrow(() -> new NoSuchCourseException("No course with id:"+ courseId + "found"));
        Users student = usersDAO.findById(studentId).orElseThrow(() -> new NoSuchUserException("No user with id:"+ studentId + "found"));

        // check if the user is a student
        if(!student.getRole().equals(Roles.STUDENT)) {
            throw new InvalidRoleException("User with id:"+ studentId + " is not a student");
        }

        // check if the course is full
        if(course.getStudents().size() >= course.getCourseCapacity()){
            throw new CourseFullException("Course with id:"+ courseId + " is full");
        }

        course.getStudents().add(student);
        coursesDAO.save(course);
        return Optional.of(student);
    }

    /**
     * Retrieves all students enrolled in a course.
     * @param courseId the ID of the course
     * @return a set of students enrolled in the course
     * @throws NoSuchCourseException if no course is found with the specified ID
     */
    public Set<Users> getEnrolledStudents(Integer courseId) throws NoSuchCourseException {
        Courses course = coursesDAO.findById(courseId).orElseThrow(() -> new NoSuchCourseException("No course with id:"+ courseId + "found"));
        return course.getStudents();
    }

    /**
     * Retrieves the teacher of a course.
     * @param courseId the ID of the course
     * @return the teacher of the course
     * @throws NoSuchCourseException if no course is found with the specified ID
     */
    public Users getTeacher(Integer courseId) throws NoSuchCourseException {
        Courses course = coursesDAO.findById(courseId).orElseThrow(() -> new NoSuchCourseException("No course with id:"+ courseId + "found"));
        return course.getTeacher();
    }

    /**
     * Removes a student from a course.
     * @param courseId the ID of the course
     * @param studentId the ID of the student
     * @throws NoSuchCourseException if no course is found with the specified ID
     * @throws NoSuchUserException if no user is found with the specified ID
     */
    public void removeStudentFromCourse(Integer courseId, Integer studentId) throws NoSuchCourseException, NoSuchUserException {
        Courses course = coursesDAO.findById(courseId).orElseThrow(() -> new NoSuchCourseException("No course with id:"+ courseId + "found"));
        Users student = usersDAO.findById(studentId).orElseThrow(() -> new NoSuchUserException("No user with id:"+ studentId + "found"));
        course.getStudents().remove(student);
        coursesDAO.save(course);
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
