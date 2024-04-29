package com.revature.backend;

import com.revature.backend.controllers.UsersController;
import com.revature.backend.exceptions.ForbiddenException;
import com.revature.backend.exceptions.NoSuchUserException;
import com.revature.backend.exceptions.UsernameAlreadyTakenException;
import com.revature.backend.models.Roles;
import com.revature.backend.models.Users;
import com.revature.backend.services.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class is a test class for the UsersController.
 * It uses JUnit and Mockito for testing.
 */
public class UsersControllerTest {

    // The controller that we're testing
    @InjectMocks
    private UsersController usersController;

    // The service that the controller depends on
    @Mock
    private UsersService usersService;

    /**
     * This method sets up the mocks before each test.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * This test checks if the controller correctly fetches all users.
     */
    @Test
    @DisplayName("Should return all users")
    public void getAllUsers() {
        Users user1 = new Users();
        Users user2 = new Users();
        List<Users> usersList = Arrays.asList(user1, user2);

        when(usersService.getAllUsers()).thenReturn(usersList);

        ResponseEntity<List<Users>> response = usersController.getAllUsersHandler();

        assertEquals(usersList, response.getBody());
        verify(usersService, times(1)).getAllUsers();
    }

    /**
     * This test checks if the controller correctly fetches a user by its ID.
     */
    @Test
    @DisplayName("Should return a user by its ID")
    public void getUserById() throws NoSuchUserException {
        Users user = new Users();
        when(usersService.getUserById(1)).thenReturn(Optional.of(user));

        ResponseEntity<Users> response = usersController.getUserByIdHandler(1);

        assertEquals(user, response.getBody());
        verify(usersService, times(1)).getUserById(1);
    }

    /**
     * This test checks if the controller correctly registers a user.
     */
    @Test
    @DisplayName("Should register a user")
    public void registerUser() throws UsernameAlreadyTakenException {
        Users user = new Users();
        when(usersService.createUser(user)).thenReturn(user);

        ResponseEntity<Users> response = usersController.registerUserHandler(user);

        assertEquals(user, response.getBody());
        verify(usersService, times(1)).createUser(user);
    }

    /**
     * This test checks if the controller correctly deletes a user by its ID.
     */
    @Test
    @DisplayName("Should delete a user by its ID")
    public void deleteUser() throws NoSuchUserException, ForbiddenException {
        doNothing().when(usersService).deleteUser(1);

        ResponseEntity<Users> response = usersController.deleteUserHandler(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(usersService, times(1)).deleteUser(1);
    }

    /**
     * This test checks if the controller correctly fetches all students.
     */
    @Test
    @DisplayName("Should return all students")
    public void getAllStudents() {
        Users student1 = new Users();
        student1.setRole(Roles.STUDENT);
        Users student2 = new Users();
        student2.setRole(Roles.STUDENT);
        List<Users> studentsList = Arrays.asList(student1, student2);

        when(usersService.getAllUsers()).thenReturn(studentsList);

        ResponseEntity<List<Users>> response = usersController.getAllStudentsHandler();

        assertEquals(studentsList, response.getBody());
        verify(usersService, times(1)).getAllUsers();
    }

    /**
     * This test checks if the controller correctly fetches all teachers.
     */
    @Test
    @DisplayName("Should return all teachers")
    public void getAllTeachers() {
        Users teacher1 = new Users();
        teacher1.setRole(Roles.TEACHER);
        Users teacher2 = new Users();
        teacher2.setRole(Roles.TEACHER);
        List<Users> teachersList = Arrays.asList(teacher1, teacher2);

        when(usersService.getAllUsers()).thenReturn(teachersList);

        ResponseEntity<List<Users>> response = usersController.getAllTeachersHandler();

        assertEquals(teachersList, response.getBody());
        verify(usersService, times(1)).getAllUsers();
    }
}