package com.revature.backend.repos;

import com.revature.backend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface represents the Users Data Access Object (DAO) in the application.
 * It extends JpaRepository to inherit the default methods for CRUD operations.
 * It also includes custom methods for finding users by username and user ID.
 */
@Repository
public interface UsersDAO extends JpaRepository<Users, Integer> {

    /**
     * Finds a user by their username.
     * @param username The username of the user.
     * @return An Optional that may contain the user if one with the specified username exists.
     */
    Optional<Users> findByUsername(String username);

    /**
     * Finds a user by their user ID.
     * This method is used in the user story: "As a Student, I can check my assignments and due dates."
     * @param userId The ID of the user.
     * @return The user with the specified ID.
     */
    Users findByUserId(Integer userId);
}