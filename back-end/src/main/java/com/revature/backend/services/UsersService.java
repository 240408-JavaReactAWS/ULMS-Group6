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

import java.util.*;

/**
 * Service class for handling operations related to Users.
 */
@Service
public class UsersService {

    private AnnouncementsDAO announcementsDAO;
    private UsersDAO usersDAO;
    private AssignmentsDAO assignmentsDAO;

    /**
     * Constructs a UsersService with the specified UsersDAO, AssignmentsDAO, and AnnouncementsDAO.
     * @param usersDAO the DAO to manage users
     * @param assignmentsDAO the DAO to manage assignments
     * @param announcementsDAO the DAO to manage announcements
     */
    @Autowired
    public UsersService(UsersDAO usersDAO, AssignmentsDAO assignmentsDAO, AnnouncementsDAO announcementsDAO) {
        this.usersDAO = usersDAO;
        this.assignmentsDAO  = assignmentsDAO;
        this.announcementsDAO = announcementsDAO;
    }

    /**
     * Retrieves all courses a student is enrolled in.
     * @param studentId the ID of the student
     * @return a set of courses the student is enrolled in
     * @throws NoSuchUserFoundException if no user is found with the specified ID
     */
    public Set<Courses> getEnrolledCourses(Integer studentId) throws NoSuchUserFoundException {
        Optional<Users> usersOptional = usersDAO.findById(studentId);
        if(usersOptional.isPresent()){
            Users user = usersOptional.get();
            return user.getEnrolledCourses();
        }else{
            throw new NoSuchUserFoundException("No user found with ID: " + studentId);
        }
    }

    /**
     * Retrieves all assignments for a specific student and course.
     * @param studentId the ID of the student
     * @param courseId the ID of the course
     * @return a list of assignments for the specified student and course
     */
    public List<Assignments> getAssignmentsByCourseAndStudent(Integer studentId, Integer courseId) {
        return assignmentsDAO.findByCourse_Students_UserIdAndCourse_CourseId(studentId, courseId);
    }

    /**
     * Retrieves all announcements for a specific student and course.
     * @param studentId the ID of the student
     * @param courseId the ID of the course
     * @return a list of announcements for the specified student and course
     */
    public List<Announcements> getAllAnnouncementsByCourseId(Integer studentId, Integer courseId){
        return announcementsDAO.findByCourse_Students_UserIdAndCourse_CourseId(studentId, courseId);
    }

    /**
     * Checks if a user's login credentials are correct.
     * @param user the user to be logged in
     * @return true if the login credentials are correct, false otherwise
     */
    public boolean login (Users user) {
        Optional<Users> existingUser = usersDAO.findByUsername(user.getUsername());
        return existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword());
    }

    /**
     * Retrieves all users.
     * @return a list of all users
     */
    public List<Users> getAllUsers() {
        return usersDAO.findAll();
    }

    /**
     * Retrieves a user by its ID.
     * @param id the ID of the user
     * @return the user with the specified ID
     * @throws NoSuchUserException if no user is found with the specified ID
     */
    public Optional<Users> getUserById(Integer id) throws NoSuchUserException {
        try{
            return usersDAO.findById(id);
        }catch (Exception e){
            throw new NoSuchUserException("No user with id:"+id + " found");
        }

    }

    /**
     * Creates a user if the username is not already taken.
     * @param user the user to be created
     * @return the created user
     * @throws UsernameAlreadyTakenException if the username is already taken
     */
    public Users createUser(Users user) throws UsernameAlreadyTakenException {
        Optional<Users> possibleUser = usersDAO.findByUsername(user.getUsername());
        if (possibleUser.isPresent()) {
            throw new UsernameAlreadyTakenException("Username:" + user.getUsername() + " was already taken!");
        }
        return usersDAO.save(user);
    }

    /**
     * Deletes a user by its ID.
     * @param id the ID of the user to be deleted
     * @throws NoSuchUserException if no user is found with the specified ID
     * @throws ForbiddenException if the user is an admin
     */
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