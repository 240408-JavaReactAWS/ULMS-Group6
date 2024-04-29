## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 17
- Maven
- PostgreSQL

### Installation

1. Clone the repository
   ```sh
   git clone git clone https://github.com/your-repository-url.git
   ```
2. Change directory to the project
   ```sh
    cd your-repository
    cd back-end
    ```
3. Install dependencies
    ```sh
    mvn clean install
    ```
4. Update the `src/main/resources/application.properties` file with your PostgreSQL credentials
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    ```
5. Run the application
    ```sh
    mvn spring-boot:run
    ```
6. The application will be accessible at `http://localhost:8080`

---
# Backend API Endpoints

This document outlines the endpoints available in the backend API.

---

## How to Use Example Endpoint

This section provides a tutorial on how to Document a endpoint

### Endpoint Details

- **Request URL:** `The Call`
- **Reason for Request:** Brief Description of why you need this Endpoint
- **Request Type:** `The type of Request`
- **Request Body:**
    ```json
    {
      "key": "value",
    }
    ```
#### Possible Responses

- **Status Code:** `Status Code`
    - **Response Body:**
        ```json
        {
          "key": "value",
        }
        ```
---

Endpoints
---

## Announcements Controller

- **Request URL:** `/courses/{courseId}/announcements`
- **Request Type:** `GET`
- **Description:** Retrieves all announcements for a course.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    [
      {
        "announcementId": 1,
        "title": "Announcement Title",
        "content": "Announcement Content"
      },
      ...
    ]
    ```

- **Request URL:** `/courses/{courseId}/announcements/{announcementId}`
- **Request Type:** `GET`
- **Description:** Retrieves an announcement by its ID.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "announcementId": 1,
      "title": "Announcement Title",
      "content": "Announcement Content"
    }
    ```

- **Request URL:** `/courses/{courseId}/announcements`
- **Request Type:** `POST`
- **Description:** Creates an announcement for a course.
- **Request Body:**
    ```json
    {
      "title": "Announcement Title",
      "content": "Announcement Content"
    }
    ```
- **Response Status:** `201 Created`
- **Response Body:**
    ```json
    {
      "announcementId": 1,
      "title": "Announcement Title",
      "content": "Announcement Content"
    }
    ```

- **Request URL:** `/courses/{courseId}/announcements/{announcementId}`
- **Request Type:** `DELETE`
- **Description:** Deletes an announcement by its ID.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "message": "Announcement deleted successfully"
    }
    ```

---

## Assignments Controller

- **Request URL:** `/courses/{courseId}/assignments`
- **Request Type:** `GET`
- **Description:** Retrieves all assignments for a course.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    [
      {
        "assignmentId": 1,
        "title": "Assignment Title",
        "description": "Assignment Description"
      },
      ...
    ]
    ```

- **Request URL:** `/courses/{courseId}/assignments`
- **Request Type:** `POST`
- **Description:** Creates an assignment for a course.
- **Request Body:**
    ```json
    {
      "title": "Assignment Title",
      "description": "Assignment Description"
    }
    ```
- **Response Status:** `201 Created`
- **Response Body:**
    ```json
    {
      "assignmentId": 1,
      "title": "Assignment Title",
      "description": "Assignment Description"
    }
    ```

- **Request URL:** `/courses/{courseId}/assignments/{assignmentId}`
- **Request Type:** `DELETE`
- **Description:** Deletes an assignment by its ID.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "message": "Assignment deleted successfully"
    }
    ```

---

## Courses Controller

- **Request URL:** `/courses/createCourse`
- **Request Type:** `POST`
- **Description:** Creates a course.
- **Request Body:**
    ```json
    {
      "courseName": "Course Name",
      "courseCapacity": "Course Capacity"
    }
    ```
- **Response Status:** `201 Created`
- **Response Body:**
    ```json
    {
      "courseId": 1,
      "courseName": "Course Name",
      "courseCapacity": "Course Capacity"
    }
    ```

- **Request URL:** `/courses`
- **Request Type:** `GET`
- **Description:** Retrieves all courses.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    [
      {
        "courseId": 1,
        "courseName": "Course Name",
        "courseCapacity": "Course Capacity"
      },
      ...
    ]
    ```

- **Request URL:** `/courses/courseList`
- **Request Type:** `GET`
- **Description:** Retrieves a list of CourseDTOs.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    [
      {
        "id": 1,
        "courseName": "Course Name",
        "courseCapacity": "Course Capacity",
        "teacher": {
          "id": 1,
          "firstName": "Teacher First Name",
          "lastName": "Teacher Last Name"
        }
      },
      ...
    ]
    ```

- **Request URL:** `/courses/{id}`
- **Request Type:** `GET`
- **Description:** Retrieves a course by its ID.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "courseId": 1,
      "courseName": "Course Name",
      "courseCapacity": "Course Capacity"
    }
    ```

- **Request URL:** `/courses/deleteCourse/{id}`
- **Request Type:** `DELETE`
- **Description:** Deletes a course by its ID.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "message": "Course deleted successfully"
    }
    ```

- **Request URL:** `/courses/assignTeacher/{courseId}/{teacherId}`
- **Request Type:** `PUT`
- **Description:** Assigns a teacher to a course.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "message": "Teacher assigned successfully"
    }
    ```

