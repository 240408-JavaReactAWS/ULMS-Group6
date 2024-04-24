package com.revature.backend.controllers;

import com.revature.backend.exceptions.ForbiddenException;
import com.revature.backend.exceptions.NoSuchUserException;
import com.revature.backend.exceptions.UsernameAlreadyTakenException;
import com.revature.backend.models.Users;
import com.revature.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService us){
        this.usersService = us;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsersHandler(){
        List<Users> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserByIdHandler(@PathVariable Integer id){
        try{
            Optional<Users> returnedUser = usersService.getUserById(id);
            return new ResponseEntity<>(returnedUser.get(), HttpStatus.OK);
        }catch (NoSuchUserException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Users> registerUserHandler(@RequestBody Users user){
        try{
            Users savedUser = usersService.createUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        }catch (UsernameAlreadyTakenException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // delete user by id

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Users> deleteUserHandler(@PathVariable Integer id) throws NoSuchUserException, ForbiddenException {
        try{
            Optional<Users> deletedUser = usersService.deleteUser(id);
            return new ResponseEntity<>(deletedUser.get(),HttpStatus.OK);
        } catch (NoSuchUserException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ForbiddenException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
