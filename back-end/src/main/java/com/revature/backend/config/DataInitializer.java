package com.revature.backend.config;

import com.revature.backend.models.Roles;
import com.revature.backend.models.Users;
import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for initializing the database with some default data.
 * It implements CommandLineRunner, which means Spring Boot will execute it after the application context is loaded.
 */
@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UsersDAO usersDAO;

    /**
     * This method will be executed after the application context is loaded.
     * It checks if a user with username "admin" exists in the database, and if not, it creates one.
     * @param args command line arguments. This method does not use them.
     * @throws Exception if an error occurs during the execution.
     */
    @Override
    public void run(String... args) throws Exception {
        if(usersDAO.findByUsername("admin").isEmpty()){
            Users adminUser = new Users("admin","admin","admin","password", Roles.ADMIN);
            usersDAO.save(adminUser);
        }
    }
}