package com.revature.backend.services;


import com.revature.backend.exceptions.NoSuchUserFoundException;
import com.revature.backend.models.Courses;
import com.revature.backend.models.Users;
import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersService {
    private UsersDAO usersDAO;

    @Autowired
    public UsersService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public Set<Courses> getEnrolledCourses(Integer userId) throws NoSuchUserFoundException {
        Optional<Users> usersOptional = usersDAO.findById(userId);
        if(usersOptional.isPresent()){
            Users user = usersOptional.get();
            return user.getEnrolledCourses();
        }else{
            throw new NoSuchUserFoundException("No user found with ID: " + userId);
        }
    }

}
