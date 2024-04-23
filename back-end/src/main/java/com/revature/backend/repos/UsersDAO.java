package com.revature.backend.repos;

import com.revature.backend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
    //Used in- As a Student, I can check my assignments and due dates.
    Users findByUserId(Integer userId);
}
