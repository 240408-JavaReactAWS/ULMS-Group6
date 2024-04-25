package com.revature.backend.config;

import com.revature.backend.models.Roles;
import com.revature.backend.models.Users;
import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UsersDAO usersDAO;

    @Override
    public void run(String... args) throws Exception {
        if(usersDAO.findByUsername("admin").isEmpty()){
            Users adminUser = new Users("admin","admin","admin","password", Roles.ADMIN);
            usersDAO.save(adminUser);
        }
    }
}
