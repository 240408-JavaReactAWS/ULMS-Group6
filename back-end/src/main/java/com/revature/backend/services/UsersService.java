package com.revature.backend.services;



import com.revature.backend.exceptions.ForbiddenException;
import com.revature.backend.exceptions.NoSuchUserException;
import com.revature.backend.exceptions.UsernameAlreadyTakenException;
import com.revature.backend.models.Roles;


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

import java.util.List;
import java.util.Optional;

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
        Optional<Users> existingUser = usersDAO.findByUsername(user.getUsername());
        return existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword());
    }

    public List<Users> getAllUsers() {
        return usersDAO.findAll();
    }

    public Optional<Users> getUserById(Integer id) throws NoSuchUserException {
        try{
            return usersDAO.findById(id);
        }catch (Exception e){
            throw new NoSuchUserException("No user with id:"+id + " found");
        }

    }

    public Users createUser(Users user) throws UsernameAlreadyTakenException {
        Optional<Users> possibleUser = usersDAO.findByUsername(user.getUsername());
        if (possibleUser.isPresent()) {
            throw new UsernameAlreadyTakenException("Username:" + user.getUsername() + " was already taken!");
        }
        return usersDAO.save(user);
    }

    public void deleteUser(int id) throws NoSuchUserException, ForbiddenException {
        Optional<Users> userToDelete = usersDAO.findById(id);

        if(userToDelete.isPresent()){
            Users user = userToDelete.get();

            if(user.getRole() == Roles.ADMIN){
                throw new ForbiddenException("Admin User with userid:"+ id + " cannot be deleted from database");
            }
            usersDAO.deleteById(id);
        } else {
            throw new NoSuchUserException("No user with id:" + id + "found");
        }
    }
}