- **Request URL:** `/courses/assignStudent/{courseId}/{studentId}`
- **Request Type:** `PUT`
- **Description:** Assigns a student to a course.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "message": "Student assigned successfully"
    }
    ```

- **Request URL:** `/courses/{courseId}/students`
- **Request Type:** `GET`
- **Description:** Retrieves the students enrolled in a course.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    [
      {
        "userId": 1,
        "firstName": "Student First Name",
        "lastName": "Student Last Name"
      },
      ...
    ]
    ```

- **Request URL:** `/courses/{courseId}/teacher`
- **Request Type:** `GET`
- **Description:** Retrieves the teacher of a course.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "userId": 1,
      "firstName": "Teacher First Name",
      "lastName": "Teacher Last Name"
    }
    ```

- **Request URL:** `/courses/removeStudent/{courseId}/{studentId}`
- **Request Type:** `DELETE`
- **Description:** Removes a student from a course.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "message": "Student removed successfully"
    }
    ```

---

## Grades Controller

- **Request URL:** `/{courseId}/grades/{userId}`
- **Request Type:** `GET`
- **Description:** Retrieves all grades for a student.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    [
      {
        "gradeId": 1,
        "grade": "Grade Value"
      },
      ...
    ]
    ```

- **Request URL:** `/{courseId}/grades`
- **Request Type:** `GET`
- **Description:** Retrieves all grades for an assignment.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    [
      {
        "gradeId": 1,
        "grade": "Grade Value"
      },
      ...
    ]
    ```

- **Request URL:** `/{courseId}/grades/course`
- **Request Type:** `GET`
- **Description:** Retrieves all grades for a course.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    [
      {
        "userId": 1,
        "firstName": "Student First Name",
        "lastName": "Student Last Name",
        "grades": [
          {
            "gradeId": 1,
            "grade": "Grade Value"
          },
          ...
        ]
      },
      ...
    ]
    ```

- **Request URL:** `/{courseId}/grades/{assignmentId}/{userId}`
- **Request Type:** `GET`
- **Description:** Retrieves a grade for a specific student and assignment.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "gradeId": 1,
      "grade": "Grade Value"
    }
    ```

- **Request URL:** `/{courseId}/grades/{assignmentId}/{userId}`
- **Request Type:** `POST`
- **Description:** Assigns a grade to a specific student and assignment.
- **Request Body:**
    ```json
    {
      "grade": "Grade Value"
    }
    ```
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "gradeId": 1,
      "grade": "Grade Value"
    }
    ```

- **Request URL:** `/{courseId}/grades/save-grades`
- **Request Type:** `POST`
- **Description:** Assigns grades in bulk.
- **Request Body:**
    ```json
    [
      {
        "userId": 1,
        "firstName": "Student First Name",
        "lastName": "Student Last Name",
        "grades": [
          {
            "gradeId": 1,
            "grade": "Grade Value"
          },
          ...
        ]
      },
      ...
    ]
    ```
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    [
      {
        "userId": 1,
        "firstName": "Student First Name",
        "lastName": "Student Last Name",
        "grades": [
          {
            "gradeId": 1,
            "grade": "Grade Value"
          },
          ...
        ]
      },
      ...
    ]
    ```

---

## Users Controller

- **Request URL:** `/users`
- **Request Type:** `GET`
- **Description:** Retrieves all users.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    [
      {
        "userId": 1,
        "firstName": "User First Name",
        "lastName": "User Last Name"
      },
      ...
    ]
    ```

- **Request URL:** `/users/students`
- **Request Type:** `GET`
- **Description:** Retrieves all students.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    [
      {
        "userId": 1,
        "firstName": "Student First Name",
        "lastName": "Student Last Name"
      },
      ...
    ]
    ```

- **Request URL:** `/users/teachers`
- **Request Type:** `GET`
- **Description:** Retrieves all teachers.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    [
      {
        "userId": 1,
        "firstName": "Teacher First Name",
        "lastName": "Teacher Last Name"
      },
      ...
    ]
    ```

- **Request URL:** `/users/{id}`
- **Request Type:** `GET`
- **Description:** Retrieves a user by its ID.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "userId": 1,
      "firstName": "User First Name",
      "lastName": "User Last Name"
    }
    ```

- **Request URL:** `/users/register`
- **Request Type:** `POST`
- **Description:** Registers a user.
- **Request Body:**
    ```json
    {
      "firstName": "User First Name",
      "lastName": "User Last Name",
      "username": "Username",
      "password": "Password"
    }
    ```
- **Response Status:** `201 Created`
- **Response Body:**
    ```json
    {
      "userId": 1,
      "firstName": "User First Name",
      "lastName": "User Last Name"
    }
    ```

- **Request URL:** `/users/deleteUser/{id}`
- **Request Type:** `DELETE`
- **Description:** Deletes a user by its ID.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "message": "User deleted successfully"
    }
    ```

- **Request URL:** `/users/login`
- **Request Type:** `POST`
- **Description:** Logs in a user.
- **Request Body:**
    ```json
    {
      "username": "Username",
      "password": "Password"
    }
    ```
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    {
      "message": "Login Success!"
    }
    ```

- **Request URL:** `/users/{studentId}/courses/{courseId}/announcements`
- **Request Type:** `GET`
- **Description:** Retrieves all announcements for a specific student and course.
- **Response Status:** `200 OK`
- **Response Body:**
    ```json
    [
      {
        "announcementId": 1,
        "title": "Announcement Title",
        "content": "Announcement Content"
      },
     ...
    ]
    ```