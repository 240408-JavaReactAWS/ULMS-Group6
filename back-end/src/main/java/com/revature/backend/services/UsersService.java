package com.revature.backend.services;


import com.revature.backend.exceptions.NoSuchUserFoundException;
import com.revature.backend.models.Announcements;
import com.revature.backend.models.Assignments;
import com.revature.backend.models.Courses;
import com.revature.backend.models.Users;
import com.revature.backend.repos.AnnouncementsDAO;
import com.revature.backend.repos.AssignmentsDAO;
import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.backend.models.Users;

import java.util.*;

@Service
public class UsersService {

    private AnnouncementsDAO announcementsDAO;
    private UsersDAO usersDAO;
    private AssignmentsDAO assignmentsDAO;

    @Autowired
    public UsersService(UsersDAO usersDAO, AssignmentsDAO assignmentsDAO, AnnouncementsDAO announcementsDAO) {
        this.usersDAO = usersDAO;
        this.assignmentsDAO  = assignmentsDAO;
        this.announcementsDAO = announcementsDAO;
    }

    //As a Student, I can view all my courses.
    public Set<Courses> getEnrolledCourses(Integer studentId) throws NoSuchUserFoundException {
        Optional<Users> usersOptional = usersDAO.findById(studentId);
        if(usersOptional.isPresent()){
            Users user = usersOptional.get();
            return user.getEnrolledCourses();
        }else{
            throw new NoSuchUserFoundException("No user found with ID: " + studentId);
        }
    }

    //As a Student, I can check my assignments and due dates.
    public List<Assignments> getAssignmentsByCourseAndStudent(Integer studentId, Integer courseId) {
        return assignmentsDAO.findByCourse_Students_UserIdAndCourse_CourseId(studentId, courseId);
    }

    //As a Student, I can check course Announcements for different courses.
    public List<Announcements> getAllAnnouncementsByCourseId(Integer studentId, Integer courseId){
        return announcementsDAO.findByCourse_Students_UserIdAndCourse_CourseId(studentId, courseId);
    }

    public boolean login (Users user) {
        Users existingUser = usersDAO.findByUsername(user.getUsername());
        return existingUser != null && existingUser.getPassword().equals(user.getPassword());
    }
}
