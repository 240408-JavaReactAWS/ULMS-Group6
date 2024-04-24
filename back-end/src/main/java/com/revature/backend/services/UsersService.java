package com.revature.backend.services;


import com.revature.backend.exceptions.ForbiddenException;
import com.revature.backend.exceptions.NoSuchUserException;
import com.revature.backend.exceptions.UsernameAlreadyTakenException;
import com.revature.backend.models.Roles;
import com.revature.backend.models.Users;
import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersDAO usersDAO;

    @Autowired
    public UsersService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
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

    public Optional<Users> deleteUser(int id) throws NoSuchUserException, ForbiddenException {
        Optional<Users> userToDelete = usersDAO.findById(id);

        if(userToDelete.isPresent()){
            Users user = userToDelete.get();

            if(user.getRole() == Roles.ADMIN){
                throw new ForbiddenException("Admin User with userid:"+ id + " cannot be deleted from database");
            }
            usersDAO.deleteById(id);
        } else{
            throw new NoSuchUserException("No user with id:"+ id + "found");
        }

        return userToDelete;
    }
}