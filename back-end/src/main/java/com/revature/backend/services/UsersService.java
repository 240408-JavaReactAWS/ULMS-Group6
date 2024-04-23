package com.revature.backend.services;


import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.backend.models.Users;

@Service
public class UsersService {
    private UsersDAO usersDAO;

    @Autowired
    public UsersService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public boolean login (Users user) {
        return usersDAO.findUser(user.getUsername(), user.getPassword());
    }
}
