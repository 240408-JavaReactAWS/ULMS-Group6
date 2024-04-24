package com.revature.backend.services;


import com.revature.backend.repos.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private UsersDAO usersDAO;

    @Autowired
    public UsersService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
<<<<<<< Updated upstream
=======
        this.assignmentsDAO  = assignmentsDAO;
        this.announcementsDAO = announcementsDAO;
    }

    //As a Student, I can view all my courses.
    public Set<Courses> getEnrolledCourses(Integer studentId) throws NoSuchUserFoundException {
        Optional<Users> usersOptional = usersDAO.findById(studentId);
        if(usersOptional.isPresent()){
            Users user = usersOptional.get();
            return user.getEnrolledCourses();
        }else{
            throw new NoSuchUserFoundException("No user found with ID: " + studentId);
        }
    }

    //As a Student, I can check my assignments and due dates.
    public List<Assignments> getAssignmentsByCourseAndStudent(Integer studentId, Integer courseId) {
        return assignmentsDAO.findByCourse_Students_UserIdAndCourse_CourseId(studentId, courseId);
    }

    //As a Student, I can check course Announcements for different courses.
    public List<Announcements> getAllAnnouncementsByCourseId(Integer studentId, Integer courseId){
        return announcementsDAO.findByCourse_Students_UserIdAndCourse_CourseId(studentId, courseId);
    }

    public boolean login (Users user) {
        Users existingUser = usersDAO.findByUsername(user.getUsername());
        return existingUser != null && existingUser.getPassword().equals(user.getPassword());
>>>>>>> Stashed changes
    }
}
