package com.revature.backend.services;


import com.revature.backend.exceptions.NoSuchUserFoundException;
import com.revature.backend.models.Assignments;
import com.revature.backend.models.Courses;
import com.revature.backend.models.Users;
import com.revature.backend.repos.AssignmentsDAO;
import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersService {
    private UsersDAO usersDAO;
    private AssignmentsDAO assignmentsDAO;

    @Autowired
    public UsersService(UsersDAO usersDAO, AssignmentsDAO assignmentsDAO) {
        this.usersDAO = usersDAO;
        this.assignmentsDAO  = assignmentsDAO;
    }

    //As a Student, I can view all my courses.
    public Set<Courses> getEnrolledCourses(Integer userId) throws NoSuchUserFoundException {
        Optional<Users> usersOptional = usersDAO.findById(userId);
        if(usersOptional.isPresent()){
            Users user = usersOptional.get();
            return user.getEnrolledCourses();
        }else{
            throw new NoSuchUserFoundException("No user found with ID: " + userId);
        }
    }

    //As a Student, I can check my assignments and due dates.
    public List<Assignments> getAssignmentsForUserAndCourse(Integer userId, Integer courseId) {

        Users user = usersDAO.findByUserId(userId);
        if (user != null) {
            // Get the user's enrolled courses
            Set<Courses> enrolledCourses = user.getEnrolledCourses();
            // Check if the user is enrolled in the specified course
            boolean isEnrolled = false;
            for (Courses course : enrolledCourses) {
                if (course.getCourseId().equals(courseId)) {
                    isEnrolled = true;
                    break;
                }
            }
            // If user is not enrolled in the specified course, return empty list
            if (!isEnrolled) {
                return Collections.emptyList();
            }
            // Fetch assignments for the specified course
            List<Assignments> courseAssignments = assignmentsDAO.findByCourseId(courseId);
            return courseAssignments;
        } else {
            // Handle case where user is not found
            return Collections.emptyList();
        }
    }
}
