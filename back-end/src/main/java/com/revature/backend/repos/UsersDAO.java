package com.revature.backend.repos;

import com.revature.backend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);

    boolean findUser(String useName, String pword);
}
