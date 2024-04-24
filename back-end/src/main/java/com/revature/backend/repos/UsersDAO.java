package com.revature.backend.repos;

import com.revature.backend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersDAO extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
}
