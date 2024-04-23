package com.revature.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.backend.models.Users;

import com.revature.backend.services.UsersService;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {
    private UsersService us;

    public UsersController(UsersService userS) {
        this.us = userS;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        if(us.login(user)) {
            return ResponseEntity.ok().body("Login Success!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}
